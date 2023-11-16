package com.tsspdcl.onlinecsc.services;


import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tsspdcl.onlinecsc.model.ApplicationDataModel;

public class InsertNR {
	
	public String FILE_DIRECTORY = "E:\\ONLINE_CSC_FILES\\NRFiles\\";

	public void insert(ApplicationDataModel AppData, HttpServletRequest request) throws Exception {
		System.out.println(AppData);
		
		java.util.Date now = new java.util.Date();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		String s1 = df.format(now);
		String [] curryear=s1.split("/");
		
		int REGSLNO=0;
		String regno1="",paddingno="",REGNO="";
		CSCDB db = new CSCDB();		
		
		AppData.setEst_type("Turn Key");
		if(AppData.getCategory().equals("5"))
			AppData.setEst_type("Department");
		
		String gst_updated_on = "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date date = new Date(); 
	    if(!AppData.getCategory().equals("1"))
		if(AppData.getGstn_flag().equals("Y")) {
			gst_updated_on = formatter.format(date);
		}
		
		String ibond = "";		
		if(AppData.getRegdoc().equals("Y"))
			ibond="No";
		if(AppData.getRegdoc().equals("N"))
			ibond="Yes";
		
		String doctype = "";
		if(AppData.getRegdoc().equals("Y"))
			doctype = AppData.getRegdoctype();
		if(AppData.getRegdoc().equals("N"))
			doctype = "Indemnity Bond";
		
		String type = "";
		if(AppData.getExtension().equals("Y"))
			type = "Y";
		
		if(AppData.getAdj_uscno() == null || AppData.getAdj_uscno().equals("")) {
			AppData.setAdj_uscno("0");
		}
		//set metrics
		String metrics = "", a_metrics="";
		int a_load=0;
		if(AppData.getCategory().equals("3") || AppData.getCategory().equals("4") || AppData.getCategory().equals("5")||
			( AppData.getCategory().equals("6")&& (AppData.getSub_category().equals("5")|| AppData.getSub_category().equals("6") || AppData.getSub_category().equals("9") || AppData.getSub_category().equals("12")))) {
			metrics = "HP";
			a_metrics="HP";
			a_load=Integer.parseInt(AppData.getConn_load());
			}
		else {
			metrics = "Watts";
			a_metrics="KW";
			a_load=Integer.parseInt(AppData.getConn_load())/1000;
		}
		
		try(Connection con = db.getConnection()){
			int CSCNO = Integer.parseInt( AppData.getCsc_number());
			
			String querySEQ="SELECT REGNO_GEN_SEQ.NEXTVAL FROM DUAL";
			PreparedStatement psSEQ = con.prepareStatement(querySEQ);		
			ResultSet rsSEQ=psSEQ.executeQuery();			
			if(rsSEQ.next())
			{
				REGSLNO=rsSEQ.getInt(1);
				regno1=""+REGSLNO;
		    }			
			paddingno=curryear[2]+leftPadding(regno1,6);
		    REGNO="NR"+CSCNO+paddingno;
			
			String query = "insert into newconnection_register (NRREGNO,APPLICANT_NAME,CONNADD1,CONNADD2,CONNADD3,CONNADD4,"
					+ "PINCODE,PHONENO,MOBILENO,EMAILID,COMMADD1,COMMADD2,COMMADD3,COMMADD4,APPLICANT_TYPE,"
					+ "LOCATION_TYPE,SOCIALGROUP,CONTRACTLD,CONNECTLD,DEPTTYPE,PHASE,AADHAR_NO,EST_TYPE,FATHER_NAME,CLEINT_IP_REL,"
					+ "GSTNFLAG,GSTN,GSTN_UPDT,IDPROOF_TYPE,IDPROOF_NO,GENDER,REGISTERED_DOC,DOC_TYPE,DOC_NO,DOC_DATE,"
					+ "SEZ,ADJACENTUSCNO,AREANAME,AREACD,SECCD,EROCD,NRCSCNO,AREAGROUP,"
					+ "NRREGID,CATCODE,SUBCATCD,CSCUSERID,METRIC,IBOND,PURPOSE,REGNO_STATUS,"
					+ "GOVTCD,HODCD,TEMP_FROMDT,TEMP_TODT,COMMN_SERVICE,MUNCIPALCD,EQPOINTS,SERVTYPECD,TYPE,NRREGDATE)" //newly added
					+ " values(?,?,?,?,?,?,"
						+ "?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,TO_DATE(?,'DD-MM-YY'),?,?,?,?,?,?,TO_DATE(?,'DD-MM-YY'),"
					+ "?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,"
					+ "?,?,TO_DATE(?,'DD-MM-YY'),TO_DATE(?,'DD-MM-YY'),?,?,?,?,?,TO_DATE(sysdate,'DD-MM-YY'))";
			
			con.setAutoCommit(false);
			
			PreparedStatement psNR = con.prepareStatement(query);
	 		psNR.setString ( 1 , REGNO );
	 		psNR.setString ( 2 , AppData.getApplicant_name() );
	 		psNR.setString ( 3 , AppData.getConn_address1() );
	 		psNR.setString ( 4 , AppData.getConn_address2() );
	 		psNR.setString ( 5 , AppData.getConn_address3() );
	 		psNR.setString ( 6 , AppData.getConn_address4() );
	 		
	 		psNR.setInt    ( 7 , Integer.parseInt(AppData.getConn_pincode()) );
	 		psNR.setLong   ( 8 , Long.parseLong(AppData.getComm_phoneno()) );
	 		psNR.setLong   ( 9 , Long.parseLong(AppData.getMobileno()) ); //check 2 mobile numbers requirement
	 		psNR.setString ( 10 , AppData.getEmail() );
	 		psNR.setString ( 11 , AppData.getComm_address1() );
	 		psNR.setString ( 12 , AppData.getComm_address2() );
	 		psNR.setString ( 13 , AppData.getComm_address3() );
	 		psNR.setString ( 14 , AppData.getComm_address4() );
	 		psNR.setString ( 15 , AppData.getCons_status());
	 		
	 		psNR.setInt    ( 16 , Integer.parseInt(AppData.getLocation_type()));     		
	 		psNR.setString ( 17 , AppData.getSocial_grp() );
	 		psNR.setDouble ( 18 , Double.parseDouble(AppData.getConn_load()));
	 		psNR.setDouble ( 19 , Double.parseDouble(AppData.getConn_load()) );
	 		psNR.setString ( 20 , AppData.getServ_type() );
	 		psNR.setInt    ( 21 , Integer.parseInt(AppData.getPhase()) );
	 		psNR.setString ( 22 , AppData.getAadhar_no() ); //will update as null
	 		psNR.setString ( 23 , AppData.getEst_type() ); //default Turn Key
	 		psNR.setString ( 24 , AppData.getFather_name() );
	 		psNR.setString ( 25 , AppData.getClient_ip_rel() );// null
	 		
	 		psNR.setString ( 26 , AppData.getGstn_flag() );    		
	 		psNR.setString ( 27 , AppData.getGstno() );
	 		psNR.setString ( 28 , gst_updated_on );
	 		psNR.setString ( 29 , AppData.getIdproof_type() );
	 		psNR.setString ( 30 , AppData.getIdproof_no() );
	 		psNR.setString ( 31 , AppData.getGender() );
	 		psNR.setString ( 32 , AppData.getRegdoc());
	 		psNR.setString ( 33 , doctype );
	 		psNR.setString ( 34 , AppData.getRegdocno() );
	 		psNR.setString ( 35 , formatDate(AppData.getRegdocdate()) ); //Date format during input is not set  in jsp
	 		
	 		psNR.setString ( 36 , AppData.getSez() );	 		
	 		psNR.setInt    ( 37 , Integer.parseInt(AppData.getAdj_uscno()) );
	 		psNR.setString ( 38 , AppData.getArea_name() );
	 		psNR.setString ( 39 , AppData.getArea_code() );
	 		psNR.setInt    ( 40 , Integer.parseInt(AppData.getSection_code()) );
	 		psNR.setInt    ( 41 , Integer.parseInt(AppData.getEro_code()) );
	 		psNR.setInt    ( 42 , Integer.parseInt(AppData.getCsc_number()) );
	 		psNR.setString ( 43 , AppData.getArea_group() );
	 		
	 		psNR.setLong   ( 44 , Long.parseLong(paddingno) );
	 		psNR.setInt    ( 45 , Integer.parseInt(AppData.getCategory()) );
	 		psNR.setInt    ( 46 , Integer.parseInt(AppData.getSub_category()) );
	 		psNR.setInt    ( 47 , 7 );
	 		psNR.setString ( 48 , metrics );
	 		psNR.setString ( 49 , ibond );
	 		psNR.setString ( 50 , "New Connection" );
	 		psNR.setString ( 51 , "ACTIVE" );
	 		
	 		psNR.setString ( 52 , AppData.getGov_code() );	 		
	 		psNR.setString ( 53 , AppData.getHod_code());
	 		psNR.setString ( 54 , formatDate(AppData.getTemp_fmdt()) );
	 		psNR.setString ( 55 , formatDate(AppData.getTemp_todt()) );
	 		psNR.setString ( 56 , AppData.getComnserv() );
	 		psNR.setString ( 57 , AppData.getMunicipality() );
	 		psNR.setString ( 58 , AppData.getEqpoints()) ;
	 		psNR.setString ( 59 , AppData.getSupply_nature() );
	 		psNR.setString ( 60 , type );
	
	 		psNR.executeUpdate();
	 		
	 		//System.out.println(REGNO);
	 		
	 		//Insert Documents
	 		
	 		/*newIdFileName       = REGNO +"_"+ AppData.getId_proof().getOriginalFilename();
	 		newRegistrationProofName = REGNO +"_"+ AppData.getRegistration_proof().getOriginalFilename();
        		        		
	        File IdFile       = new File(FILE_DIRECTORY + newIdFileName);
	        File RegistrationProofFile = new File(FILE_DIRECTORY + newRegistrationProofName);
	        
	        IdFile.createNewFile();		        	        
	        RegistrationProofFile.createNewFile();
	        

	        
	        FileOutputStream Fos_IdFile = new FileOutputStream(IdFile);		   
	        FileOutputStream Fos_RegistrationProofFile = new FileOutputStream(RegistrationProofFile);
	        
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
	        psDoc.setString(4, doctype);
	        psDoc.setBinaryStream(5, Fis_RegistrationProofFile, Fis_RegistrationProofFile.available());	        
	        psDoc.executeUpdate();  
	        	        
	        String qryDoc1 = "insert into spddocs.documents (regno, idproof_type, id_proof, sale_deed_type, sale_deed ) values(?,?,?,?,?) ";
	 		FileInputStream Fis_IdFile1       			= fs.FileRead(AppData.getId_proof());
	 		FileInputStream Fis_RegistrationProofFile1   = fs.FileRead(AppData.getRegistration_proof());
	 		
	        PreparedStatement psDoc1 = con.prepareStatement(qryDoc1);
	        psDoc1.setString(1, REGNO);
	        psDoc1.setString(2, AppData.getIdproof_type());
	        psDoc1.setBinaryStream(3, Fis_IdFile1, Fis_IdFile1.available());
	        psDoc1.setString(4, doctype);
	        psDoc1.setBinaryStream(5, Fis_RegistrationProofFile1, Fis_RegistrationProofFile1.available());	        
	        int z = psDoc1.executeUpdate();
	        
	        System.out.println(z + " files uploaded for "+REGNO);
	        
	        //Setting required payments
	        double appfee = 0.0, gst = 0.0, devchg = 0.0, secdp= 0.0, totamt=0.0;
	        double develop_chrgs = 0.0, develop_gst = 0.0;
	        double load = 0.00;
	        String qryPaymt = "SELECT APP_FEE, APP_FEE_GST, SEC_DEP, DEV_CH, DEV_CH_GST FROM INITIAL_PAYMT WHERE CAT = ? and "
					+ "	SUBCAT = ? ";
	        PreparedStatement psPaymt = con.prepareStatement(qryPaymt);	
	        psPaymt.setString(1, AppData.getCategory());
	        psPaymt.setString(2, AppData.getSub_category());
			ResultSet rs = psPaymt.executeQuery();	
			if(rs.next()) {
				if(AppData.getCategory().equals("3") || AppData.getCategory().equals("4") || AppData.getCategory().equals("5") || ( AppData.getCategory().equals("6") & ( AppData.getSub_category().equals("5") || AppData.getSub_category().equals("6") || AppData.getSub_category().equals("9") || AppData.getSub_category().equals("12") ))) {
					load = Double.parseDouble(AppData.getConn_load());
				}else {
					load = Math.ceil(0.001*Double.parseDouble(AppData.getConn_load()));
				}
				appfee = rs.getDouble(1);
				gst = rs.getDouble(2);
				if(AppData.getRegdoc().equals("N"))
					secdp = load*3*rs.getDouble(3);
				else
					secdp = load*rs.getDouble(3);
				
				devchg = load*rs.getDouble(4) + load*rs.getDouble(5);
				develop_chrgs = load*rs.getDouble(4);
				develop_gst  = load*rs.getDouble(5);
				
				totamt = appfee + gst + devchg + secdp ;				
			}
			
			String cat = AppData.getCategory();
			if(AppData.getExtension().equals("Y") & !cat.equals("5")) {
				secdp = 0.00; devchg = 0.00; totamt = appfee + gst; develop_chrgs = 0.00; develop_gst = 0.00; 
			}
						
			if(cat.equals("8") || cat.equals("9") ) {
				secdp = 0.00; devchg = 0.00; totamt = appfee + gst; develop_chrgs = 0.00; develop_gst = 0.00; 
			}
			
			//Updating split in NR Table
			String qryU = "update newconnection_register set APPL_FEES = ?, APPL_FEES_GST = ?, SECURITYDEP = ?,DEVLOPCHRGS = ?, "
							+ "DCGST = ?, DDAMOUNT = ? where NRREGNO = ? ";
			con.setAutoCommit(false);			
			PreparedStatement psU = con.prepareStatement(qryU);
			psU.setDouble( 1 , appfee );
			psU.setDouble( 2 , gst );
			psU.setDouble( 3 , secdp );
			psU.setDouble( 4 , develop_chrgs );
			psU.setDouble( 5 , develop_gst );
			psU.setDouble( 6 , totamt );
			psU.setString( 7 , REGNO );
			
			//appfee = 1;
			//gst = 0.18;
			//totamt = 1.18 ; 
			
			psU.executeUpdate();
			
			if((AppData.getScnoext().equals("yes"))) {
			int i=0;
			String uscno1 = AppData.getUkscnos1(); String uscno2 = AppData.getUkscnos2(); String uscno3 = AppData.getUkscnos3(); String uscno4 = AppData.getUkscnos4();
			String arrUksc[] = {uscno1, uscno2, uscno3, uscno4};
			for(int j=0; j < arrUksc.length ; j++) {
			System.out.println(arrUksc[j]);
			}
			String status1 = AppData.getBillStatus1(); String status2 = AppData.getBillStatus2(); String status3 = AppData.getBillStatus3(); String status4 = AppData.getBillStatus4();
			String arrStatus[] = {status1, status2, status3, status4};
			String cat1 = AppData.getCategory1(); String cat2 = AppData.getCategory2(); String cat3 = AppData.getCategory3(); String cat4 = AppData.getCategory4();
			String arrCat[] = {cat1, cat2, cat3, cat4};
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
		      sess.setAttribute("appname", AppData.getApplicant_name());
		      sess.setAttribute("category", AppData.getCategory());
		      sess.setAttribute("contld", AppData.getConn_load());
		     // sess.setAttribute("contld", (new StringBuilder(String.valueOf(a_load))).toString());
		      sess.setAttribute("metric", metrics);
		      sess.setAttribute("appfee", (new StringBuilder(String.valueOf(appfee))).toString());
		      sess.setAttribute("devchg", (new StringBuilder(String.valueOf(devchg))).toString());
		      sess.setAttribute("secdp", (new StringBuilder(String.valueOf(secdp))).toString());
		      sess.setAttribute("totamt", (new StringBuilder(String.valueOf(totamt))).toString());
		      sess.setAttribute("gst", (new StringBuilder(String.valueOf(gst))).toString());
		      sess.setAttribute("sdpaid", "0");
		      sess.setAttribute("bal", "0");
		      sess.setAttribute("estpmt", "N");
		      sess.setAttribute("trmsg", temp);
		      sess.setAttribute("paytype", AppData.getPaytype());
		      
		      if(AppData.getPaytype().equals("meeseva"))
		      {
		    	  sess.setAttribute("requestid", AppData.getRequestid()); 
		    	  sess.setAttribute("staffcode", AppData.getStaffcode()); 
		    	  sess.setAttribute("msg", AppData.getMsg());
			      sess.setAttribute("encmsg", AppData.getEncmsg());
		      }
		      sess.setAttribute("mobileno", AppData.getMobileno());

		}
		catch(Exception e) {
	    	throw e;
			}

	}
	
	

	
	public String leftPadding(String str1,int x){
		String ipadding="";
		for(int i=1;i<=x-str1.length();i++)
		{
			ipadding+="0";
		}

		return (ipadding+str1);
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
