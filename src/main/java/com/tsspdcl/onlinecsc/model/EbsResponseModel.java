package com.tsspdcl.onlinecsc.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class EbsResponseModel {
	private String ebs_resp;
	private String adjacent_uniq_scno;
	private String section_code;
	private String ero_code;
	private String section_name;
	private String csc_number;
	private String adj_area_code;
	private String adj_area_name;
	private String adj_area_group;
	private String billstatus;
	private String billcategory;
	private ArrayList<AreaData> area_data=new ArrayList<AreaData>();
}
