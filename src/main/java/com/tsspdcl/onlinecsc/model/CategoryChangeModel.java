package com.tsspdcl.onlinecsc.model;

import lombok.Data;

@Data
public class CategoryChangeModel {

	public String catid;
	public String catdesc;
	
	public CategoryChangeModel(String catid, String catdesc) {
		this.catid = catid;
		this.catdesc = catdesc;
    }

	public CategoryChangeModel() {}
}
