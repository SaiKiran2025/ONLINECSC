package com.tsspdcl.onlinecsc.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tsspdcl.onlinecsc.model.AdditionalPaymentModel;

public class AdlPayUpdate {

	public void update(String nrno, HttpServletRequest request) throws Exception {
		CSCDB db = new CSCDB();
		ResultSet rs= null;
		String qry = "";
		AdditionalPaymentModel apm = new AdditionalPaymentModel();
		try(Connection con = db.getConnection()){
			qry="SELECT  NRREGNO RegNo,a.APPLICANT_NAME Name,to_char(NRREGDATE,'DD-Mon-YYYY')Reg_Date,c.SECNAME Section, b.CATDESC Category,CONTRACTLD load,status Status,"
					+ "(NVL(a.APPL_FEES,0)+NVL(a.APPL_FEES_GST,0)+NVL(a.DEVLOPCHRGS,0)+NVL(a.DCGST,0)+NVL(a.SECURITYDEP,0))Amt_To_Be_Paid ,"
					+ "(select sum(nvl(amount,0)) from payment_master where reg_no= ?) Amt_paid,a.metric FROM NEWCONNECTION_REGISTER a,category b,all_section c,SUBCATEGORY d "
					+ "where a.catcode=b.catcode and a.seccd=c.uk_seccd and a.SUBCATCD=d.SCSUBCAT and a.CATCODE=d.SCCAT and  ADDL_PMT='Y' AND nvl(a.type,'N')<>'Y' AND a.status<>5 and NRREGNO= ? ";
			//
			PreparedStatement pstmt=con.prepareStatement(qry);  
     		pstmt.setString(1, nrno);
     		pstmt.setString(2, nrno);
     		rs=pstmt.executeQuery();
			if(rs.next()) {
				apm.setNrno(rs.getString(1));
				apm.setName(rs.getString(2));
				apm.setNrdate(rs.getString(3));
				apm.setSection_name(rs.getString(4));
				apm.setCategory(rs.getString(5));
				apm.setLoad(rs.getDouble(6));
				apm.setActual_amount(rs.getDouble(8));
				apm.setPaid(rs.getDouble(9));
				apm.setMetric(rs.getString(10));
			}
			DBMethods dbm = new DBMethods();
			HttpSession sess = request.getSession();
	        String REGNO = apm.getNrno();
	        double contld = apm.getLoad();
	        double actual_amount = apm.getActual_amount();
	        double paid_amount = apm.getPaid();
	        double totamt = actual_amount - paid_amount;
	        
	        String temp = "TSSPDCL|" + REGNO + "|NA|" + totamt + "|NA|NA|NA|INR|NA|R|tsspdcl|NA|NA|F|NEWSCON|NA|NA|NA|NA|NA|NA|http://117.239.151.19:8080/AdditionalPmt.jsp";
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
		      sess.setAttribute("appname", apm.getName());
		      sess.setAttribute("category", apm.getCategory());
		      sess.setAttribute("contld", (new StringBuilder(String.valueOf(contld))).toString());
		     // sess.setAttribute("contld", (new StringBuilder(String.valueOf(a_load))).toString());
		      sess.setAttribute("metric", apm.getMetric());
		      sess.setAttribute("actual_amount", (new StringBuilder(String.valueOf(actual_amount))).toString());
		      sess.setAttribute("paid_amount", (new StringBuilder(String.valueOf(paid_amount))).toString());
		      sess.setAttribute("totamt", (new StringBuilder(String.valueOf(totamt))).toString());
		      sess.setAttribute("sdpaid", "0");
		      sess.setAttribute("bal", "0");
		      sess.setAttribute("estpmt", "N");
		      sess.setAttribute("trmsg", temp);
	
		}
		catch(Exception e) {
	    	throw e;
			}
		
	}
	//Addtional Payment 
	public String update1(String nrno) throws Exception {
		CSCDB db = new CSCDB();
		ResultSet rs= null;
		String qry = "";
		AdditionalPaymentModel apm = new AdditionalPaymentModel();
		try(Connection con = db.getConnection()){
			qry="SELECT  NRREGNO RegNo,a.APPLICANT_NAME Name,to_char(NRREGDATE,'DD-Mon-YYYY')Reg_Date,c.SECNAME Section, b.CATDESC Category,CONTRACTLD load,status Status,"
					+ "(NVL(a.APPL_FEES,0)+NVL(a.APPL_FEES_GST,0)+NVL(a.DEVLOPCHRGS,0)+NVL(a.DCGST,0)+NVL(a.SECURITYDEP,0))Amt_To_Be_Paid ,"
					+ "(select sum(nvl(amount,0)) from payment_master where reg_no= ?) Amt_paid,a.metric FROM NEWCONNECTION_REGISTER a,category b,all_section c,SUBCATEGORY d "
					+ "where a.catcode=b.catcode and a.seccd=c.uk_seccd and a.SUBCATCD=d.SCSUBCAT and a.CATCODE=d.SCCAT and  ADDL_PMT='Y' AND nvl(a.type,'N')<>'Y' AND a.status<>5 and NRREGNO= ? ";
			//
			PreparedStatement pstmt=con.prepareStatement(qry);  
     		pstmt.setString(1, nrno);
     		pstmt.setString(2, nrno);
     		rs=pstmt.executeQuery();
			if(rs.next()) {
				apm.setNrno(rs.getString(1));
				apm.setName(rs.getString(2));
				apm.setNrdate(rs.getString(3));
				apm.setSection_name(rs.getString(4));
				apm.setCategory(rs.getString(5));
				apm.setLoad(rs.getDouble(6));
				apm.setActual_amount(rs.getDouble(8));
				apm.setPaid(rs.getDouble(9));
				apm.setMetric(rs.getString(10));
			}
			DBMethods dbm = new DBMethods();
	        String REGNO = apm.getNrno();
	        double contld = apm.getLoad();
	        double actual_amount = apm.getActual_amount();
	        double paid_amount = apm.getPaid();
	        double totamt = actual_amount - paid_amount;
	        
	        String temp = "TSSPDCL|" + REGNO + "|NA|" + totamt + "|NA|NA|NA|INR|NA|R|tsspdcl|NA|NA|F|NEWSCON|NA|NA|NA|NA|NA|NA|http://117.239.151.19:8080/AdditionalPmt.jsp";
		      String strHash = null;
		      String commonstr = "BObYe64qYnS6";
		      try {
		        strHash = dbm.HmacSHA256(temp, commonstr);
		      } catch (Exception eCRC) {
		        System.out.println(eCRC.toString());
		      } 
		      temp = String.valueOf(temp) + "|" + strHash;
		      apm.setTrmsg(temp);
		     }
		catch(Exception e) {
	    	throw e;
			}
		return apm.getTrmsg();
	}
	
