package com.tsspdcl.onlinecsc.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.tsspdcl.onlinecsc.model.CCAppDataModel;
import com.tsspdcl.onlinecsc.model.CompNaturePaymentsModel;
import com.tsspdcl.onlinecsc.model.EBSModel;

public class InsertCC1 {

	CSCDB db = new CSCDB();
	
	public void insert(CCAppDataModel AppData, HttpServletRequest request) throws Exception {
		
		String paddingno="",REGNO="", gst_updated_on = "", status = "", is_payment_req="", payment_type="", cnature_desc = "";
		CSCDB db = new CSCDB();
		
		String[] cnature_list = {"53","54","24","59","76",   "45","48","62","64","65","78","81"}; // from "45" application fee
		for(int i = 0; i < cnature_list.length; i++) {
			if(AppData.getCnature().equals(cnature_list[i]) || (AppData.getCtype().equals("4") && !AppData.getCnature().equals("21") && !AppData.getCnature().equals("23")) )
				is_payment_req = "Y";
		}
				
		try(Connection con = db.getConnection()){
		    
		    EBSMethods ebsAll = new EBSMethods();
		    EBSModel em = new EBSModel();
		    DBMethodsComplaints dbmc = new DBMethodsComplaints();
		    em = ebsAll.getAllConsumerData(Integer.parseInt(AppData.getXukscno()));
		    
			int CSCNO = Integer.parseInt( em.getCscno());
						
			paddingno=dbmc.generateRegno(); //generating Registration number
			REGNO="CC"+CSCNO+paddingno;
		    
		    //payment fetch start
			double af=0.0, afgst = 0.0, other_charges = 0.0, other_cgst = 0.0, other_sgst = 0.0, totamt=0.0;
			CompNaturePaymentsModel cnpm = new CompNaturePaymentsModel();
			if(AppData.getCnature().equals("73")) {
				af = 75;
				totamt = 75;
				payment_type = "XX";
			}
			if(AppData.getCnature().equals("71")) {
				totamt = Double.parseDouble(AppData.getOthamount());
				payment_type = "XX";
			}
			if(is_payment_req.equals("Y") ) {
				cnpm = dbmc.getCnaturePayments(AppData.getCnature(),AppData.getCtype(),em.getCatcode());
				af = cnpm.getCnature_af();
				afgst = cnpm.getCnature_afgst();
				other_charges = cnpm.getCnature_sc();
				other_cgst = cnpm.getCnature_scgst()/2;
				other_sgst = cnpm.getCnature_scgst()/2;
				totamt = cnpm.getCnature_total();
				if(cnpm.getIs_appfee().equals("Y"))
					payment_type = "AF";
				if(cnpm.getIs_other_amount().equals("Y"))
					payment_type = "SC";
			}
			//payment fetch end
			
			if(payment_type.equals(""))
				status = "3";
			
			if(AppData.getCnature().equals("85") || AppData.getCnature().equals("86"))
				cnature_desc = dbmc.getCtype(AppData.getCnature(),2);
			else
				cnature_desc = AppData.getCdetails();
			
			double existing_load =0.0;
	 		String metrics = "";
	 		if(dbmc.getMetric(em.getCatcode(), em.getSubcatcode()).equals("HP")){
				existing_load = em.getLoad();
				metrics = "HP";
			}else {
				existing_load = 1000 * em.getLoad();
				metrics = "Watts";
			}
			
			
		    // PR_NO, PRNO_AT,  DD_NUMBER, DD_DATE, DD_AMOUNT, EMAIL, COMPLAINT_DETAILS, PREV_OFFICE_ID, BANK_NAME, OTH_COM_NAME, REQD_CATEGORY_ID,
		    //CONS_NEW_NAME, NCONNADD1, NCONNADD2, NCONNADD3, NCONNADD4,CLUB_LOAD,oth_cgst,oth_sgst, +dotcond+, SECURITY_DEPOSIT, DEV_CHRGS, DCGST,\
		    String query="INSERT INTO COMPLAINT_DETAILS "
		    			+ " (ID, COMPLAINT_NO, SCNO, pincode, gstnflag, GSTN, TYPE, EST_TYPE, OFFICE_PHONE, MOBILE_NO, "
		    			+ " NAME, COMPLAINANTS_NAME, ADDRESS_1, ADDRESS_2, AREA_NAME, ADDRESS_4, CATEGORY_ID, CONTRACTED_LOAD, seccd, ukscno, "
		    			+ " TOT_AMOUNT, APP_FEE, appl_fees_gst,  TOBE_PAID_AMT, COMPLAINT_NATUREID, CSCNO, CSCUSERID, "
		    			+ " phase, ISMTRREQ,  EXIST_SD, IS_DISPATCHED, STATUS, CREATED_BY, RECORD_STATUS," //check condition for phase and ismtrreq
		    			+ " GSTN_UPDT, COMPLAINT_GIVEN_ON, CREATE_DATE,"
		    			+ " COMPLAINT_DETAILS, AMOUNT, OTH_CGST, OTH_SGST,"
		    			+ " ADV_CC_CH, REQD_LOAD,"
		    			+ " NETMETERFLAG, CTMTRFLAG, PREPAIDFLAG, MTRSIDE_HT, SUBCATCD)"
		    			+ " VALUES(?,?,?,?,?,?,?,?,?,?,"
						+ 		 " ?,?,?,?,?,?,?,?,?,?,"
						+ 		 " ?,?,?,?,?,?,?,"
						+        " ?,?,?,?,?,?,?,"
						+ 		 " TO_DATE(?,'DD-MM-YY'), TO_DATE(sysdate,'DD-MM-YY'), TO_DATE(sysdate,'DD-MM-YY'), "
						+ 		 " ?,?,?,?,"
						+ 		 " ?,?,"
						+ 		 " ?,?,?,?,?)";
		    con.setAutoCommit(false);
			
			PreparedStatement ps = con.prepareStatement(query);
	 		ps.setString ( 1  , paddingno );
	 		ps.setString ( 2  , REGNO );
	 		ps.setString ( 3  , em.getScno() );
	 		ps.setString ( 4  , AppData.getPincode() );//ps.setInt changed to ps.setString
	 		ps.setString ( 5  , AppData.getGstn_flag() );
	 		ps.setString ( 6  , AppData.getGstno() );
	 		ps.setString ( 7  , AppData.getExtension() );
	 		ps.setString ( 8  , AppData.getEst_type()  );
	 		ps.setString ( 9  , AppData.getXmobileno() );
	 		ps.setString ( 10 , AppData.getXmobileno() );
	 		
	 		ps.setString ( 11 , em.getName() );
	 		ps.setString ( 12 , em.getName() );
	 		ps.setString ( 13 , em.getAddr1() );
	 		ps.setString ( 14 , em.getAddr2() );
	 		ps.setString ( 15 , em.getAddr3() );
	 		ps.setString ( 16 , em.getAddr4() );
	 		ps.setString ( 17 , em.getCatcode() );
	 		ps.setDouble ( 18 , existing_load ); //load in watts
	 		ps.setString ( 19 , em.getSeccode() );
	 		ps.setString ( 20 , AppData.getXukscno() );
	 		
	 		ps.setDouble ( 21 , totamt );
	 		ps.setDouble ( 22 , af);
	 		ps.setDouble ( 23 , afgst);
	 		ps.setDouble ( 24 , totamt);
	 		ps.setInt	 ( 25 , Integer.parseInt(AppData.getCnature()) );
	 		ps.setString ( 26 , em.getCscno() );
	 		ps.setInt	 ( 27 , 7 );	 	
	 		
	 		ps.setInt	 ( 28 , em.getPhase() ); //Check condition 
	 		ps.setString ( 29 , "Y" ); //Check condition 
	 		ps.setDouble ( 30 , em.getExisting_sd() );//check ebs field name from corporate table	 		
	 		ps.setString ( 31 , "Y" ); //check condition
	 		ps.setString ( 32 , status ); //status not required?
	 		ps.setInt	 ( 33 , 9 );
	 		ps.setString ( 34 , "ACTIVE" ); 
	 		
	 		ps.setString ( 35 , gst_updated_on); 	 		
	 		
	 		ps.setString ( 36 , cnature_desc);
	 		ps.setDouble ( 37 , other_charges);
	 		ps.setDouble ( 38 , other_cgst); 
	 		ps.setDouble ( 39 , other_sgst); 
	 		
	 		ps.setString ( 40 , AppData.getOthamount()); 
	 		ps.setString ( 41 , AppData.getTempload());
	 		
	 		ps.setInt	 ( 42 , em.getNetmtr_flag());
	 		ps.setInt	 ( 43 , em.getCtmtr_flag());
	 		ps.setInt	 ( 44 , em.getPrepaid_flag());
	 		ps.setString ( 45 , em.getMeter_ltht_flag());
	 		ps.setString ( 46 , em.getSubcatcode());

	 		ps.executeUpdate();
	 		
	 		//Insert Documents starts
	 		if(AppData.getCtype().equals("5") ) {
	 			optionalFileUpload1(AppData.getDoc1(),AppData.getDoc2(), REGNO, con) ;
	 		}
	 		if(AppData.getCtype().equals("6")) {
	 			optionalFileUpload2(AppData.getDoc1(),REGNO, con) ;
	 		}
	 		//Insert Documents Ends
	        
	        String title = "";
			String qry2="SELECT COMPLAINT_NATURE FROM COMPLAINT_NATURE WHERE ID = ? ";
			PreparedStatement ps2 = con.prepareStatement(qry2);		
			ps2.setString(1, AppData.getCnature());
			ResultSet rs2=ps2.executeQuery();			
			if(rs2.next())
			{
				title=rs2.getString(1);
		    }
	        	 		
			HttpSession sess = request.getSession();
			String temp="";
			DBMethods dbm = new DBMethods();

			System.out.println(is_payment_req);
		   if(is_payment_req.equals("Y") || AppData.getCnature().equals("73") || AppData.getCnature().equals("71") ) {     
		        temp = "TSSPDCL|" + REGNO + "|NA|" + totamt + "|NA|NA|NA|INR|NA|R|tsspdcl|NA|NA|F|NEWSCON|NA|NA|NA|NA|NA|NA|http://117.239.151.19:8080/RegPmtReceipt.jsp";
			    String strHash = null;
			    String commonstr = "BObYe64qYnS6";
			    try {
			        strHash = dbm.HmacSHA256(temp, commonstr);
			    } catch (Exception eCRC) {
			    	System.out.println(eCRC.toString());
			    } 
			    temp = String.valueOf(temp) + "|" + strHash;
			}
		    sess.setAttribute("header_title", title);
		    sess.setAttribute("regno", REGNO);
		    sess.setAttribute("appname", em.getName());
		    sess.setAttribute("category", em.getCatcode() + "-" + em.getCatdescription());
		    sess.setAttribute("contld", String.valueOf(existing_load) );
		    sess.setAttribute("metric", metrics);
		    sess.setAttribute("appfee", String.valueOf(af));
		    sess.setAttribute("appfee_gst", String.valueOf(afgst));
		    sess.setAttribute("sc_charges", String.valueOf(other_charges));
		    sess.setAttribute("sc_gst", String.valueOf(2*other_cgst));
		    sess.setAttribute("totamt", String.valueOf(totamt));
		    sess.setAttribute("trmsg", temp);		
		    sess.setAttribute("ctype", AppData.getCtype());
		    sess.setAttribute("cnature", AppData.getCnature());
		    sess.setAttribute("cdetails", AppData.getCdetails());
		    sess.setAttribute("payment_type", payment_type);
		    
		}	
		
	}
	
	
	public void optionalFileUpload1(MultipartFile doc1, MultipartFile doc2, String REGNO, Connection con) throws IOException, SQLException {
		String qryDoc1 = "",qryDoc2 = "";
			String newDoc1Name = "", newDoc2Name="";
 		String FILE_DIRECTORY = "E:\\ONLINE_CSC_FILES\\CCFiles\\";
			if(!doc1.isEmpty()) {
				qryDoc1 = "insert into documents (regno, id_proof) values(?,?) ";
				newDoc1Name       = REGNO +"_"+ doc1.getOriginalFilename();
				File Doc1File       = new File(FILE_DIRECTORY + newDoc1Name);
				Doc1File.createNewFile();		
	        FileOutputStream Fos_Doc1File = new FileOutputStream(Doc1File);		   
	        Fos_Doc1File.write(doc1.getBytes());
	        Fos_Doc1File.close();	
	        FileInputStream Fis_Doc1File       = new FileInputStream(FILE_DIRECTORY + newDoc1Name);		        
	        PreparedStatement psDoc1 = con.prepareStatement(qryDoc1);
	        psDoc1.setString(1, REGNO);
	        psDoc1.setBinaryStream(2, Fis_Doc1File, Fis_Doc1File.available());
	        psDoc1.executeUpdate();  
	        System.out.println("Doc1 file uploaded for "+REGNO);    
	        if(!doc2.isEmpty()) {
 				qryDoc2 = "UPDATE  documents SET  sale_deed=? where REGNO=?";
 				newDoc2Name       = REGNO +"_"+ doc2.getOriginalFilename();
 				File Doc2File       = new File(FILE_DIRECTORY + newDoc2Name);
 				Doc2File.createNewFile();		
		        FileOutputStream Fos_Doc2File = new FileOutputStream(Doc2File);		   
		        Fos_Doc2File.write(doc2.getBytes());
		        Fos_Doc2File.close();	
		        FileInputStream Fis_Doc2File       = new FileInputStream(FILE_DIRECTORY + newDoc2Name);		        
		        PreparedStatement psDoc2 = con.prepareStatement(qryDoc2);
		        psDoc2.setBinaryStream(1, Fis_Doc2File, Fis_Doc2File.available());
		        psDoc2.setString(2, REGNO);
		        psDoc2.executeUpdate();  
		        System.out.println("Doc2 file uploaded for "+REGNO);     	 		

 			}

			}
			if(doc1.isEmpty() && !doc2.isEmpty()) {              
				qryDoc2 = "insert into documents (regno, sale_deed) values(?,?) ";
				newDoc2Name       = REGNO +"_"+ doc2.getOriginalFilename();
				File Doc2File       = new File(FILE_DIRECTORY + newDoc2Name);
				Doc2File.createNewFile();		
	        FileOutputStream Fos_Doc2File = new FileOutputStream(Doc2File);		   
	        Fos_Doc2File.write(doc2.getBytes());
	        Fos_Doc2File.close();	
	        FileInputStream Fis_Doc2File       = new FileInputStream(FILE_DIRECTORY + newDoc2Name);		         
	        PreparedStatement psDoc2 = con.prepareStatement(qryDoc2);
	        psDoc2.setString(1, REGNO);
	        psDoc2.setBinaryStream(2, Fis_Doc2File, Fis_Doc2File.available());
	        psDoc2.executeUpdate();  
	        System.out.println("Doc2 file uploaded for "+REGNO);    
				
			}
 		  
	}
	
