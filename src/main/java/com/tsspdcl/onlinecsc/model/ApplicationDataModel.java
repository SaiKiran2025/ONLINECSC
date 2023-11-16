
package com.tsspdcl.onlinecsc.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ApplicationDataModel {

	private String applicant_name;
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
	private String cons_status;
	private String location_type;
	private String social_grp;
	private String contract_load;
	private String conn_load;
	private String serv_type;
	private String phase;
	private String aadhar_no;
	private String est_type;
	private String father_name;
	private String client_ip_rel;
	private String gstn_flag;
	private String gstno;
	private String gstn_updated_on;
	private String idproof_type;
	private String idproof_no;
	private String gender;
	private String extension;
	private String regdoc;
	private String regdoctype;
	private String regdocno;
	private String regdocdate;
	private String sez;
	
	private String adj_uscno;
	private String  area_name;
	private String  area_code;
	private String  section_code;
	private String  ero_code;
	private String  csc_number;
	private String  area_group;
		
	//new
	private String category;
	private String sub_category;
	private String gov_code;
	private String hod_code;
	private String temp_fmdt;
	private String temp_todt;
	private String comnserv;
	private String municipality;
	private String eqpoints;
	private String supply_nature;
	
	private MultipartFile applicant_photo ;
	private MultipartFile id_proof;
	private MultipartFile premises_proof;
	private MultipartFile location_photo;
	private MultipartFile registration_proof;
	
	private String regno;
	private String aphotoUrl;
	private String iproofUrl;
	private String sdeedUrl;
	private String lphotoUrl;
	
	private String scnoext;
	private String noscnos;
	private String ukscnos1;
	private String ukscnos2;
	private String ukscnos3;
	private String ukscnos4;
	
	private String billStatus1;
	private String billStatus2;
	private String billStatus3;
	private String billStatus4;
	
	private String category1;
	private String category2;
	private String category3;
	private String category4;
	
	//meeseva
	private String paytype;
	private String requestid;
	private String staffcode;
	private String msg;
	private String encmsg;
	
	//for entry1 page
	private String section_name;
	private String category_desc;
	
	
	
	
}
