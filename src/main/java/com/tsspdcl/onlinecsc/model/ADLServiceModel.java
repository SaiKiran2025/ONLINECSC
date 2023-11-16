package com.tsspdcl.onlinecsc.model;

import lombok.Data;

@Data
public class ADLServiceModel {
	
	private String valid;
	private String name;
	private String catcode;
	private String subcatcode;
	private String Category;
	private double load;
	private String metric;
	
	private String section_name;
	private String addr1;
	private String addr2;
	private String addr3;
	private String addr4;
	
	private String is_complaint_exist;
	private String complaint_no;
	private String existing_cnature_desc;
	private String new_cnature_desc;
	
	private String is_billstop;
	
	private String is_mats_case;
	private String mats_case;
	private String mats_amount;
}
