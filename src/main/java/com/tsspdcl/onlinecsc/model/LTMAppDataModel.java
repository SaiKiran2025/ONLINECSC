package com.tsspdcl.onlinecsc.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class LTMAppDataModel {
	
	private String reg_type;
	private String is_dbh;
	private String apartment_name;
	private String conn_address1;
	private String conn_address2;
	private String conn_address3;
	private String conn_address4;
	private String submission_on;
	private String conn_pincode;
	private String mobileno;
	private String email;
	private String comm_address1;
	private String comm_address2;
	private String comm_address3;
	private String comm_address4;
	private String comm_pincode;
	private String comm_phoneno;
	private String tot_req_connections;
	private String tot_dom_connections;
	private String tot_com_connections;
	private String dom_load;
	private String com_load;
	private String est_type;
	private String gstn_flag;
	private String gstno;
	private String gstn_updated_on;
	private String extension;
	private String regdoc;
	private String regdoctype;
	private String regdocno;
	private String approval_plan_no;
	
	private String occ_cert;
	private String occ_no;
	private String occ_issued_by;
	private String occ_issue_date;
	private String occ_flats;
	private String occ_certn_btn;
	private String is_height_less;
	private String is_occ_submit_later;
	private String company_name;
	private String mch_regid;
	private String builder_name;
	private String builder_pan_no;
	
	private String adj_uscno;
	private String  area_name;
	private String  area_code;
	private String  section_code;
	private String  ero_code;
	private String  csc_number;
	private String  area_group;
	
	private MultipartFile id_proof;
	private MultipartFile registration_proof;
	private MultipartFile approval_plan;
	
	private String idproof_type;
	
	private String scnoext;
	private String noscnos;
	private String ukscnos1;
	private String ukscnos2;
	private String ukscnos3;
	private String ukscnos4;
	private String ukscnos5;
	private String ukscnos6;
	private String ukscnos7;
	private String ukscnos8;
	private String ukscnos9;
	private String ukscnos10;

	private String billStatus1;
	private String billStatus2;
	private String billStatus3;
	private String billStatus4;
	private String billStatus5;
	private String billStatus6;
	private String billStatus7;
	private String billStatus8;
	private String billStatus9;
	private String billStatus10;
	
	private String category1;
	private String category2;
	private String category3;
	private String category4;
	private String category5;
	private String category6;
	private String category7;
	private String category8;
	private String category9;
	private String category10;

	
}
