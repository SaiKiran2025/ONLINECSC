package com.tsspdcl.onlinecsc.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tsspdcl.onlinecsc.model.EBSModel;

public class EBSMethods {

	CSCDB db = new CSCDB();
	String qry = "";		
	EBSModel em = new EBSModel();
	
	public EBSModel getAllConsumerData(int ukscno) {
	try(Connection con = db.getConnection()){
		qry = " SELECT CTSCNO,CTNAME,nvl(A.CTADD1,'nil'),nvl(A.CTADD2,'nil'),nvl(A.CTADD3,'nil'),nvl(A.CTADD4,'nil'),B.id, B.category, a.ctload, A.CTUKSECCD, C.SECNAME,"
			+ " SD.CSCNO, nvl(A.CTSUBCAT,0), nvl(CTMTRPHASE,0), nvl(A.CTSECDPAMT,0),"
			+ " nvl(CTNETMETERFLAG,0), nvl(MPTCTMTRFLAG,0), nvl(CTPREPAIDFLAG,0), nvl(MPTMTRSIDE_HT,'LT') "
			+ "	FROM corporate.all_consumer@ebsrodbl A,CATEGORY_MASTER B,ALL_SECTION C, ALL_SUBDIVISION SD "
			+ " WHERE A.CTSECCD=C.SECCD AND C.SUBDIVCD = SD.UK_SUBDIV AND A.CTEROCD=C.EROCD AND A.CTCAT=B.ID "
			+ " AND B.htlt_flag = 'LT' AND A.CTUKSCNO= ? ";
		PreparedStatement ps = con.prepareStatement(qry);	
		ps.setInt(1, ukscno);
		ResultSet rs = ps.executeQuery();	
		if(rs.next()) {
			em.setScno(rs.getString(1));
			em.setName(rs.getString(2));
			em.setAddr1(rs.getString(3));
			em.setAddr2(rs.getString(4));
			em.setAddr3(rs.getString(5));
			em.setAddr4(rs.getString(6));
			em.setCatcode(rs.getString(7));
			em.setCatdescription(rs.getString(8));
			em.setLoad(rs.getDouble(9));
			em.setSeccode(rs.getString(10));
			em.setSecname(rs.getString(11));
			em.setCscno(rs.getString(12));
			em.setSubcatcode(rs.getString(13));
			em.setPhase(rs.getInt(14));
			em.setExisting_sd(rs.getDouble(15));
			
			em.setNetmtr_flag(rs.getInt(16));
			em.setCtmtr_flag(rs.getInt(17));
			em.setPrepaid_flag(rs.getInt(18));
			em.setMeter_ltht_flag(rs.getString(19));
		
		}
	}catch(Exception e) {
		e.printStackTrace();
	}	
	return em;
	
	}
	
}
