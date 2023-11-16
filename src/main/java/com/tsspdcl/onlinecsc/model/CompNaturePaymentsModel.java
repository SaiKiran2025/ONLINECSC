package com.tsspdcl.onlinecsc.model;

import lombok.Data;

@Data
public class CompNaturePaymentsModel {
	
	private double cnature_af;
	private double cnature_afgst;
	private double cnature_sc;
	private double cnature_scgst;
	private double cnature_total;
	
	private String is_appfee;
	private String is_other_amount;
	
	private String cnature_desc;
}
