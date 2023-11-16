package com.tsspdcl.onlinecsc.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CCAppDataModel {
	
	private String  xukscno;
	private String  xmobileno;
	private String  pincode;
	private String  gstn_flag;
	private String  gstno;
	
	private String ctype;
	private String cnature;
	
	private String additional_load;
	private String total_load;
	private String extension;
	private String est_type;
	
	private String new_cat; // for category change
	
	private String naddress1;
	private String naddress2;
	private String naddress3;
	private String naddress4; // for address correction
	
	private String newtitle_name; 
	private String careOf; // for title transfer	
	
	private String xclubbedUscnos; 
	private String parent_uscno; 
	private String club_adl_load; 
	private String req_load_club;
	private String total_club_load; 
	private String is_adl_req; // for clubbing of load
	
	private String idproof_type;
	private String regdoctype;
	private MultipartFile id_proof;
	private MultipartFile registration_proof;
	private MultipartFile indeminity_bond;
	
	private MultipartFile doc1;
	private MultipartFile doc2;
	
	private String othamount;
	private String tempload;
	
	
	private String cdetails;//for billing complaints
		
}
