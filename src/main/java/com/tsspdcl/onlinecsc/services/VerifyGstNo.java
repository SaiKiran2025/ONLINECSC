package com.tsspdcl.onlinecsc.services;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.net.ssl.HttpsURLConnection;
//import online.AdqureGSP;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.tsspdcl.onlinecsc.services.CSCDB;

public class VerifyGstNo {
	
	public static void main(String args[])
	{
		try
		{
			CSCDB db = new CSCDB();
			Connection con=db.getConnection();
			System.out.println(getVerifyStat(con,"36AJUPT1215F1Z1"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static String getVerifyStat(Connection dbcon, String gstin) {
		//System.out.println("gstin..."+gstin);
	    String res = "";
	    HttpsURLConnection conn = null;
	    URL url = null;
	    try {
	      //String tkn = getToken(dbcon);
	    	String tkn = setNewToken(dbcon);
	      //System.out.println("get tkn:" + tkn);
	      
	      if (!tkn.startsWith("Error")) {
	        url = new URL("https://gsp.adaequare.com/enriched/commonapi/search?gstin=" + gstin + "&action=TP");
	        conn = (HttpsURLConnection)url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setRequestProperty("Content-Type", "application/json");
	        conn.setRequestProperty("Accept", "application/json");
	        conn.setRequestProperty("Authorization", "Bearer " + tkn);
	        if (conn.getResponseCode() != 200)
	          throw new RuntimeException("Failed : HTTP error code : " + 
	              conn.getResponseCode()); 
	        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        res = br.readLine();
	        System.out.println("res:" + res);
	        JSONParser parser = new JSONParser();
	        JSONObject jobj = (JSONObject)parser.parse(res);
	        System.out.println(" jobj:" + jobj);
	        Boolean succ = (Boolean)jobj.get("success");
	        String mess = (String)jobj.get("message");
	        if (succ.booleanValue()) {
	          JSONObject result = (JSONObject)jobj.get("result");
	          String trdnm = (String)result.get("lgnm");
	          String sez = (String)result.get("dty");
	          System.out.println("sez:" + sez);
	          JSONObject pradr = (JSONObject)result.get("pradr");
	          JSONObject addr = (JSONObject)pradr.get("addr");
	          String bnm = (String)addr.get("bnm");
	          String st = (String)addr.get("st");
	          String loc = (String)addr.get("loc");
	          String bno = (String)addr.get("bno");
	          String stcd = (String)addr.get("stcd");
	          String dst = (String)addr.get("dst");
	          String city = (String)addr.get("lgnm");
	          String flno = (String)addr.get("flno");
	          String pncd = (String)addr.get("pncd");
	          String lt = (String)addr.get("lt");
	          String lg = (String)addr.get("lg");
	          String sts = (String)result.get("sts");
	          String sqlStart = "INSERT INTO GSTN_REGS(GSTNO";
	          String sqlEnd = ") Values('".concat(gstin).concat("'");
	          sqlStart = String.valueOf(sqlStart).concat(", TRDNAME");
	          sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(trdnm)).concat("'");
	          if (sez != null) {
	            sqlStart = String.valueOf(sqlStart).concat(", DTY");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(sez)).concat("'");
	          } 
	          if (bnm != null) {
	            sqlStart = String.valueOf(sqlStart).concat(", BNM");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(bnm)).concat("'");
	          } 
	          if (st != null) {
	            sqlStart = String.valueOf(sqlStart).concat(", ST");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(st)).concat("'");
	          } 
	          if (loc != null) {
	            sqlStart = String.valueOf(sqlStart).concat(", LOC");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(loc)).concat("'");
	          } 
	          if (bno != null) {
	            sqlStart = String.valueOf(sqlStart).concat(", BNO");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(bno)).concat("'");
	          } 
	          if (stcd != null) {
	            sqlStart = String.valueOf(sqlStart).concat(", STCD");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(stcd)).concat("'");
	          } 
	          if (dst != null) {
	            sqlStart = String.valueOf(sqlStart).concat(", DST");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(dst)).concat("'");
	          } 
	          if (city != null) {
	            sqlStart = String.valueOf(sqlStart).concat(", CITY");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(city)).concat("'");
	          } 
	          if (flno != null) {
	            sqlStart = String.valueOf(sqlStart).concat(", FLNO");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(flno)).concat("'");
	          } 
	          if (pncd != null) {
	            sqlStart = String.valueOf(sqlStart).concat(", PNCD");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(pncd)).concat("'");
	          } 
	          if (lt != null && !lt.equals("")) {
	            sqlStart = String.valueOf(sqlStart).concat(", LT");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(lt)).concat("'");
	          } 
	          if (lg != null && !lg.equals("")) {
	            sqlStart = String.valueOf(sqlStart).concat(", LG");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(lg)).concat("'");
	          } 
	          if (sts != null) {
	            sqlStart = String.valueOf(sqlStart).concat(", STAT");
	            sqlEnd = String.valueOf(String.valueOf(String.valueOf(sqlEnd).concat(",'")).concat(sts)).concat("'");
	          } 
	          String sql = String.valueOf(String.valueOf(sqlStart).concat(sqlEnd)).concat(")");
	          System.out.println(sql);
	          if (sts.equals("Active")) {
	        	CSCDB db = new CSCDB();
	        	 String dr="";
	            try(Connection con = db.getConnection()) {
	    			ResultSet rs= null;
	            	String qry = "select trdname from gstn_regs where gstno='" + gstin + "'" ;
			    	PreparedStatement ps = con.prepareStatement(qry);		
					rs=ps.executeQuery();
					if(rs.next()) {
		             dr = rs.getString(1);
					}
		              Statement stmt = dbcon.createStatement();
		              if (dr != null) {
		                String qry1 = "delete from gstn_regs where gstno= ? "; 
		                PreparedStatement pstmt=dbcon.prepareStatement(qry1);  
		                pstmt.setString(1, gstin);
			     		pstmt.executeUpdate();
		              }
		              stmt.executeUpdate(sql);
		              stmt.close();
		              System.out.println("EXECUTED");
	            } catch (Exception e1) {
	              System.out.println(e1.getMessage());
	            } 
	            res = trdnm;
	          } else {
	            res = "Error:Inactive GSTN";
	          } 
	        } else {
	          res = "Error:" + mess;
	        } 
	        conn.disconnect();
	      } else {
	        res = tkn;
	      } 
	    } catch (Exception e) {
	      res = "Error:" + e.getMessage();
	      e.printStackTrace();
	    } 
	    return res;
	  }
	  
	private static String getToken(Connection dcon) {
		CSCDB db = new CSCDB();
		ResultSet rs= null;
		ResultSet rs1= null;
	    String res = "";
	
	    try(Connection con = db.getConnection()) {
	    	String qry = "select access_token from adqgsp_token where round((sysdate-created_dt),3)<0.917" ;
		    	PreparedStatement ps = con.prepareStatement(qry);		
				rs=ps.executeQuery();
				if(rs.next())
					res = rs.getString(1);
				if (res == null) {
					String r = setNewToken(dcon);
		        if (r.equals("S")) {
		        	String qry1 = "select access_token from adqgsp_token where round((sysdate-created_dt),3)<0.917" ;
			    	PreparedStatement ps1 = con.prepareStatement(qry1);		
					rs1=ps1.executeQuery();
					if(rs1.next())
					res = rs1.getString(1);
		        } else {
		          res = "Error:Technical Problem";
		          System.out.println("tknrew:" + res);
		        } 
		      } 
		    } catch (Exception e) {
		      res = "Error:" + e.getMessage();
		    } 
		    return res;
		  }
	  
	  private static String setNewToken(Connection dcon) {
	    String res = "";
	    String atkn = "";
	    String urlpath = "https://gsp.adaequare.com/gsp/authenticate?grant_type=token";
	    try {
	      URL url = new URL(urlpath);
	      HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
	      con.setRequestMethod("POST");
	      con.setRequestProperty("Content-Type", "application/json; utf-8");
	      con.setRequestProperty("Accept", "application/json");
	      con.setRequestProperty("gspappid", "FF8C35A9792C4F95AAC9DFEEE0820A7F");
	      con.setRequestProperty("gspappsecret", "C4462E49G3095G4D41GBFA7GA22F18518224");
	      con.setDoOutput(true);
	      BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
	      String tmpStr = null;
	      String s = "";
	      while ((tmpStr = br.readLine()) != null) {
	        System.out.println(tmpStr);
	        s = String.valueOf(s) + tmpStr;
	      } 
	      s = s.trim();
	      JSONParser parser = new JSONParser();
	      JSONObject jobj = (JSONObject)parser.parse(s);
	      atkn = (String)jobj.get("access_token");
	      System.out.println("access_token:" + atkn.length());
	      /*Statement stmt = dcon.createStatement();
	      String qry = "update adqgsp_token set access_token='" + atkn + "',created_dt=sysdate";
	      stmt.executeUpdate(qry);
	      stmt.close();*/
	      res = "S";
	    } catch (Exception e) {
	      res = "Error:" + e.getMessage();
	      e.printStackTrace();
	    } 
	    return atkn;
	  }
	  
	   /*public static void main(String args[]) throws SQLException {
		  CSCDB db = new CSCDB();
		  Connection con = db.getConnection();
		  
		  System.out.println(VerifyGstNo.getVerifyStat(con, "36AAHFH8993C1Z1"));
	  }*/
}
