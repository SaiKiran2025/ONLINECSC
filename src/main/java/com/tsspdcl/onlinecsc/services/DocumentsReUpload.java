package com.tsspdcl.onlinecsc.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.web.multipart.MultipartFile;

import com.tsspdcl.onlinecsc.model.DocReUploadModel;

public class DocumentsReUpload {

	public ArrayList<String> getDocType(String regno) {
		CSCDB db = new CSCDB();	
		ArrayList<String> doc_type = new ArrayList<String>();
		//String[] doc_type = {};
		try(Connection con = db.getConnection()){
			String qry = "select doc_typeid,docname,doc_rem from doc_quaries a,doctypes b where a.doc_typeid=b.docid and doc_status='ACTIVE' and doc_upflag is null and regno=? ";
			PreparedStatement ps = con.prepareStatement(qry);	
			ps.setString(1, regno);
			ResultSet rs = ps.executeQuery();			
			while(rs.next()) {
				doc_type.add(rs.getString(1));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println(doc_type.size() + " " +  doc_type.toString());
		return doc_type;
	}
	
	public String applicantDetails(String regno) {
		CSCDB db = new CSCDB();		
		String qry = "", applicant_name="";
		try(Connection con = db.getConnection()){
			if (regno.startsWith("NR")) 
				qry = "select APPLICANT_NAME from newconnection_register where nrregno = ? ";
			else if (regno.startsWith("LTM")) 
	        	qry = "select APARTMENT_NAME from apartment_connection where registration_number = ? ";
		    else if(regno.startsWith("CC"))
	        	qry = "select name from complaint_details  where complaint_no= ?";
			
			PreparedStatement ps = con.prepareStatement(qry);	
			ps.setString(1, regno);
			ResultSet rs = ps.executeQuery();	
			if(rs.next()) {
				applicant_name = rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return applicant_name;
	}
	
	public String uploadDocs(DocReUploadModel DocsData) throws Exception {
		
		CSCDB db = new CSCDB();		
		try(Connection con = db.getConnection()){
			String regno = DocsData.getRegno();
			if(DocsData.getId_proof() != null) {
				if(!DocsData.getId_proof().isEmpty())
					uploadFile(DocsData.getId_proof(), con, regno, "ID_PROOF");
			}
			if(DocsData.getRegistration_proof() != null) {
				if(!DocsData.getRegistration_proof().isEmpty())
					uploadFile(DocsData.getRegistration_proof(), con, regno, "SALE_DEED");
			}
			if(DocsData.getOccupancy_cert() != null) {
				if(!DocsData.getOccupancy_cert().isEmpty())
					uploadFile(DocsData.getOccupancy_cert(), con, regno, "OCC_CERT");
			}
			if(DocsData.getApproval_plan() != null) {
				if(!DocsData.getApproval_plan().isEmpty())
					uploadFile(DocsData.getApproval_plan(), con, regno, "APPLICATION_FORM");
			}
			if(DocsData.getIndeminity_bond() != null) {
				if(!DocsData.getIndeminity_bond().isEmpty())
					uploadFile(DocsData.getIndeminity_bond(), con, regno, "WIRING_CERTIFICATE");
			}
			
			String qryS = "", qryU = "", prev_status="";
			qryS = "select prev_status from documents where regno = ? ";
			PreparedStatement psS = con.prepareStatement(qryS);
			psS.setString(1, regno);
			ResultSet rs =  psS.executeQuery();	
			if(rs.next()) {
				prev_status = rs.getString(1);
			}
			if (regno.startsWith("NR")) 
				qryU = "update newconnection_register set status = ? where nrregno = ? ";
			else if (regno.startsWith("LTM")) 
	        	qryU = "update apartment_connection set status = ? where registration_number = ? ";
		    else if(regno.startsWith("CC"))
	        	qryU = "update complaint_details set status = ? where complaint_no= ? ";
		        
			PreparedStatement psU = con.prepareStatement(qryU);
	        psU.setString(1, prev_status);
	        psU.setString(2, regno);
	        psU.executeUpdate(); 
	        
	        String qryDQ = "update doc_quaries set DOC_UPFLAG = ? where doc_status='ACTIVE' and REGNO = ? ";
	        PreparedStatement psDQ = con.prepareStatement(qryDQ);
	        psDQ.setString(1, "Y");
	        psDQ.setString(2, regno);
	        psDQ.executeUpdate(); 
	        
	        System.out.println("files re-uploaded successfully");
	        return "uploaded";
	        
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}
	
	public void uploadFile(MultipartFile doc, Connection con, String regno, String column_name ) throws IOException, SQLException {
		String FILE_DIRECTORY = "E:\\ONLINE_CSC_FILES\\ReUploaded Files\\";
		FileStatements fs = new FileStatements(FILE_DIRECTORY, regno);
		FileInputStream Fis   = fs.FileRead(doc);
		String qryDoc = "UPDATE  documents SET " + column_name + " = ? where REGNO = ? ";
		PreparedStatement psDoc = con.prepareStatement(qryDoc);
        psDoc.setBinaryStream(1, Fis, Fis.available());
        psDoc.setString(2, regno);
        psDoc.executeUpdate(); 
        
        FileInputStream Fis1   = fs.FileRead(doc);
		String qryDoc1 = "UPDATE  spddocs.documents SET " + column_name + " = ? where REGNO = ? ";
		PreparedStatement psDoc1 = con.prepareStatement(qryDoc1);
        psDoc1.setBinaryStream(1, Fis1, Fis1.available());
        psDoc1.setString(2, regno);
        psDoc1.executeUpdate();
	}
	
	
}
