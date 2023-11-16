package com.tsspdcl.onlinecsc.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tsspdcl.onlinecsc.model.ALAppDataModel;
import com.tsspdcl.onlinecsc.model.EBSModel;

public class InsertAL {

	CSCDB db = new CSCDB();
	java.util.Date now = new java.util.Date();
	DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
	String s1 = df.format(now);
	String [] curryear=s1.split("/");
	
	public void insert(ALAppDataModel AppData, HttpServletRequest request) throws Exception {
		
		int REGSLNO=0;
		String regno1="",paddingno="",REGNO="";
		CSCDB db = new CSCDB();
		
		try(Connection con = db.getConnection()){
		    
		    EBSMethods ebsAll = new EBSMethods();
		    EBSModel em = new EBSModel();
		    System.out.println(AppData.getXukscno());
		    em = ebsAll.getAllConsumerData(Integer.parseInt(AppData.getXukscno()));
		    
			int CSCNO = Integer.parseInt( em.getCscno());
			
			String querySEQ="SELECT COMPL_GEN_SEQ.NEXTVAL FROM DUAL";
			PreparedStatement psSEQ = con.prepareStatement(querySEQ);		
			ResultSet rsSEQ=psSEQ.executeQuery();			
			if(rsSEQ.next())
			{
				REGSLNO=rsSEQ.getInt(1);
				regno1=""+REGSLNO;
		    }			
			paddingno=curryear[2]+leftPadding(regno1,6);
		    REGNO="AL"+CSCNO+paddingno;
		    
		    //payment fetch start
		    double appfee = 0.0, appfee_gst = 0.0, dc_tot = 0.0, secdp= 0.0, totamt=0.0;
	        double dc = 0.0, dcgst = 0.0;
	        double load = 0.00;
	        String qryPaymt = " SELECT APP_FEE, APP_FEE_GST, SEC_DEP, DEV_CH, DEV_CH_GST FROM INITIAL_PAYMT WHERE CAT = ? "
							+ "	and SUBCAT = ? ";
	        PreparedStatement psPaymt = con.prepareStatement(qryPaymt);	
	        psPaymt.setString(1, em.getCatcode());
	        psPaymt.setString(2, em.getSubcatcode());
			ResultSet rs = psPaymt.executeQuery();	
			if(rs.next()) {
				if(em.getCatcode().equals("3") || em.getCatcode().equals("5") || ( em.getCatcode().equals("6") & ( em.getSubcatcode().equals("5") || em.getSubcatcode().equals("6") || em.getSubcatcode().equals("9") || em.getSubcatcode().equals("12") ))) {
					load = Double.parseDouble(AppData.getAdditional_load());
				}else {
					load = Math.ceil(0.001*Double.parseDouble(AppData.getAdditional_load()));
				}
				appfee = rs.getDouble(1);
				appfee_gst = rs.getDouble(2);
				secdp = load*rs.getDouble(3);
				
				dc = load*rs.getDouble(4);
				dcgst  = load*rs.getDouble(5);
				
				totamt = appfee + appfee_gst + secdp + dc +  dcgst ;	
				
				if(AppData.getExtension().equals("Y") ) {
					secdp = 0.00; dc_tot = 0.00; totamt = appfee + appfee_gst; dc = 0.00; dcgst = 0.00; 
				}
			}
			 //payment fetch end
		    
			String gst_updated_on = "";
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
		    Date date = new Date(); 
			if(AppData.getGstn_flag().equals("Y")) {
				gst_updated_on = formatter.format(date);
			}
			
			double existing_load =0.0;
	 		String metrics = "";
			if(em.getCatcode().equals("3") || em.getCatcode().equals("5")||
			( em.getCatcode().equals("6")&& (em.getSubcatcode().equals("5")|| em.getSubcatcode().equals("6") || em.getSubcatcode().equals("9") || em.getSubcatcode().equals("12"))))
				{
					existing_load = em.getLoad();
					metrics = "HP";
				}else {
					existing_load = 1000 * em.getLoad();
					metrics = "Watts";
				}
			
		    // PR_NO, PRNO_AT,  DD_NUMBER, DD_DATE, DD_AMOUNT, EMAIL, COMPLAINT_DETAILS, PREV_OFFICE_ID, BANK_NAME, OTH_COM_NAME, REQD_CATEGORY_ID,
		    //CONS_NEW_NAME, NCONNADD1, NCONNADD2, NCONNADD3, NCONNADD4,CLUB_LOAD,oth_cgst,oth_sgst, +dotcond+\
		    String query="INSERT INTO COMPLAINT_DETAILS "
		    			+ " (ID, COMPLAINT_NO, REQD_LOAD, pincode, gstnflag, GSTN, TYPE, EST_TYPE, OFFICE_PHONE, MOBILE_NO, "
		    			+ " SCNO, NAME, COMPLAINANTS_NAME, ADDRESS_1, ADDRESS_2, AREA_NAME, ADDRESS_4, CATEGORY_ID, CONTRACTED_LOAD, seccd, "
		    			+ " TOT_AMOUNT, APP_FEE, appl_fees_gst, SECURITY_DEPOSIT, DEV_CHRGS, DCGST, TOBE_PAID_AMT, COMPLAINT_NATUREID, CSCNO, CSCUSERID, "
		    			+ " TOTAL_LAOD, phase, ISMTRREQ, ukscno, EXIST_SD, IS_DISPATCHED, STATUS, CREATED_BY, RECORD_STATUS," //check condition for phase and ismtrreq
		    			+ " GSTN_UPDT, COMPLAINT_GIVEN_ON, CREATE_DATE)"
		    			+ " VALUES(?,?,?,?,?,?,?,?,?,?,"
						+ 		 " ?,?,?,?,?,?,?,?,?,?,"
						+ 		 " ?,?,?,?,?,?,?,?,?,?,"
						+        " ?,?,?,?,?,?,?,?,?,"
						+ 		 " TO_DATE(?,'DD-MM-YY'), TO_DATE(sysdate,'DD-MM-YY'), TO_DATE(sysdate,'DD-MM-YY'))";
		    con.setAutoCommit(false);
			
			PreparedStatement ps = con.prepareStatement(query);
	 		ps.setString ( 1  , paddingno );
	 		ps.setString ( 2  , REGNO );
	 		ps.setDouble ( 3  , Double.parseDouble(AppData.getAdditional_load()) );
	 		ps.setInt	 ( 4  , Integer.parseInt(AppData.getPincode()) );
	 		ps.setString ( 5  , AppData.getGstn_flag() );
	 		ps.setString ( 6  , AppData.getGstno() );
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
	 		ps.setDouble ( 19 , em.getLoad() );
	 		ps.setString ( 20 , em.getSeccode() );
	 		
	 		ps.setDouble ( 21 , totamt );
	 		ps.setDouble ( 22 , appfee );
	 		ps.setDouble ( 23 , appfee_gst );
	 		ps.setDouble ( 24 , secdp );
	 		ps.setDouble ( 25 , dc );
	 		ps.setDouble ( 26 , dcgst );
	 		ps.setDouble ( 27 , totamt );
	 		ps.setInt	 ( 28 , 40 );
	 		ps.setString ( 29 , em.getCscno() );
	 		ps.setInt	 ( 30 , 7 );
	 		
	 		ps.setDouble ( 31 , existing_load + Double.parseDouble(AppData.getAdditional_load()));
	 		ps.setInt	 ( 32 , em.getPhase() ); //Check condition 
	 		ps.setString ( 33 , "Y" ); //Check condition 
	 		ps.setString ( 34 , AppData.getXukscno() );
	 		ps.setDouble ( 35 , em.getExisting_sd() );//check ebs field name from corporate table
	 		ps.setString ( 36 , "Y" ); //check condition
	 		ps.setString ( 37 , "" ); //status not required?
	 		ps.setInt	 ( 38 , 9 );
	 		ps.setString ( 39 , "ACTIVE" ); 
	 		ps.setString ( 40 , gst_updated_on); 
	 		
	 		System.out.println(AppData.getGstn_flag());
	 		System.out.println(AppData.getGstno());
	 		System.out.println(gst_updated_on);
	 		
	 		
	 		ps.executeUpdate();
	 		
	 		
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
		    sess.setAttribute("appname", em.getName());
		    sess.setAttribute("category", em.getCatcode() + "-" + em.getCatdescription());
		    sess.setAttribute("contld", String.valueOf(existing_load) );
		    sess.setAttribute("reqld", AppData.getAdditional_load());
		    sess.setAttribute("totld", String.valueOf(existing_load + Double.parseDouble( AppData.getAdditional_load() )) );
		    sess.setAttribute("metric", metrics);
		    sess.setAttribute("appfee", (new StringBuilder(String.valueOf(appfee))).toString());
		    sess.setAttribute("appfee_gst", (new StringBuilder(String.valueOf(appfee_gst))).toString());
		    sess.setAttribute("secdp", (new StringBuilder(String.valueOf(secdp))).toString());
		    sess.setAttribute("devchg", (new StringBuilder(String.valueOf(dc))).toString());
		    sess.setAttribute("devchg_gst", (new StringBuilder(String.valueOf(dcgst))).toString());
		    sess.setAttribute("totamt", (new StringBuilder(String.valueOf(totamt))).toString());
		    sess.setAttribute("sdpaid", "0");
		    sess.setAttribute("bal", "0");
		    sess.setAttribute("estpmt", "N");
		    sess.setAttribute("trmsg", temp);
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
