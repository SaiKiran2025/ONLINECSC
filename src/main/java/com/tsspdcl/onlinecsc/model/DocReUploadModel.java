package com.tsspdcl.onlinecsc.model;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class DocReUploadModel {

	private String regno;
	
	private MultipartFile id_proof;
	private MultipartFile registration_proof;
	private MultipartFile occupancy_cert;
	private MultipartFile approval_plan;
	private MultipartFile indeminity_bond;
	
}
