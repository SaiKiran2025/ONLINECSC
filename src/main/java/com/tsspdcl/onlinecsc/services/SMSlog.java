package com.tsspdcl.onlinecsc.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class SMSlog {

	
	public void InsertSMSLog(String mobileno,String msg,String user,String ack)
	{
		CSCDB db = new CSCDB();
		try(Connection con =db.getConnection()){
			
						
			String sql="insert into route_sms_log(mobileno,message,createdby,ack) values (?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString ( 1 , mobileno );
			pstmt.setString ( 2 , msg );
			pstmt.setString ( 3 , user);
			pstmt.setString ( 4 , ack );
			int i=pstmt.executeUpdate();

		} catch(Exception ex) {
				System.out.println("\n Error In SMSlog Class : "+ex.getMessage()+" \n");
		}
	}
	
}