		public AdditionalPaymentModel checkNrno(String nrno) {
			CSCDB db = new CSCDB();
			ResultSet rs= null;
			String qry = "";
			AdditionalPaymentModel apm = new AdditionalPaymentModel();
			try(Connection con = db.getConnection()){
			
				qry="SELECT  NRREGNO RegNo,a.APPLICANT_NAME Name,to_char(NRREGDATE,'DD-Mon-YYYY')Reg_Date,c.SECNAME Section, b.CATDESC Category,CONTRACTLD load,status Status,"
						+ "(NVL(a.APPL_FEES,0)+NVL(a.APPL_FEES_GST,0)+NVL(a.DEVLOPCHRGS,0)+NVL(a.DCGST,0)+NVL(a.SECURITYDEP,0))Amt_To_Be_Paid ,"
						+ "(select sum(nvl(amount,0)) from payment_master where reg_no= ?) Amt_paid,a.metric FROM NEWCONNECTION_REGISTER a,category b,all_section c,SUBCATEGORY d "
						+ "where a.catcode=b.catcode and a.seccd=c.uk_seccd and a.SUBCATCD=d.SCSUBCAT and a.CATCODE=d.SCCAT  AND a.status<>5 and NRREGNO= ?";
				//
				PreparedStatement pstmt=con.prepareStatement(qry);  
	     		pstmt.setString(1, nrno);
	     		pstmt.setString(2, nrno);
	     		rs=pstmt.executeQuery();
				if(rs.next()) {
					apm.setAddpay_resp("VALID");
					//System.out.println("VALID Condition");
					apm.setNrno(rs.getString(1));
					apm.setName(rs.getString(2));
					apm.setNrdate(rs.getString(3));
					apm.setSection_name(rs.getString(4));
					apm.setCategory(rs.getString(5));
					apm.setLoad(rs.getDouble(6));
					apm.setActual_amount(rs.getDouble(8));
					apm.setPaid(rs.getDouble(9));
					apm.setMetric(rs.getString(10));
					apm.setBalance(rs.getDouble(8)-rs.getDouble(9));
					apm.setTrmsg(update1(nrno));
					}
				else {
					apm.setAddpay_resp("INVALID");
					//System.out.println("INVALID Condition");
				}
		        
			}catch(Exception e) {
				e.printStackTrace();
			}
			return apm;
		}
	
}