	public void optionalFileUpload2(MultipartFile doc1, String REGNO, Connection con) throws IOException, SQLException {
		String qryDoc1 = "";
			String newDoc1Name = "";
 		String FILE_DIRECTORY = "E:\\CCFiles\\";
			if(!doc1.isEmpty()) {
				qryDoc1 = "insert into documents (regno, id_proof) values(?,?) ";
				newDoc1Name       = REGNO +"_"+ doc1.getOriginalFilename();
				File Doc1File       = new File(FILE_DIRECTORY + newDoc1Name);
				Doc1File.createNewFile();		
		        FileOutputStream Fos_Doc1File = new FileOutputStream(Doc1File);		   
		        Fos_Doc1File.write(doc1.getBytes());
		        Fos_Doc1File.close();	
		        FileInputStream Fis_Doc1File       = new FileInputStream(FILE_DIRECTORY + newDoc1Name);		        
		        PreparedStatement psDoc1 = con.prepareStatement(qryDoc1);
		        psDoc1.setString(1, REGNO);
		        psDoc1.setBinaryStream(2, Fis_Doc1File, Fis_Doc1File.available());
		        psDoc1.executeUpdate();  
		        System.out.println("Doc1 file uploaded for "+REGNO);    
			}
 		  
	}
		
}
