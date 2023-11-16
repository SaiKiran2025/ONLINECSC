package com.tsspdcl.onlinecsc.model;

import lombok.Data;
@Data
public class AdditionalPaymentModel {
		private String nrno;
		private String name;
		private String nrdate;
		private String section_name;
		private String category;
		private double load;
		private double actual_amount;
		private double paid;
		private double balance;
		private String addpay_resp;
		private String metric;
		private String trmsg;
	
}
