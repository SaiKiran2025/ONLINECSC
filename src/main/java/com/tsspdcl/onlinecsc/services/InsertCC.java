package com.tsspdcl.onlinecsc.services;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tsspdcl.onlinecsc.model.CCAppDataModel;
import com.tsspdcl.onlinecsc.model.EBSModel;
import com.tsspdcl.onlinecsc.model.PreReqInputModelAdl;
import com.tsspdcl.onlinecsc.model.PreReqModel;

public class InsertCC {

	CSCDB db = new CSCDB();
	
	public void insert(CCAppDataModel AppData, HttpServletRequest request) throws Exception {		
		String paddingno="",REGNO="", gst_updated_on = "", status = "", type="", est_type="", cnature_desc = "", req_load = "";
		CSCDB db = new CSCDB();
				
		try(Connection con = db.getConnection()){
		    
		    EBSMethods ebsAll = new EBSMethods();
		    EBSModel em = new EBSModel();
		    DBMethodsComplaints dbmc = new DBMethodsComplaints();
		    System.out.println(AppData.getXukscno());
		    em = ebsAll.getAllConsumerData(Integer.parseInt(AppData.getXukscno()));
		    if(AppData.getCnature().equals("69")) 
		    	em = ebsAll.getAllConsumerData(Integer.parseInt(AppData.getParent_uscno()));
		    
			int CSCNO = Integer.parseInt( em.getCscno());
						
			paddingno=dbmc.generateRegno();
			if(AppData.getCnature().equals("40"))
				REGNO="AL"+CSCNO+paddingno;
			else
				REGNO="CC"+CSCNO+paddingno;
		    
		    //payment fetch start
		    PreReqModel prm = new PreReqModel();
		    double totamt=0.0, load = 0.00;
		    
		    cnature_desc = dbmc.getCtype(AppData.getCnature(),2);
		    
		    if(AppData.getCnature().equals("40") || (AppData.getCnature().equals("69") && AppData.getIs_adl_req().equals("Y"))) {
		    	PreReqInputModelAdl prima = new PreReqInputModelAdl();
		    	prima.setUkscno(AppData.getXukscno());
		    	prima.setReq_load_prereq(AppData.getAdditional_load());
		    	prima.setExtension(AppData.getExtension());
			    prm = dbmc.genPreReqAdl(prima); // CALLING for AL PAYMENT DETAILS			    	
		    }
		    else if(AppData.getCnature().equals("42")) {
			    prm = dbmc.getCatChangePayments(Integer.parseInt(AppData.getNew_cat()), AppData.getXukscno());					    
		    }
		    else if(AppData.getCnature().equals("79")) {
		    	prm = dbmc.getZeroPayments();
		    	status = "0";
		    }
		    else {
		    	prm = dbmc.getAppFeePayments(AppData.getCnature(), em.getCatcode());
		    }		    
			//payment fetch end
		    
		    if(AppData.getCnature().equals("40") || AppData.getCnature().equals("42") || AppData.getCnature().equals("69")) {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			    Date date = new Date(); 
				if(AppData.getGstn_flag().equals("Y")) 
					gst_updated_on = formatter.format(date);
		    }
		    
		    //code when estimate required option removed on entryCC2 page
		    /*if(AppData.getCnature().equals("40")) {
		    	req_load = AppData.getAdditional_load();
		    	if(em.getCatcode().equals("3") || em.getCatcode().equals("4") ) {
			    		type="Y";
			    		est_type="Turn Key";
		    	}
		    	if(em.getCatcode().equals("5") || em.getCatcode().equals("6") ) {
		    		type="Y";
		    		est_type="Department";
		    	}
		    	if(em.getCatcode().equals("1") || em.getCatcode().equals("2") || em.getCatcode().equals("7") | em.getCatcode().equals("8")) {
			    	if(Double.parseDouble(AppData.getTotal_load()) > 15000){
			    		type="Y";
			    		est_type="Turn Key";
			    	}
		    	}		    	
		    }*/    		    		    
		    
		    //code for additional load
		    if(AppData.getCnature().equals("40")) {
		    	req_load = AppData.getAdditional_load();
		    }
			double existing_load =0.0;
	 		String metrics = "";
	 		if(dbmc.getMetric(em.getCatcode(), em.getSubcatcode()).equals("HP")){
					existing_load = em.getLoad();
					metrics = "HP";
			}else {
					existing_load = 1000 * em.getLoad();
					metrics = "Watts";
				}
			
		    //for clubbing of load
			double club_load = 0.0;
		    if(AppData.getCnature().equals("69")) {
		    	System.out.println("Total load for cnature 69 is  " + AppData.getTotal_club_load_final());
		    	req_load = AppData.getReq_load_club();
		    	AppData.setTotal_load(AppData.getTotal_club_load_final());
		    	AppData.setXukscno(AppData.getParent_uscno());
		    	club_load = existing_load + Double.parseDouble(AppData.getReq_load_club()) ;
		    }
			
		    System.out.println("Total load for cnature 69 is  " + AppData.getTotal_load());
		    // PR_NO, PRNO_AT,  DD_NUMBER, DD_DATE, DD_AMOUNT, EMAIL, COMPLAINT_DETAILS, PREV_OFFICE_ID, BANK_NAME, OTH_COM_NAME, REQD_CATEGORY_ID,
		    //CONS_NEW_NAME, NCONNADD1, NCONNADD2, NCONNADD3, NCONNADD4,CLUB_LOAD,oth_cgst,oth_sgst, +dotcond+\
		    String query="INSERT INTO COMPLAINT_DETAILS "
		    			+ " (ID, COMPLAINT_NO, REQD_LOAD, pincode, gstnflag, GSTN, TYPE, EST_TYPE, OFFICE_PHONE, MOBILE_NO, "
		    			+ " SCNO, NAME, COMPLAINANTS_NAME, ADDRESS_1, ADDRESS_2, AREA_NAME, ADDRESS_4, CATEGORY_ID, CONTRACTED_LOAD, seccd, "
		    			+ " TOT_AMOUNT, APP_FEE, appl_fees_gst, SECURITY_DEPOSIT, DEV_CHRGS, DCGST, TOBE_PAID_AMT, COMPLAINT_NATUREID, CSCNO, CSCUSERID, "
		    			+ " TOTAL_LAOD, phase, COMPLAINT_DETAILS, ukscno, EXIST_SD, IS_DISPATCHED, STATUS, CREATED_BY, RECORD_STATUS," //check condition for phase and ismtrreq
		    			+ " GSTN_UPDT, COMPLAINT_GIVEN_ON, CREATE_DATE,"
		    			+ " NCONNADD1, NCONNADD2, NCONNADD3, NCONNADD4,"
		    			+ " REQD_CATEGORY_ID,"
		    			+ " CONS_NEW_NAME, FATHER_HUSBAND_NAME,"
		    			+ "	CLUBBEDSERVICES, CLB_ADL_LOAD, CLUB_LOAD,"
		    			+ " NETMETERFLAG, CTMTRFLAG, PREPAIDFLAG, MTRSIDE_HT, SUBCATCD)"
		    			+ " VALUES(?,?,?,?,?,?,?,?,?,?,"
						+ 		 " ?,?,?,?,?,?,?,?,?,?,"
						+ 		 " ?,?,?,?,?,?,?,?,?,?,"
						+        " ?,?,?,?,?,?,?,?,?,"
						+ 		 " TO_DATE(?,'DD-MM-YY'), TO_DATE(sysdate,'DD-MM-YY'), TO_DATE(sysdate,'DD-MM-YY'), "
						+ 		 " ?,?,?,?, "
						+ 		 " ?,"
						+ 		 " ?,?,"
						+ 		 " ?,?,?,"
						+ 		 " ?,?,?,?,?)";
		    con.setAutoCommit(false);
			
			PreparedStatement ps = con.prepareStatement(query);
	 		ps.setString ( 1  , paddingno );
	 		ps.setString ( 2  , REGNO );
	 		ps.setString ( 3  , req_load );//for additional load and clubbing with additional load
	 		ps.setString ( 4  , AppData.getPincode() );
	 		ps.setString ( 5  , AppData.getGstn_flag() );
	 		ps.setString ( 6  , AppData.getGstno() );
	 		//ps.setString ( 7  , type );
	 		//ps.setString ( 8  , est_type );
	 		ps.setString ( 7  , AppData.getExtension() );
	 		ps.setString ( 8  , AppData.getEst_type() );
	 		ps.setString ( 9  , AppData.getXmobileno() );
	 		ps.setString ( 10 , AppData.getXmobileno() );
	 		
	 		ps.setString ( 11 , em.getScno() );
	 		ps.setString ( 12 , em.getName() );
	 		ps.setString ( 13 , em.getName() );
	 		ps.setString ( 14 , em.getAddr1() );
	 		ps.setString ( 15 , em.getAddr2() );
	 		ps.setString ( 16 , em.getAddr3() );
	 		ps.setString ( 17 , em.getAddr4() );
	 		ps.setString ( 18 , em.getCatcode() );
	 		ps.setDouble ( 19 , existing_load ); //load in watts
	 		ps.setString ( 20 , em.getSeccode() );
	 		
	 		ps.setDouble ( 21 , Double.parseDouble(prm.getTot_pre() ) );
	 		ps.setDouble ( 22 , Double.parseDouble(prm.getAf_pre() ));
	 		ps.setDouble ( 23 , Double.parseDouble(prm.getAfgst_pre() ));
	 		ps.setDouble ( 24 , Double.parseDouble(prm.getSd_pre() ));
	 		ps.setDouble ( 25 , Double.parseDouble(prm.getDc_pre() ));
	 		ps.setDouble ( 26 , Double.parseDouble(prm.getDcgst_pre() ));
	 		ps.setDouble ( 27 , Double.parseDouble(prm.getTot_pre() ));
	 		ps.setInt	 ( 28 , Integer.parseInt(AppData.getCnature()) );
	 		ps.setString ( 29 , em.getCscno() );
	 		ps.setInt	 ( 30 , 7 );
	 		
	 		ps.setString ( 31 , AppData.getTotal_load());
	 		ps.setInt	 ( 32 , em.getPhase() ); 
	 		ps.setString ( 33 , cnature_desc ); 
	 		ps.setString ( 34 , AppData.getXukscno() );
	 		ps.setDouble ( 35 , em.getExisting_sd() );
	 		ps.setString ( 36 , "Y" ); //not nullable, dafault value is 'Y
	 		ps.setString ( 37 , status ); //0 status updating only for load deration
	 		ps.setInt	 ( 38 , 9 );
	 		ps.setString ( 39 , "ACTIVE" ); 
	 		ps.setString ( 40 , gst_updated_on); 	 		
	 		
	 		ps.setString ( 41 , AppData.getNaddress1());
	 		ps.setString ( 42 , AppData.getNaddress2());
	 		ps.setString ( 43 , AppData.getNaddress3());
	 		ps.setString ( 44 , AppData.getNaddress4()); //for address correction	
	 		
	 		ps.setString ( 45 , AppData.getNew_cat()); //for category change
	 		
	 		ps.setString ( 46 , AppData.getNewtitle_name());
	 		ps.setString ( 47 , AppData.getCareOf()); //for title transfer
	 		
	 		ps.setString ( 48 , AppData.getXclubbedUscnos()); 
	 		ps.setString ( 49 , AppData.getAdditional_load());
	 		ps.setDouble ( 50 , club_load);//just clubbed load without additional load
	 		
	 		ps.setInt	 ( 51 , em.getNetmtr_flag());
	 		ps.setInt	 ( 52 , em.getCtmtr_flag());
	 		ps.setInt	 ( 53 , em.getPrepaid_flag());
	 		ps.setString ( 54 , em.getMeter_ltht_flag());
	 		ps.setString ( 55 , em.getSubcatcode());
	 		ps.executeUpdate();
	
	 		//Insert Documents for address correction, title transfer, load deration
	 		String FILE_DIRECTORY = "E:\\ONLINE_CSC_FILES\\CCFiles\\";
	 		if(AppData.getCnature().equals("41") || AppData.getCnature().equals("47") || AppData.getCnature().equals("48") || AppData.getCnature().equals("79")) {
			 		
		 		/*String newIdFileName = "", newRegistrationProofName="";
		 		
		 		newIdFileName       = REGNO +"_"+ AppData.getId_proof().getOriginalFilename();
		 		newRegistrationProofName = REGNO +"_"+ AppData.getRegistration_proof().getOriginalFilename();
	        		        		
		        File IdFile       = new File(FILE_DIRECTORY + newIdFileName);
		        File RegistrationProofFile = new File(FILE_DIRECTORY + newRegistrationProofName);
		        
		        IdFile.createNewFile();		        	        
		        RegistrationProofFile.createNewFile();
		        
		        FileOutputStream Fos_IdFile = new FileOutputStream(IdFile);		   
		        FileOutputStream Fos_RegistrationProofFile = new FileOutputStream(RegistrationProofFile);
		        
		        //File saving
		        Fos_IdFile.write(AppData.getId_proof().getBytes());
		        Fos_RegistrationProofFile.write(AppData.getRegistration_proof().getBytes());
		        
		        Fos_IdFile.close();		        
		        Fos_RegistrationProofFile.close();
	       
		        //File reading
		        FileInputStream Fis_IdFile       = new FileInputStream(FILE_DIRECTORY + newIdFileName);		        
		        FileInputStream Fis_RegistrationProofFile       = new FileInputStream(FILE_DIRECTORY + newRegistrationProofName);*/
		        
		 		FileStatements fs = new FileStatements(FILE_DIRECTORY, REGNO);
		 		FileInputStream Fis_IdFile       			= fs.FileRead(AppData.getId_proof());
		 		FileInputStream Fis_RegistrationProofFile   = fs.FileRead(AppData.getRegistration_proof());
		 		
		 		String qryDoc = "insert into documents (regno, idproof_type, id_proof, sale_deed_type, sale_deed ) values(?,?,?,?,?) ";
		 		
		        PreparedStatement psDoc = con.prepareStatement(qryDoc);
		        psDoc.setString(1, REGNO);
		        psDoc.setString(2, AppData.getIdproof_type());
		        psDoc.setBinaryStream(3, Fis_IdFile, Fis_IdFile.available());
		        psDoc.setString(4, AppData.getRegdoctype());
		        psDoc.setBinaryStream(5, Fis_RegistrationProofFile, Fis_RegistrationProofFile.available());
		        psDoc.executeUpdate();
		        
		        FileInputStream Fis_IdFile1       			= fs.FileRead(AppData.getId_proof());
		 		FileInputStream Fis_RegistrationProofFile1   = fs.FileRead(AppData.getRegistration_proof());
		 		
		 		String qryDoc1 = "insert into spddocs.documents (regno, idproof_type, id_proof, sale_deed_type, sale_deed ) values(?,?,?,?,?) ";
		 		
		        PreparedStatement psDoc1 = con.prepareStatement(qryDoc1);
		        psDoc1.setString(1, REGNO);
		        psDoc1.setString(2, AppData.getIdproof_type());
		        psDoc1.setBinaryStream(3, Fis_IdFile1, Fis_IdFile1.available());
		        psDoc1.setString(4, AppData.getRegdoctype());
		        psDoc1.setBinaryStream(5, Fis_RegistrationProofFile1, Fis_RegistrationProofFile1.available());
		        
		        psDoc1.executeUpdate();   
		        System.out.println("files uploaded for "+REGNO);
	 		}	       
	 		
	 		if(AppData.getCnature().equals("41") || AppData.getCnature().equals("47") ) {
	 			
	 			FileStatements fs = new FileStatements(FILE_DIRECTORY, REGNO);
		 		FileInputStream Fis_Ibond       			= fs.FileRead(AppData.getIndeminity_bond());
		 		
		 		String qryDoc = "UPDATE  documents SET  WIRING_CERTIFICATE=? where REGNO=?";	        		        
		        PreparedStatement psDoc = con.prepareStatement(qryDoc);
		        psDoc.setBinaryStream(1, Fis_Ibond, Fis_Ibond.available());
		        psDoc.setString(2, REGNO);		        
		        psDoc.executeUpdate();  
		        
		        FileInputStream Fis_Ibond1     = fs.FileRead(AppData.getIndeminity_bond());
		 		
		 		String qryDoc1 = "UPDATE  spddocs.documents SET  WIRING_CERTIFICATE=? where REGNO=?";	        		        
		        PreparedStatement psDoc1 = con.prepareStatement(qryDoc1);
		        psDoc1.setBinaryStream(1, Fis_Ibond1, Fis_Ibond1.available());
		        psDoc1.setString(2, REGNO);		        
		        psDoc1.executeUpdate(); 
		        
		        System.out.println("Indmenity file uploaded for "+REGNO);
	 		}
	 		
	 		//new category for paymmentCC page
	        if(AppData.getCnature().equals("42")) {
		 		String qry1 = "select id,category from category_master where id = ? ";
		 		PreparedStatement ps1 = con.prepareStatement(qry1);		
		 		ps1.setString(1, AppData.getNew_cat());
				ResultSet rs1 = ps1.executeQuery();	
				if(rs1.next()) 
					AppData.setNew_cat(rs1.getString(1) + "-" + rs1.getString(2));
		    }
	        
	        String title = "";
			String qry2="SELECT COMPLAINT_NATURE FROM COMPLAINT_NATURE WHERE ID = ? ";
			PreparedStatement ps2 = con.prepareStatement(qry2);		
			ps2.setString(1, AppData.getCnature());
			ResultSet rs2=ps2.executeQuery();			
			if(rs2.next())
			{
				title=rs2.getString(1);
		    }
	        
	 		DBMethods dbm = new DBMethods();
			HttpSession sess = request.getSession();
			totamt = Double.parseDouble(prm.getTot_pre() );
	        
	        String temp = "TSSPDCL|" + REGNO + "|NA|" + totamt + "|NA|NA|NA|INR|NA|R|tsspdcl|NA|NA|F|NEWSCON|NA|NA|NA|NA|NA|NA|http://117.239.151.19:8080/RegPmtReceipt.jsp";
		    String strHash = null;
		    String commonstr = "BObYe64qYnS6";
		    try {
		        strHash = dbm.HmacSHA256(temp, commonstr);
		    } catch (Exception eCRC) {
		    	System.out.println(eCRC.toString());
		    } 
		    temp = String.valueOf(temp) + "|" + strHash;
		    sess.setAttribute("header_title", title);
		    sess.setAttribute("regno", REGNO);
		    sess.setAttribute("appname", em.getName());
		    sess.setAttribute("category", em.getCatcode() + "-" + em.getCatdescription());
		    sess.setAttribute("contld", String.valueOf(existing_load) );
		    System.out.println(AppData.getAdditional_load());
		    sess.setAttribute("reqld", AppData.getAdditional_load());
		    if(AppData.getCnature().equals("40")) {
		    	sess.setAttribute("totld", String.valueOf(existing_load + Double.parseDouble( AppData.getAdditional_load() )) );
		    }else if(AppData.getCnature().equals("79")){
		    	sess.setAttribute("totld",String.valueOf(AppData.getAdditional_load()) );
		    }else {		   
		    	sess.setAttribute("totld","");
		    }
		    sess.setAttribute("metric", metrics);
		    sess.setAttribute("appfee", prm.getAf_pre());
		    sess.setAttribute("appfee_gst", prm.getAfgst_pre());
		    sess.setAttribute("secdp", prm.getSd_pre());
		    sess.setAttribute("devchg", prm.getDc_pre());
		    sess.setAttribute("devchg_gst", prm.getDcgst_pre());
		    sess.setAttribute("totamt", prm.getTot_pre());
		    sess.setAttribute("sdpaid", "0");
		    sess.setAttribute("bal", "0");
		    sess.setAttribute("estpmt", "N");
		    sess.setAttribute("trmsg", temp);
		    
		    sess.setAttribute("cnature", AppData.getCnature());
		    sess.setAttribute("new_cat", AppData.getNew_cat());
		    
		    sess.setAttribute("addr1", em.getAddr1());
		    sess.setAttribute("addr2", em.getAddr2());
		    sess.setAttribute("addr3", em.getAddr3());
		    sess.setAttribute("addr4", em.getAddr4());
		    
		    sess.setAttribute("new_title", AppData.getNewtitle_name());
		    
		    sess.setAttribute("club_ukscnos", AppData.getXclubbedUscnos());
		    sess.setAttribute("club_load", AppData.getTotal_club_load_final());
		    
		}	
		
	}
		
}
