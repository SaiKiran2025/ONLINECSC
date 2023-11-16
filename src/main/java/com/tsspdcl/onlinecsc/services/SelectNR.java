package com.tsspdcl.onlinecsc.services;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tsspdcl.onlinecsc.model.ApplicationDataModel;

public class SelectNR { 

	public ApplicationDataModel select(String login_regno) {
		String saveas = "E:\\READFiles\\retrieved.jpg";
		ApplicationDataModel adm = new ApplicationDataModel();
		CSCDB db = new CSCDB();
		try(Connection con = db.getConnection()){			
			String qry = "SELECT NRREGNO,APPLICANT_NAME,CONNADD1,CONNADD2,CONNADD3,CONNADD4,"
					+ "PINCODE,PHONENO,MOBILENO,EMAILID,COMMADD1,COMMADD2,COMMADD3,COMMADD4,APPLICANT_TYPE,"
					+ "LOCATION_TYPE,SOCIALGROUP,CONTRACTLD,CONNECTLD,DEPTTYPE,PHASE,AADHAR_NO,EST_TYPE,FATHER_NAME,CLEINT_IP_REL,"
					+ "GSTNFLAG,GSTN,to_char(GSTN_UPDT,'DD/MM/YYYY'),IDPROOF_TYPE,IDPROOF_NO,GENDER,REGISTERED_DOC,DOC_TYPE,DOC_NO,DOC_DATE,"
					+ "SEZ,ADJACENTUSCNO,AREANAME,AREACD,a.SECCD,a.EROCD,NRCSCNO,AREAGROUP,"
					+ "NRREGID,CATCODE,SUBCATCD,CSCUSERID,a.METRIC,IBOND,PURPOSE,REGNO_STATUS,"
					+ "GOVTCD,HODCD,to_char(TEMP_FROMDT,'DD/MM/YYYY'),to_char(TEMP_TODT,'DD/MM/YYYY'),COMMN_SERVICE,MUNCIPALCD,EQPOINTS,SERVTYPECD,TYPE, "
					+ "b.SECNAME, a.catcode || '-' || c.category as catdesc  "
					+ "FROM NEWCONNECTION_REGISTER a, ALL_SECTION b, CATEGORY_MASTER c "
					+ "WHERE NRREGNO = ? and status is null and a.SECCD = b.UK_SECCD and a.catcode = c.id and HTLT_FLAG = 'LT' ";
			PreparedStatement ps = con.prepareStatement(qry);
			ps.setString(1, login_regno);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {				
				adm.setApplicant_name(rs.getString(2));
				adm.setConn_address1(rs.getString(3));
				adm.setConn_address2(rs.getString(4));
				adm.setConn_address3(rs.getString(5));
				adm.setConn_address4(rs.getString(6));
				adm.setConn_pincode(rs.getString(7));
				adm.setMobileno(rs.getString(8));
				adm.setMobileno(rs.getString(9));
				adm.setEmail(rs.getString(10));
				adm.setComm_address1(rs.getString(11));
				adm.setComm_address2(rs.getString(12));
				adm.setComm_address3(rs.getString(13));
				adm.setComm_address4(rs.getString(14));
				adm.setCons_status(rs.getString(15));
				adm.setLocation_type(rs.getString(16));
				adm.setSocial_grp(rs.getString(17));
				adm.setContract_load(rs.getString(18));
				adm.setConn_load(rs.getString(19));
				adm.setServ_type(rs.getString(20));
				adm.setPhase(rs.getString(21));
				adm.setAadhar_no(rs.getString(22));
				adm.setEst_type(rs.getString(23));
				adm.setFather_name(rs.getString(24));
				adm.setClient_ip_rel(rs.getString(25));
				
				adm.setGstn_flag(rs.getString(26));
				adm.setGstno(rs.getString(27));
				adm.setGstn_updated_on(rs.getString(28));
				adm.setIdproof_type(rs.getString(29));
				adm.setIdproof_no(rs.getString(30));
				adm.setGender(rs.getString(31));
				adm.setRegdoc(rs.getString(32));
				adm.setRegdoctype(rs.getString(33));
				adm.setRegdocno(rs.getString(34));
				adm.setRegdocdate(rs.getString(35));
				
				adm.setSez(rs.getString(36));
				adm.setAdj_uscno(rs.getString(37));
				adm.setArea_name(rs.getString(38));
				adm.setArea_code(rs.getString(39));
				adm.setSection_code(rs.getString(40));
				adm.setEro_code(rs.getString(41));
				adm.setCsc_number(rs.getString(42));
				adm.setArea_group(rs.getString(43));
				
				adm.setCategory(rs.getString(45));
				adm.setSub_category(rs.getString(46));
				
				adm.setGov_code(rs.getString(52));
				adm.setHod_code(rs.getString(53));
				adm.setTemp_fmdt(rs.getString(54));
				adm.setTemp_todt(rs.getString(55));
				adm.setComnserv(rs.getString(56));
				adm.setMunicipality(rs.getString(57));
				adm.setEqpoints(rs.getString(58));
				adm.setSupply_nature(rs.getString(59));
				if(removeNull(rs.getString(60)).equals("Y")) {
					adm.setExtension(rs.getString(60));
				}
				else {
					adm.setExtension("N");
				}
				
				adm.setSection_name(rs.getString(61));
				adm.setCategory_desc(rs.getString(62));
				
				String qryFiles = "SELECT PHOTO, ID_PROOF, SALE_DEED, LOC_PHOTO FROM DOCUMENTS WHERE REGNO = ?"; 
				PreparedStatement psF = con.prepareStatement(qryFiles);
				psF.setString(1, login_regno);
				ResultSet rsF=psF.executeQuery();
				/*if(rsF.next()) {
					Blob b = rsF.getBlob(1);
					byte[] byte_array = b.getBytes(1, (int)b.length());
					FileOutputStream fos = new FileOutputStream(saveas);
					fos.write(byte_array);
					fos.close();
				}*/
				if(rsF.next()) {
					if(rsF.getBlob(1) != null)
						adm.setAphotoUrl("http://localhost:9090/onlinecsc/TSSPDCL/getDocument/"+login_regno+"/aphoto");
					if(rsF.getBlob(2) != null)
						adm.setIproofUrl("http://localhost:9090/onlinecsc/TSSPDCL/getDocument/"+login_regno+"/iproof");
					if(rsF.getBlob(3) != null)
						adm.setSdeedUrl("http://localhost:9090/onlinecsc/TSSPDCL/getDocument/"+login_regno+"/sdeed");
					if(rsF.getBlob(4) != null)
						adm.setLphotoUrl("http://localhost:9090/onlinecsc/TSSPDCL/getDocument/"+login_regno+"/lphoto");
				}
								
				adm.setRegno(login_regno);
				
				

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return adm;
	}
	
	public InputStream selectFile(String login_regno, String code) {
		Blob b = null;
		InputStream inputStream = null;
		CSCDB db = new CSCDB();
		try(Connection con = db.getConnection()){	
		String qryFiles = "SELECT PHOTO, ID_PROOF, SALE_DEED, LOC_PHOTO FROM DOCUMENTS WHERE REGNO = ?"; 
		PreparedStatement psF = con.prepareStatement(qryFiles);
		psF.setString(1, login_regno);
		ResultSet rsF=psF.executeQuery();
		if(rsF.next()) {
				if(code.equals("aphoto"))
					b = rsF.getBlob(1);
				if(code.equals("iproof"))
					b = rsF.getBlob(2);
				if(code.equals("sdeed"))
					b = rsF.getBlob(3);
				if(code.equals("lphoto"))
					b = rsF.getBlob(4);
				if(b != null) {
				byte[] bytes = b.getBytes(1, (int) b.length());
				inputStream = new ByteArrayInputStream(bytes);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			}
		return inputStream;
	}
	
	public Blob selectFile1(String login_regno) {
		Blob b = null;		CSCDB db = new CSCDB();
		try(Connection con = db.getConnection()){	
		String qryFiles = "SELECT PHOTO FROM DOCUMENTS WHERE REGNO = ?"; 
		PreparedStatement psF = con.prepareStatement(qryFiles);
		psF.setString(1, login_regno);
		ResultSet rsF=psF.executeQuery();
		if(rsF.next()) {
			b = rsF.getBlob(1);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	public String removeNull(String str)
	{
		if(str==null)
			return "";
		else
			return str;
	}
}
