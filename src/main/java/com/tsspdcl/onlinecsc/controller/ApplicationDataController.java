package com.tsspdcl.onlinecsc.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.Collections;
import java.util.Vector;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tsspdcl.onlinecsc.model.ALAppDataModel;
import com.tsspdcl.onlinecsc.model.AdditionalPaymentModel;
import com.tsspdcl.onlinecsc.model.ApplicationDataModel;
import com.tsspdcl.onlinecsc.model.CCAppDataModel;
import com.tsspdcl.onlinecsc.model.DocReUploadModel;
import com.tsspdcl.onlinecsc.model.GaneshAppDataModel;
import com.tsspdcl.onlinecsc.model.LTMAppDataModel;
import com.tsspdcl.onlinecsc.services.AdlPayUpdate;
import com.tsspdcl.onlinecsc.services.CSCDB;
import com.tsspdcl.onlinecsc.services.DocumentsReUpload;
import com.tsspdcl.onlinecsc.services.InsertAL;
import com.tsspdcl.onlinecsc.services.InsertCC;
import com.tsspdcl.onlinecsc.services.InsertCC1;
import com.tsspdcl.onlinecsc.services.InsertGanesh;
import com.tsspdcl.onlinecsc.services.InsertLTM;
import com.tsspdcl.onlinecsc.services.InsertNR;
import com.tsspdcl.onlinecsc.services.SMSSender;
import com.tsspdcl.onlinecsc.services.UpdateNR;

@Controller
@RequestMapping(path="")
public class ApplicationDataController {
	final static Logger logger = Logger.getLogger(MainController.class);
	
	@RequestMapping(path = "/saveNR", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE ,
																			MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String insert(@ModelAttribute ApplicationDataModel AppData, HttpServletRequest request, Model model) {
		
		InsertNR in = new InsertNR();
		
		try {
			in.insert(AppData, request);
		} catch (Exception e) {
			logger.warn("Exception in ApplicationDataController in insert method  " + e.getLocalizedMessage());
			e.printStackTrace();
			model.addAttribute("type", "NR");
			return "error";
		}
		
		return "PaymentNR";
		
	}
	
	@RequestMapping(path = "/save1", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE ,
			MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String update(@ModelAttribute ApplicationDataModel AppData, HttpServletRequest request) {
		
		UpdateNR up = new UpdateNR();
		up.update(AppData, request);
		
		return "PaymentNR";
		
	}
	
	@RequestMapping(path = "/saveAdl", method = RequestMethod.POST)
	public String AdditionalPay(@RequestParam String nrno, HttpServletRequest request) {
		
		AdlPayUpdate apu = new AdlPayUpdate();
		try {
			apu.update(nrno, request);
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
		
		return "PaymentAdlPay";
		
	}
	

	@RequestMapping(path = "/saveLTM", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE ,
																				 MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String insertLTM(@ModelAttribute LTMAppDataModel AppData, HttpServletRequest request, Model model) {
		
		InsertLTM in = new InsertLTM();
		
		try {
			in.insert(AppData, request);
		} catch (Exception e) {
			logger.warn("Exception in ApplicationDataController in insertLTM method  " + e.getLocalizedMessage());
			e.printStackTrace();
			model.addAttribute("type", "LTM");
			return "error";
		}
		
		return "PaymentLTM";
		
	}
	
	@RequestMapping(path = "/saveAL", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE ,
																			   MediaType.APPLICATION_FORM_URLENCODED_VALUE})
		public String insertADL(@ModelAttribute ALAppDataModel AppData, HttpServletRequest request, Model model) {
		
		InsertAL in = new InsertAL();
		
		try {
			in.insert(AppData, request);
		} catch (Exception e) {
			logger.warn("Exception in ApplicationDataController in insertAL method  " + e.getLocalizedMessage());
		e.printStackTrace();
		//model.addAttribute("type", "AL");
		return "error";
		}
		
		return "PaymentLTM";
	
	}
	
	
	@RequestMapping(path = "/saveCC", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE ,
			   MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String insertCC(@ModelAttribute CCAppDataModel AppData, HttpServletRequest request, Model model) {
	
		InsertCC in = new InsertCC();
		InsertCC1 in1 = new InsertCC1();
		String cnature = AppData.getCnature();
		try {
			if( cnature.equals("40") || cnature.equals("41") || cnature.equals("42") || cnature.equals("47") || cnature.equals("48") || cnature.equals("79") || cnature.equals("69") )
				in.insert(AppData, request);
			else
				in1.insert(AppData, request);
			
			model.addAttribute("AppData", AppData);
		} catch (Exception e) {
			logger.warn("Exception in ApplicationDataController in insertCC method  " + e.getLocalizedMessage());
		e.printStackTrace();
		//model.addAttribute("type", "AL");
		return "error";
		}
		if( cnature.equals("40") || cnature.equals("41") || cnature.equals("42") || cnature.equals("47") || cnature.equals("48") || cnature.equals("79") || cnature.equals("69") )
			return "PaymentCC";
		else
			return "PaymentCC1";
	
	}
	
	@RequestMapping(path = "/saveCCGanesh", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE ,
			   MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String insertCCGanesh(@ModelAttribute GaneshAppDataModel AppData, HttpServletRequest request, Model model) {
	
		InsertGanesh ing = new InsertGanesh();
		try {
			ing.insert(AppData, request);			
			model.addAttribute("AppData", AppData);
		} catch (Exception e) {
			logger.warn("Exception in ApplicationDataController in insertCCGanesh method  " + e.getLocalizedMessage());
		e.printStackTrace();
		return "error";
		}

		return "PaymentCCGanesh";
	
	}
	
	@RequestMapping(path = "/saveDocs", method = RequestMethod.POST, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE ,
			   MediaType.APPLICATION_FORM_URLENCODED_VALUE})
	public String updateDocs(@ModelAttribute DocReUploadModel DocData, Model model) {
	
		DocumentsReUpload dru = new DocumentsReUpload();
		try {
			String status = dru.uploadDocs(DocData);
			model.addAttribute("upload_status", status);
		} catch (Exception e) {
			logger.warn("Exception in ApplicationDataController in updateDocs method  " + e.getLocalizedMessage());
			e.printStackTrace();
			return "error";
		}

		return "reUploadDocs";
	
	}
	
	
	
		
	
	/*@PostMapping(path = "/save",consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
			public void handleBrowserSubmissions(ApplicationDataModel AppData) throws Exception {
			    // Save feedback data
			System.out.println(AppData.getApplicant_name());
		
		InsertNR in = new InsertNR();
		in.insert(AppData);
		//return "redirect:/feedback/success";
			   
			}
	
	/*@GetMapping("/feedback/success")
	public ResponseEntity<String> getSuccess() {
	    return new ResponseEntity<String>("Thank you for submitting feedback.", HttpStatus.OK);
	}*/
	

}
