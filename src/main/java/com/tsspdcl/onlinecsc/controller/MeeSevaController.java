package com.tsspdcl.onlinecsc.controller;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.tsspdcl.onlinecsc.meesevaservices.MeeSeva;

@Controller
@RequestMapping(path = "")
public class MeeSevaController {
	final static Logger logger = Logger.getLogger(MainController.class);
	
	
	
	@RequestMapping("/MeeSevaNewConnection")
	//@GetMapping("/MeeSevaNewConnection")
	public String getNewRegistration(@RequestParam("msg") String msg, @RequestParam("encmsg") String encmsg, 
				HttpSession session, Model model) throws  IOException {
		
		   MeeSeva mseva=new MeeSeva();
		   String result = "";
		   try {
			   //String msg="eyJyZXF1ZXN0aWQiOiIzMVNSTjIwMjMwOTIxMTQ0NDE0Iiwic3RhZmZjb2RlIjoiVFNHT1BSMDAwNSIsImNlbnRyZWNvZGUiOiJFU0VWQS1TUk4iLCJkaXN0Y29kZSI6IjMxIn0=";
			   //String encmsg = "BE48CD6D98DFEA8BB71F5B1A32256BAF10611F1D2EA9FE863A2EE9ADCA95498B";
				result = mseva.meeSevaNewRegistration(msg, encmsg, model);
			} catch (Exception e) {
				logger.warn("Exception in main controller in getNewRegistration method  " + e.getLocalizedMessage());
				e.printStackTrace();
			}
		   if(result.equals("success"))
			   //return "redirect:http://10.10.101.109:8080/onlinecsc/MeeSeva?requestid="+requestid+"&staffcode="+staffcode+"&centrecode="+centrecode+"&distcode="+distcode+"&error="+error+"&msg="+msg+"&encmsg="+encmsg; 
			   return "loginNR";
		   else
			   return "error";
		}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("MeesevaPayment")
	protected String meeSevaPayment(	@RequestParam("nregno") String nregno, 
								@RequestParam("meeseva_data") String meeseva_data,  
								@RequestParam("mobileno") String mobile, 
								HttpSession session, Model model) throws  IOException {
		   MeeSeva mseva=new MeeSeva();
		   String result = "";
		   try {
				result = mseva.meeSevaPayment(nregno, meeseva_data, mobile, model);
			} catch (Exception e) {
				logger.warn("Exception in main controller in meeSevaPayment method  " + e.getLocalizedMessage());
				e.printStackTrace();
			}
		   if(result.equals("paymentsuccess"))
			   return "meesevaPaymentRedirect";
		   else
			   return "error";
		
	}
	
	@PostMapping("/MeeSevaResponse")
	public ModelAndView MeeSevaResponse(@RequestParam("msg") String msg,
									@RequestParam("encmsg") String encmsg,
									Model model) {
	MeeSeva mseva=new MeeSeva();
	   String result = "";
	   try {
			result = mseva.meeSevaPaymentResponse(msg, encmsg, model);
		} catch (Exception e) {
			logger.warn("Exception in main controller in meeSevaPayment method  " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	   if(!result.equals("")) {
		   String Url="http://117.239.151.19:8080/RegPmtMSevaReceipt.jsp?params="+result; 
		   return new ModelAndView("redirect:" +Url);
	   } else
		   return new ModelAndView("error");
	}
	
}
