package com.tsspdcl.onlinecsc.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tsspdcl.onlinecsc.model.ApplicationDataModel;

public class UpdateNR {

	public String FILE_DIRECTORY = "E:\\NRFiles\\";

	public void update(ApplicationDataModel AppData, HttpServletRequest request) {
		
		java.util.Date now = new java.util.Date();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		String s1 = df.format(now);
		String [] curryear=s1.split("/");
		
		int REGSLNO=0;
		String regno1="",paddingno="",REGNO="";
		CSCDB db = new CSCDB();
		
		//File upload initialization
		String newIdFileName = "", newPremisesProofName = "", newPhotoName = "", newLocationPhotoName = "";
		
		AppData.setEst_type("Turn Key");
		
		String gst_updated_on = "";
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    Date date = new Date();  
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
				
		try(Connection con = db.getConnection()){
			
			int CSCNO = Integer.parseInt( AppData.getCsc_number());
			
			/*String querySEQ="SELECT REGNO_GEN_SEQ.NEXTVAL FROM DUAL";
			PreparedStatement psSEQ = con.prepareStatement(querySEQ);		
			ResultSet rsSEQ=psSEQ.executeQuery();			
			if(rsSEQ.next())
			{
				REGSLNO=rsSEQ.getInt(1);
				regno1=""+REGSLNO;
		    }			
			paddingno=curryear[2]+leftPadding(regno1,6);
		    REGNO="NR"+CSCNO+paddingno;*/
			REGNO = AppData.getRegno();
			
			String query = "update newconnection_register set TYPE=?,APPLICANT_NAME=?,CONNADD1=?,CONNADD2=?,CONNADD3=?,CONNADD4=?,"
					+ "PINCODE=?,PHONENO=?,MOBILENO=?,EMAILID=?,COMMADD1=?,COMMADD2=?,COMMADD3=?,COMMADD4=?,APPLICANT_TYPE=?,"
					+ "LOCATION_TYPE=?,SOCIALGROUP=?,CONTRACTLD=?,CONNECTLD=?,DEPTTYPE=?,PHASE=?,AADHAR_NO=?,EST_TYPE=?,FATHER_NAME=?,CLEINT_IP_REL=?,"
					+ "GSTNFLAG=?,GSTN=?,GSTN_UPDT=?,IDPROOF_TYPE=?,IDPROOF_NO=?,GENDER=?,REGISTERED_DOC=?,DOC_TYPE=?,DOC_NO=?,DOC_DATE=?,"
					+ "SEZ=?,ADJACENTUSCNO=?,AREANAME=?,AREACD=?,SECCD=?,EROCD=?,NRCSCNO=?,AREAGROUP=?,"
					+ "CATCODE=?,SUBCATCD=?,CSCUSERID=?,METRIC=?,IBOND=?,PURPOSE=?,REGNO_STATUS=?,"
					+ "GOVTCD=?,HODCD=?,TEMP_FROMDT=?,TEMP_TODT=?,COMMN_SERVICE=?,MUNCIPALCD=?,EQPOINTS=?,SERVTYPECD=?" //newly added
					+ "where NRREGNO = ? ";
			
			con.setAutoCommit(false);
			
			PreparedStatement psNR = con.prepareStatement(query);
	 		psNR.setString ( 1 , type);
	 		psNR.setString ( 2 , AppData.getApplicant_name() );
	 		psNR.setString ( 3 , AppData.getConn_address1() );
	 		psNR.setString ( 4 , AppData.getConn_address2() );
	 		psNR.setString ( 5 , AppData.getConn_address3() );
	 		psNR.setString ( 6 , AppData.getConn_address4() );
	 		
	 		psNR.setInt    ( 7 , Integer.parseInt(AppData.getConn_pincode()) );
	 		//psNR.setLong   ( 8 , Long.parseLong(AppData.getPhoneno()) );
	 		//psNR.setLong   ( 9 , Long.parseLong(AppData.getPhoneno()) ); //check 2 mobile numbers requirement
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
	 		psNR.setString ( 35 , AppData.getRegdocdate() ); //Date format during input is not set  in jsp
	 		
	 		psNR.setString ( 36 , AppData.getSez() );	 		
	 		psNR.setInt    ( 37 , Integer.parseInt(AppData.getAdj_uscno()) );
	 		psNR.setString ( 38 , AppData.getArea_name() );
	 		psNR.setString ( 39 , AppData.getArea_code() );
	 		psNR.setInt    ( 40 , Integer.parseInt(AppData.getSection_code()) );
	 		psNR.setInt    ( 41 , Integer.parseInt(AppData.getEro_code()) );
	 		psNR.setInt    ( 42 , Integer.parseInt(AppData.getCsc_number()) );
	 		psNR.setString ( 43 , AppData.getArea_group() );
	 		
	 		psNR.setInt    ( 44 , Integer.parseInt(AppData.getCategory()) );
	 		psNR.setInt    ( 45 , Integer.parseInt(AppData.getSub_category()) );
	 		psNR.setInt    ( 46 , 7 );
	 		psNR.setString ( 47 , "Watts" );
	 		psNR.setString ( 48 , ibond );
	 		psNR.setString ( 49 , "New Connection" );
	 		psNR.setString ( 50 , "ACTIVE" );
	 		
	 		psNR.setString ( 51 , AppData.getGov_code() );	 		
	 		psNR.setString ( 52 , AppData.getHod_code());
	 		psNR.setString ( 53 , AppData.getTemp_fmdt() );
	 		psNR.setString ( 54 , AppData.getTemp_todt() );
	 		psNR.setString ( 55 , AppData.getComnserv() );
	 		psNR.setString ( 56 , AppData.getMunicipality() );
	 		psNR.setString ( 57 , AppData.getEqpoints()) ;
	 		psNR.setString ( 58 , AppData.getSupply_nature() );
	 		psNR.setString ( 59 , REGNO );
	
	 		psNR.executeUpdate();
	 		
	 		System.out.println(REGNO);
	 		
	 		//Insert Documents
	 		String qryDoc = "update documents set idproof_type=?, id_proof=?, sale_deed_type=?, sale_deed=?, photo=?, loc_photo=? "
	 				+ "where regno = ? ";
	 		con.setAutoCommit(false);
	 		
        	newPhotoName = REGNO +"_"+ AppData.getApplicant_photo().getOriginalFilename();
	 		newIdFileName       = REGNO +"_"+ AppData.getId_proof().getOriginalFilename();
	 		newPremisesProofName = REGNO +"_"+ AppData.getPremises_proof().getOriginalFilename();
        	newLocationPhotoName = REGNO +"_"+ AppData.getLocation_photo().getOriginalFilename();
        		        		
	        File PhotoFile = new File(FILE_DIRECTORY + newPhotoName);
	        File IdFile       = new File(FILE_DIRECTORY + newIdFileName);
	        File PremisesProofFile = new File(FILE_DIRECTORY + newPremisesProofName);	       
	        File LocationPhotoFile = new File(FILE_DIRECTORY + newLocationPhotoName);
	        
	        PhotoFile.createNewFile();
	        IdFile.createNewFile();		        	        
	        PremisesProofFile.createNewFile();
	        LocationPhotoFile.createNewFile();
	        
	        FileOutputStream Fos_PhotoFile = new FileOutputStream(PhotoFile);
	        FileOutputStream Fos_IdFile = new FileOutputStream(IdFile);		   
	        FileOutputStream Fos_PremisesFile= new FileOutputStream(PremisesProofFile);
	        FileOutputStream Fos_LocationPhotoFile = new FileOutputStream(LocationPhotoFile);
	        
	        //File saving
	        Fos_PhotoFile.write(AppData.getApplicant_photo().getBytes());
	        Fos_IdFile.write(AppData.getId_proof().getBytes());
	        Fos_PremisesFile.write(AppData.getPremises_proof().getBytes());
	        Fos_LocationPhotoFile.write(AppData.getLocation_photo().getBytes());	
	        
	        Fos_PhotoFile.close();
	        Fos_IdFile.close();		        
	        Fos_PremisesFile.close();
	        Fos_LocationPhotoFile.close();
       
	        //File reading
	        FileInputStream Fis_PhotoFile       = new FileInputStream(FILE_DIRECTORY + newPhotoName);
	        FileInputStream Fis_IdFile       = new FileInputStream(FILE_DIRECTORY + newIdFileName);		        
	        FileInputStream Fis_PremisesProofFile       = new FileInputStream(FILE_DIRECTORY + newPremisesProofName);
	        FileInputStream Fis_LocationPhotoFile       = new FileInputStream(FILE_DIRECTORY + newLocationPhotoName);
	        
	        PreparedStatement psDoc = con.prepareStatement(qryDoc);
	        psDoc.setString(1, AppData.getIdproof_type());
	        psDoc.setBinaryStream(2, Fis_IdFile, Fis_IdFile.available());
	        psDoc.setString(3, doctype);
	        psDoc.setBinaryStream(4, Fis_PremisesProofFile, Fis_PremisesProofFile.available());
	        psDoc.setBinaryStream(5, Fis_PhotoFile, Fis_PhotoFile.available());
	        psDoc.setBinaryStream(6, Fis_LocationPhotoFile, Fis_LocationPhotoFile.available());
	        psDoc.setString(7 , REGNO );
	        
	        psDoc.executeUpdate();   
	        
	        System.out.println("files uploaded for "+REGNO);
	        
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
				if(AppData.getCategory().equals("3") || AppData.getCategory().equals("5")) {
					load = Double.parseDouble(AppData.getConn_load());
				}else {
					load = Math.ceil(0.001*Double.parseDouble(AppData.getConn_load()));
				}
				appfee = rs.getDouble(1);
				gst = rs.getDouble(2);
				if(AppData.getRegdoc().equals("N"))
					secdp = load*3*rs.getDouble(3);
				else
					secdp = rs.getDouble(3);
				
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
			
			psU.executeUpdate();
			
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
		      sess.setAttribute("metric", "Watts");
		      sess.setAttribute("appfee", (new StringBuilder(String.valueOf(appfee))).toString());
		      sess.setAttribute("devchg", (new StringBuilder(String.valueOf(devchg))).toString());
		      sess.setAttribute("secdp", (new StringBuilder(String.valueOf(secdp))).toString());
		      sess.setAttribute("totamt", (new StringBuilder(String.valueOf(totamt))).toString());
		      sess.setAttribute("gst", (new StringBuilder(String.valueOf(gst))).toString());
		      sess.setAttribute("sdpaid", "0");
		      sess.setAttribute("bal", "0");
		      sess.setAttribute("estpmt", "N");
		      sess.setAttribute("trmsg", temp);

		}
		catch(Exception e) {
	    	e.printStackTrace();
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
}

