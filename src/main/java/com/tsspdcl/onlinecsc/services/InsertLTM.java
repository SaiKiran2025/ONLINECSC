package com.tsspdcl.onlinecsc.services;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tsspdcl.onlinecsc.model.LTMAppDataModel;

public class InsertLTM {

	public String FILE_DIRECTORY = "E:\\ONLINE_CSC_FILES\\LTMFiles\\";
	CSCDB db = new CSCDB();
	
	SimpleDateFormat sdf1=new SimpleDateFormat("dd/MM/yy");
	java.util.Date dt3= new java.util.Date();
	String dt2=sdf1.format(dt3);
	String year=dt2.substring(6,8);

	public void insert(LTMAppDataModel AppData, HttpServletRequest request) throws Exception {
		String regformat	   = "LTM"+year+AppData.getSection_code();
		String REGID 		   = year+AppData.getSection_code() + nextAPTVal(regformat);
		String REGNO		   = "LTM"+REGID;

		double tot_load 	   = Double.parseDouble(AppData.getDom_load()) + Double.parseDouble(AppData.getCom_load());
		double totamt 	 	   = 29.5*(Integer.parseInt(AppData.getTot_dom_connections())) + 59*(Integer.parseInt(AppData.getTot_com_connections()));		
		double tot_appfee      = 25*(Integer.parseInt(AppData.getTot_dom_connections())) + 50*(Integer.parseInt(AppData.getTot_com_connections()));
		double tot_appfee_cgst = 2.25*(Integer.parseInt(AppData.getTot_dom_connections())) + 4.5*(Integer.parseInt(AppData.getTot_com_connections()));

		String doctype = "";
		if(AppData.getRegdoc().equals("Y"))
			doctype = AppData.getRegdoctype();
		if(AppData.getRegdoc().equals("N"))
			doctype = "Indemnity Bond";
		
		String ibond = "";
		if(AppData.getRegdoc().equals("N")) {
			ibond = "Yes";
		}else {
			ibond = "No";
		}
		
		String occ_flag="",permit_no ="", issued_authority="", issued_date="";
		if(AppData.getOcc_cert().equals("Y"))
		{
			occ_flag = "Y";
			permit_no = AppData.getOcc_no() + "," + AppData.getOcc_issued_by() + "," + formatDate(AppData.getOcc_issue_date()) + "," + AppData.getOcc_flats();	
			issued_authority = "Y";
			issued_date = formatDate(AppData.getOcc_issue_date());
			System.out.println(issued_date);
			System.out.println("formatDate(issued_date)"+formatDate(issued_date));
		}
		else {
			if(AppData.getOcc_certn_btn()!=null) {
				if(AppData.getOcc_certn_btn().equals("yes")) {
					occ_flag = "Y";
					permit_no = "Plot area is less";
				}else {
					occ_flag = "N";
				}
			}			
		}
		if(AppData.getIs_dbh()==null) {
			AppData.setIs_dbh("N");
		}
		System.out.println("AppData.getIs_dbh()"+AppData.getIs_dbh());
		String gst_updated_on = "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date date = new Date(); 
		if(AppData.getGstn_flag().equals("Y")) {
			gst_updated_on = formatter.format(date);
		}
		try(Connection con = db.getConnection()){
		
		String query = "INSERT INTO APARTMENT_CONNECTION (ID, REGISTRATION_NUMBER, APARTMENT_NAME, ADDRESS_1, ADDRESS_2, AREA_NAME, ADDRESS_4, PINCODE, PHONE, MOBILE, "
						+ "DBL_BEDRM_SCM, NO_OF_CONN_REQD, NOF_DOMESTIC_CON, DOMESTIC_LOAD, NOF_COMMERCIAL_CON, COMMERCIAL_LOAD, OCC_CERT, PERMIT_NO, VERIFIED_BY, OCC_CERT_SUB_DATE, "
						+ "COMPANY_NAME, MCH_REG_NO, BUILDER_NAME, BUILDER_PANNO, C_ADDRESS1, C_ADDRESS2, C_AREA_NAME, C_ADDRESS4, C_PHONE, C_MOBILE, "
						+ "P_ADDRESS1, P_ADDRESS2, P_AREA_NAME, P_ADDRESS4, P_PHONE, P_MOBILE, OFFICE_ID, IS_CONST_NEW, IS_FLATSADDED, CREATED_BY, "
						+ "NATUREOFWORK, RECORD_STATUS, SALEDEED_DOC, IMDEMINITYBOND_DOC, MCH_DOC, LOADDETAILS_DOC, CSCNO, APP_FEE, AF_CGST, AF_SGST, "
						+ "REGISTRATION_ON, CREATE_DATE,GSTNFLAG,GSTN,GSTN_UPDT,LAYOUT )"
						+ "VALUES(?,?,?,?,?,?,?,?,?,?,"
						+ 		 "?,?,?,?,?,?,?,?,?,TO_DATE(?,'DD-MM-YY'),"
						+ 		 "?,?,?,?,?,?,?,?,?,?,"
						+        "?,?,?,?,?,?,?,?,?,?,"
						+        "?,?,?,?,?,?,?,?,?,?,"
						+ 		 "TO_DATE(sysdate,'DD-MM-YY'), TO_DATE(sysdate,'DD-MM-YY'),?,?,TO_DATE(?,'DD-MM-YY'),?)";
		con.setAutoCommit(false);
								
		PreparedStatement ps = con.prepareStatement(query);
 		ps.setString ( 1  , REGID );
 		ps.setString ( 2  , REGNO );
 		ps.setString ( 3  , AppData.getApartment_name());
 		ps.setString ( 4  , AppData.getConn_address1() );
 		ps.setString ( 5  , AppData.getConn_address2() );
 		ps.setString ( 6  , AppData.getConn_address3() );
 		ps.setString ( 7  , AppData.getConn_address4() );
 		ps.setString ( 8  , AppData.getConn_pincode() );
 		ps.setString ( 9  , AppData.getComm_phoneno() );
 		ps.setString ( 10 , AppData.getMobileno() );
 		
 		
 		ps.setString ( 11 , AppData.getIs_dbh() );
 		ps.setInt 	 ( 12 , Integer.parseInt(AppData.getTot_req_connections()) );
 		ps.setInt 	 ( 13 , Integer.parseInt(AppData.getTot_dom_connections()) );
 		ps.setDouble ( 14 , Double.parseDouble(AppData.getDom_load()) );
 		ps.setInt 	 ( 15 , Integer.parseInt(AppData.getTot_com_connections()) );
 		ps.setDouble ( 16 , Double.parseDouble(AppData.getCom_load()) ); 		
 		ps.setString ( 17 , occ_flag);
 		ps.setString ( 18 , permit_no );	
 		ps.setString ( 19 , issued_authority );
 		ps.setString ( 20 , issued_date );
 		
 		ps.setString ( 21 , AppData.getCompany_name() );		
 		ps.setString ( 22 , AppData.getMch_regid() );
 		ps.setString ( 23 , AppData.getBuilder_name() );
 		ps.setString ( 24 , AppData.getBuilder_pan_no() );
 		ps.setString ( 25 , AppData.getComm_address1() );
 		ps.setString ( 26 , AppData.getComm_address2() );
 		ps.setString ( 27 , AppData.getComm_address3() );
 		ps.setString ( 28 , AppData.getComm_address4() );
 		ps.setString ( 29 , AppData.getComm_phoneno() );
 		ps.setString ( 30 , AppData.getComm_phoneno() );	
 		
 		ps.setString ( 31 , AppData.getConn_address1() );	
 		ps.setString ( 32 , AppData.getConn_address2() );
 		ps.setString ( 33 , AppData.getConn_address3() );
 		ps.setString ( 34 , AppData.getConn_address4() );
 		ps.setString ( 35 , AppData.getMobileno() );
 		ps.setString ( 36 , AppData.getMobileno() );
 		ps.setString ( 37 , AppData.getSection_code() );
 		ps.setString ( 38 , "NO" );
 		ps.setString ( 39 , "NO" );
 		ps.setInt    ( 40 , 7 );
 		
 		ps.setString ( 41 , AppData.getEst_type() );
 		ps.setString ( 42 , "ACTIVE" );
 		ps.setString ( 43 , AppData.getRegdocno() );
 		ps.setString ( 44 , ibond );
 		ps.setString ( 45 , AppData.getApproval_plan_no() );
 		ps.setString ( 46 , String.valueOf(tot_load) );
 		ps.setString ( 47 , AppData.getCsc_number() );
 		ps.setDouble ( 48 , tot_appfee );
 		ps.setDouble ( 49 , tot_appfee_cgst );
 		ps.setDouble ( 50 , tot_appfee_cgst );
 		
 		ps.setString ( 51 , AppData.getGstn_flag());
 		ps.setString ( 52 , AppData.getGstno());
 		ps.setString ( 53 , gst_updated_on);
 		ps.setString ( 54 , AppData.getReg_type());
 		
 		ps.executeUpdate();
 		
 		//Insert into EXISITING_CONNECTIONS table
 		if((AppData.getScnoext().equals("yes"))) {
			int i=0;
			String arrUksc[] 	= {AppData.getUkscnos1(), AppData.getUkscnos2(), AppData.getUkscnos3(), AppData.getUkscnos4(),AppData.getUkscnos5(),
								   AppData.getUkscnos6(), AppData.getUkscnos7(), AppData.getUkscnos8(), AppData.getUkscnos9(), AppData.getUkscnos10()};
			String arrStatus[]  = {AppData.getBillStatus1(), AppData.getBillStatus2(), AppData.getBillStatus3(), AppData.getBillStatus4(), AppData.getBillStatus5(),
								   AppData.getBillStatus6(), AppData.getBillStatus7(), AppData.getBillStatus8(), AppData.getBillStatus9(), AppData.getBillStatus10()};
			String arrCat[] 	= {AppData.getCategory1(), AppData.getCategory2(), AppData.getCategory3(), AppData.getCategory4(),AppData.getCategory5(),
								   AppData.getCategory6(), AppData.getCategory7(), AppData.getCategory8(), AppData.getCategory9(), AppData.getCategory10()};
			String qryE = "insert into EXIST_CONNECTIONS (ID, REGISTRATION_NUMBER, SCNO, EXIST_CON_STATUS, CATEGORY) values(?,?,?,?,?)";
			PreparedStatement psE = con.prepareStatement(qryE);
			for(i=0; i<Integer.parseInt(AppData.getNoscnos()); i++) {
				psE.setInt(1, i+1);
				psE.setString(2, REGNO);
				psE.setString(3, arrUksc[i] );
				psE.setString(4, arrStatus[i]);
				psE.setString(5, arrCat[i]);
				int j = psE.executeUpdate();
			}
			}
 		
 		//Insert Documents
 		 		
 		/*newIdFileName       = REGNO +"_"+ AppData.getId_proof().getOriginalFilename();
 		newRegistrationProofName = REGNO +"_"+ AppData.getRegistration_proof().getOriginalFilename();
 		newApprovalPlanName = REGNO +"_"+ AppData.getApproval_plan().getOriginalFilename();
    		        		
        File IdFile       = new File(FILE_DIRECTORY + newIdFileName);
        File RegistrationProofFile = new File(FILE_DIRECTORY + newRegistrationProofName);
        File ApprovalPlanFile = new File(FILE_DIRECTORY + newApprovalPlanName);
        
        IdFile.createNewFile();		        	        
        RegistrationProofFile.createNewFile();
        ApprovalPlanFile.createNewFile();
        
        FileOutputStream Fos_IdFile = new FileOutputStream(IdFile);		   
        FileOutputStream Fos_RegistrationProofFile = new FileOutputStream(RegistrationProofFile);
        FileOutputStream Fos_ApprovalPlanFile = new FileOutputStream(ApprovalPlanFile);
        
        //File saving
        Fos_IdFile.write(AppData.getId_proof().getBytes());
        Fos_RegistrationProofFile.write(AppData.getRegistration_proof().getBytes());
        Fos_ApprovalPlanFile.write(AppData.getApproval_plan().getBytes());
        
        //Fos_PhotoFile.close();
        Fos_IdFile.close();		        
        Fos_RegistrationProofFile.close();
        Fos_ApprovalPlanFile.close();
   
        //File reading
        FileInputStream Fis_IdFile       = new FileInputStream(FILE_DIRECTORY + newIdFileName);		        
        FileInputStream Fis_RegistrationProofFile       = new FileInputStream(FILE_DIRECTORY + newRegistrationProofName);
        FileInputStream Fis_ApprovalPlanFile      = new FileInputStream(FILE_DIRECTORY + newApprovalPlanName);*/
		FileStatements fs = new FileStatements(FILE_DIRECTORY, REGNO);
 		FileInputStream Fis_IdFile       			= fs.FileRead(AppData.getId_proof());
 		FileInputStream Fis_RegistrationProofFile   = fs.FileRead(AppData.getRegistration_proof());
 		FileInputStream Fis_ApprovalPlanFile       	= fs.FileRead(AppData.getApproval_plan());
 		
 		String qryDoc = "insert into documents (regno, idproof_type, id_proof, sale_deed_type, sale_deed, APPLICATION_FORM ) values(?,?,?,?,?,?) ";

        PreparedStatement psDoc = con.prepareStatement(qryDoc);
        psDoc.setString(1, REGNO);
        psDoc.setString(2, AppData.getIdproof_type());
        psDoc.setBinaryStream(3, Fis_IdFile, Fis_IdFile.available());
        psDoc.setString(4, doctype);
        psDoc.setBinaryStream(5, Fis_RegistrationProofFile, Fis_RegistrationProofFile.available());
        psDoc.setBinaryStream(6, Fis_ApprovalPlanFile, Fis_ApprovalPlanFile.available());       
        psDoc.executeUpdate();   
        
        FileInputStream Fis_IdFile1       			= fs.FileRead(AppData.getId_proof());
 		FileInputStream Fis_RegistrationProofFile1  = fs.FileRead(AppData.getRegistration_proof());
 		FileInputStream Fis_ApprovalPlanFile1       = fs.FileRead(AppData.getApproval_plan());
        String qryDoc1 = "insert into spddocs.documents (regno, idproof_type, id_proof, sale_deed_type, sale_deed, APPLICATION_FORM ) values(?,?,?,?,?,?) ";

        PreparedStatement psDoc1 = con.prepareStatement(qryDoc1);
        psDoc1.setString(1, REGNO);
        psDoc1.setString(2, AppData.getIdproof_type());
        psDoc1.setBinaryStream(3, Fis_IdFile1, Fis_IdFile1.available());
        psDoc1.setString(4, doctype);
        psDoc1.setBinaryStream(5, Fis_RegistrationProofFile1, Fis_RegistrationProofFile1.available());
        psDoc1.setBinaryStream(6, Fis_ApprovalPlanFile1, Fis_ApprovalPlanFile1.available());       
        psDoc1.executeUpdate(); 
        
        System.out.println("files uploaded for "+REGNO);
 		
		
		DBMethods dbm = new DBMethods();
		HttpSession sess = request.getSession();
        
        String temp = "TSSPDCL|" + REGNO + "|NA|" + totamt + "|NA|NA|NA|INR|NA|R|tsspdcl|NA|NA|F|NEWSCON|NA|NA|NA|NA|NA|NA|http://117.239.151.19:8080/RegPmtReceipt.jsp";
	      String strHash = null;
	      String commonstr = "BObYe64qYnS6";
	      try {
	        strHash = dbm.HmacSHA256(temp, commonstr);
	      } catch (Exception eCRC) {
	        System.out.println(eCRC.toString());
	      } 
	      temp = String.valueOf(temp) + "|" + strHash;
	      sess.setAttribute("fileuploadSuccess", " Documents have been Uploaded Successfully.");
	      sess.setAttribute("regno", REGNO);
	      sess.setAttribute("appname", AppData.getApartment_name());
	      sess.setAttribute("category", "");
	      sess.setAttribute("contld",  "");
	      sess.setAttribute("contld", (new StringBuilder(String.valueOf(tot_load))).toString());
	      sess.setAttribute("metric", "Watts");
	      sess.setAttribute("appfee", (new StringBuilder(String.valueOf(tot_appfee))).toString());
	      sess.setAttribute("devchg", "0");
	      sess.setAttribute("secdp",  "0");
	      sess.setAttribute("totamt", (new StringBuilder(String.valueOf(totamt))).toString());
	      sess.setAttribute("gst",    (new StringBuilder(String.valueOf(2*tot_appfee_cgst))).toString());
	      sess.setAttribute("sdpaid", "0");
	      sess.setAttribute("bal",    "0");
	      sess.setAttribute("estpmt", "N");
	      sess.setAttribute("trmsg",  temp);
	      //Exclusive for LTM
	      sess.setAttribute("dom_nos", AppData.getTot_dom_connections());
	      sess.setAttribute("dom_load", AppData.getDom_load());
	      sess.setAttribute("com_nos", AppData.getTot_com_connections());
	      sess.setAttribute("com_load", AppData.getCom_load());
	      sess.setAttribute("tot_nos", AppData.getTot_req_connections());
	      sess.setAttribute("type", AppData.getReg_type());

		}catch(Exception e) {
	    	throw e;
			}
	
	}
	
	
	 public synchronized String nextAPTVal(String pstrSequenceName) {
		 String SEQ = "", ipadding="";
		 try(Connection con = db.getConnection()){
			    String querySEQ= "select nvl(max(substr(registration_number,9,4)),0)+1 from apartment_connection where registration_number like ? ";
				PreparedStatement psSEQ = con.prepareStatement(querySEQ);		
				psSEQ.setString(1, pstrSequenceName+"%");
				ResultSet rs=psSEQ.executeQuery();
				if(rs.next()) {
					SEQ =  rs.getString(1);
					
					for(int i=1;i<=4-SEQ.length();i++)
					{
						ipadding+="0";
					}
					System.out.println(ipadding+SEQ);
				}
		    } catch (Exception e) {
		      System.out.print("\n SQLException in SequenceNumber\n" + e.getMessage());
		      return null;
		    } 
		 return ipadding+SEQ;
	 }
	 
	 public String formatDate(String date) {
			if(date != null) {
				String dateF = date.substring(8);
				String monthF = date.substring(5,7);
				String yearF = date.substring(0,4);
				String fdate = dateF + "-" + monthF + "-" + yearF ;
				return fdate;
			}else {
				return "";
			}
		}
		
}
