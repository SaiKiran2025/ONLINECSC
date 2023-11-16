package com.tsspdcl.onlinecsc.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tsspdcl.onlinecsc.model.CCAppDataModel;
import com.tsspdcl.onlinecsc.model.EBSModel;
import com.tsspdcl.onlinecsc.model.GaneshAppDataModel;
import com.tsspdcl.onlinecsc.model.PreReqInputModelAdl;
import com.tsspdcl.onlinecsc.model.PreReqModel;

public class InsertGanesh {

	CSCDB db = new CSCDB();
	
	public void insert(GaneshAppDataModel AppData, HttpServletRequest request) throws Exception {
		
		String paddingno="",REGNO="", gst_updated_on = "", status = "";
		CSCDB db = new CSCDB();
				
		try(Connection con = db.getConnection()){
		    
		    DBMethodsComplaints dbmc = new DBMethodsComplaints();
		    System.out.println(AppData.getAdj_uscno());
		    
			int CSCNO = Integer.parseInt( AppData.getCsc_number());
						
			paddingno=dbmc.generateRegno();
			REGNO="CC"+CSCNO+paddingno;

			
		    // PR_NO, PRNO_AT,  DD_NUMBER, DD_DATE, DD_AMOUNT, EMAIL, COMPLAINT_DETAILS, PREV_OFFICE_ID, BANK_NAME, OTH_COM_NAME, REQD_CATEGORY_ID,
		    //CONS_NEW_NAME, NCONNADD1, NCONNADD2, NCONNADD3, NCONNADD4,CLUB_LOAD,oth_cgst,oth_sgst, +dotcond+\
		    String query="INSERT INTO COMPLAINT_DETAILS "
		    			+ " (ID, COMPLAINT_NO, REQD_LOAD, pincode,  OFFICE_PHONE, "
		    			+ "	 MOBILE_NO, NAME, COMPLAINANTS_NAME, ADDRESS_1, ADDRESS_2,"
		    			+ "  AREA_NAME, ADDRESS_4,  seccd, TOT_AMOUNT,  TOBE_PAID_AMT, "
		    			+ "  COMPLAINT_NATUREID, CSCNO, CSCUSERID, ukscno, CREATED_BY, RECORD_STATUS,SCNO,IS_DISPATCHED,ADV_CC_CH," //check condition for phase and ismtrreq
		    			+ "  COMPLAINT_GIVEN_ON, CREATE_DATE, category_id, contracted_load)"
		    			+ "  VALUES(?,?,?,?,?,"
						+ 		 " ?,?,?,?,?,"
						+ 		 " ?,?,?,?,?,"
						+        " ?,?,?,?,?,?,?,?,?,"
						+ 		 " TO_DATE(sysdate,'DD-MM-YY'), TO_DATE(sysdate,'DD-MM-YY'),8,?)";
		    con.setAutoCommit(false);
			
			PreparedStatement ps = con.prepareStatement(query);
	 		ps.setString ( 1  , paddingno );
	 		ps.setString ( 2  , REGNO );
	 		ps.setString ( 3  , AppData.getReqload() );
	 		ps.setString ( 4  , AppData.getPincode() );
	 		ps.setString ( 5  , AppData.getXmobileno() );
	 		
	 		ps.setString ( 6 , AppData.getXmobileno() );	 		
	 		ps.setString ( 7 , AppData.getApplicant_name());
	 		ps.setString ( 8 , AppData.getApplicant_name() );
	 		ps.setString ( 9 , AppData.getAddress1() );
	 		ps.setString ( 10 , AppData.getAddress2()  );
	 		
	 		ps.setString ( 11 , AppData.getAddress3()  );
	 		ps.setString ( 12 , AppData.getAddress4()  );
	 		ps.setString ( 13 , AppData.getSection_code() );	 		
	 		ps.setDouble ( 14 , Double.parseDouble(AppData.getOthamount()) );
	 		ps.setDouble ( 15 , Double.parseDouble(AppData.getOthamount()) );
	 		
	 		ps.setInt	 ( 16 , 71 );
	 		ps.setString ( 17 , AppData.getCsc_number() );
	 		ps.setInt	 ( 18 , 7 );	 		
	 		ps.setString ( 19 , AppData.getAdj_uscno() );
	 		ps.setInt	 ( 20 , 9 );
	 		ps.setString ( 21 , "ACTIVE" ); 
	 		ps.setString ( 22 , "-" ); 
	 		ps.setString ( 23 , "Y" ); 
	 		ps.setDouble ( 24 , Double.parseDouble(AppData.getOthamount()));
	 		ps.setDouble ( 25 , Double.parseDouble(AppData.getReqload()));
	 		
	 		ps.executeUpdate();
	        //String title = "Ganesh Pandals Service Connection ";
	 		String title = "Navaratri Pandals Service Connection ";
	        
	 		DBMethods dbm = new DBMethods();
			HttpSession sess = request.getSession();
			double totamt = Double.parseDouble(AppData.getOthamount());
			//totamt = 1;
	        
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
		    sess.setAttribute("appname", AppData.getApplicant_name());
		    sess.setAttribute("reqld", AppData.getReqload());

		    sess.setAttribute("metric", "Watts");
		    
		    sess.setAttribute("address", AppData.getAddress1() + "," + AppData.getAddress2() + "," + AppData.getAddress3() + "," + AppData.getAddress4());
		    sess.setAttribute("service_charges", AppData.getOthamount());	    
		    sess.setAttribute("totamt", AppData.getOthamount());
		    sess.setAttribute("sdpaid", "0");
		    sess.setAttribute("bal", "0");
		    sess.setAttribute("estpmt", "N");
		    sess.setAttribute("trmsg", temp);
		    
		    sess.setAttribute("cnature", "71");
		    
		}	
		
	}
		
}
