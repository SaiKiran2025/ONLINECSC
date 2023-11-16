package com.tsspdcl.onlinecsc.meesevaservices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

public class MeeSeva {

	public String meeSevaNewRegistration(String msg,String encmsg,Model model) {
		String  requestid = "",staffcode = "",centrecode = "",distcode = "",error = "";
		try {  
		    String result=generateChecksum(msg);
		    System.out.println("------checksum generated from meeseva------"+result);
			org.json.simple.parser.JSONParser parser = new JSONParser();
			org.json.simple.JSONObject jobj=null;
			jobj = (org.json.simple.JSONObject)parser.parse(result);
				if(jobj.get("errorcode").equals("000"))
				{
				String generatedChecksum = (String) jobj.get("checksum");
				//System.out.println("generated checksum ====" + generatedChecksum);
					if(generatedChecksum.equals(encmsg))
					{
						String dmsg=getDecode(msg);
						org.json.simple.parser.JSONParser parser1 = new JSONParser();
						org.json.simple.JSONObject jobj1=null;
						jobj1 = (org.json.simple.JSONObject)parser1.parse(dmsg);
						System.out.println("***************************");
						System.out.println("MeeSeva Request Details---"+jobj1);
						
						requestid = (String) jobj1.get("requestid");
						staffcode = (String) jobj1.get("staffcode");
						centrecode = (String) jobj1.get("centrecode");
						distcode = (String) jobj1.get("distcode");
						error = "0";
						
						//return "redirect:http://10.10.101.109:8080/onlinecsc/MeeSeva?requestid="+requestid+"&staffcode="+staffcode+"&centrecode="+centrecode+"&distcode="+distcode+"&error="+error+"&msg="+msg+"&encmsg="+encmsg; 
						 model.addAttribute("requestid", requestid);
							model.addAttribute("staffcode", staffcode);
							model.addAttribute("centrecode", centrecode);
							model.addAttribute("distcode", distcode);
							model.addAttribute("paytype", "meeseva");
							model.addAttribute("error", error);
							model.addAttribute("msg", msg);
							model.addAttribute("encmsg", encmsg);
							return "success";
							//return "loginNR"; 
					}	
					else{	
						model.addAttribute("error", "1");
						System.out.println("ERROR in meeSevaNewRegistration method");
					}	
			}
			else{
				model.addAttribute("error", "1");
				System.out.println("ERROR in meeSevaNewRegistration method");
			}
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		return "failure";
	}
public String meeSevaPayment(String nregno,String paytype,String mobile,Model model)
{
	String pvendor="", preqid="", appname="",parameters="",pstaffcode = "";
	double pdamt=0.0;
	JSONObject jobjip=new JSONObject();
	final String checksum ;
	String msg = "";
	
	String[] resparam=paytype.split("\\|");
	
	if(resparam.length>1) {
		pvendor = resparam[0];
		preqid = resparam[1];
		pdamt = Double.parseDouble(resparam[2]);
		appname = resparam[3];
		pstaffcode = resparam[4];
		jobjip.put("vlecode", pstaffcode);
		jobjip.put("transid", preqid);
		jobjip.put("mprn", nregno);
		//jobjip.put("amount", pdamt);
		jobjip.put("amount", pdamt);
		jobjip.put("appname", appname);
		//jobjip.put("ru", "http://10.10.101.109:8080/onlinecsc/MeeSevaResponse"); 
		jobjip.put("ru", "https://webportal.tssouthernpower.com/onlinecsc/MeeSevaResponse");
		jobjip.put("opt1", "");
		jobjip.put("opt2", "");
		jobjip.put("opt3", "");
		jobjip.put("opt4", "");
		System.out.println("***************************");
		System.out.println("registration data sent to meeseva----"+jobjip);
	}
	else {
		pvendor = resparam[0];
	}
		if(pvendor.equals("meeseva")) {
			JSONParser parser = new JSONParser();
			org.json.simple.JSONObject jobj=null;
		
			try {
				msg = CipherDecrypt.encode(jobjip.toString());
				//System.out.println(" ----- msg from payment ---- "+msg);
				checksum = generateChecksum(msg);
				System.out.println("***************************");
				System.out.println(" ----- Checksum generated from meeseva during payment ---- "+checksum);
				System.out.println("***************************");
				org.json.simple.parser.JSONParser parser2 = new JSONParser();
				org.json.simple.JSONObject jobj2=null;
				jobj2 = (org.json.simple.JSONObject)parser2.parse(checksum);
				//System.out.println(" ----- Checksum generated from jobj2 ---- "+jobj2);
				model.addAttribute("paymentchecksum", jobj2.get("checksum"));
				model.addAttribute("msg", msg);
				
				return "paymentsuccess";
				//return "meesevaPaymentRedirect";
			}
			catch(Exception ei) {
				ei.printStackTrace();
			}
			
	} 
		return "paymentfailure";
}

public String meeSevaPaymentResponse(String msg,String encmsg, Model model) {
	
	String parameters = "";
	String checksumResult = generateChecksum(msg);
	
	System.out.println("***************************");
	System.out.println("----Checksum generated after meeseva response ------"+checksumResult);
	
	org.json.simple.parser.JSONParser checksum_parser = new JSONParser();
	org.json.simple.JSONObject checksum_jobj=null;
	
	try {
		 checksum_jobj = (org.json.simple.JSONObject)checksum_parser.parse(checksumResult);
		 if(checksum_jobj.get("errorcode").equals("000")){
			String generatedChecksum = (String) checksum_jobj.get("checksum");
			if(generatedChecksum.equals(encmsg)){
				String response_msg=getDecode(msg);
				org.json.simple.parser.JSONParser parser1 = new JSONParser();
				org.json.simple.JSONObject response_jobj=null;
				response_jobj = (org.json.simple.JSONObject)parser1.parse(response_msg);
				System.out.println("***************************");
				System.out.println("-----meeseva Payment Details---"+response_jobj);
					
					if(response_jobj.get("errrcode").equals("000")) {
					        parameters = CipherDecrypt.encode(response_jobj.get("mprn")+"|"+response_jobj.get("meesevaid")+"|meeseva|"+response_jobj.get("prn")+"|"+response_jobj.get("amount").toString()+"|"+response_jobj.get("usercharges").toString()+"|"+response_jobj.get("vlecode").toString());
							return parameters;
					}
			}	
			else {	
					model.addAttribute("error", "1");
			}	
		}
		else{	
			model.addAttribute("error", "1");
		}	
	}
	catch(Exception ei) {
		ei.printStackTrace();
	}
	return parameters;
}
public static String getDecode(String msg)
{
	String r="";
	try
	{
		Base64 b=new Base64();
		byte[] decstr=b.decode(msg);
		String ds=new String(decstr);
		r=ds;
	}
	catch(Exception e)
	{
		r=e.getMessage();
		e.printStackTrace();
	}
	return r;
}
@SuppressWarnings("unchecked")
public static String generateChecksum(String msg)
{
	String res="";
	String jsonInputString="";
	String urlpath = "https://ts.meeseva.telangana.gov.in/meesevaredirectsrvs/PgCheckGeneration.htm";
	//System.out.println(urlpath);
	
	try	{
		JSONObject jobjip=new JSONObject();
		jobjip.put("mid", "");
		jobjip.put("jsonstr", msg);
		jobjip.put("userid", "meeseva");
		jobjip.put("password", "meeseva789$#");

		StringWriter swout = new StringWriter();
    	
		try {
    		jobjip.writeJSONString(swout);
    	} catch(Exception ex1){}
		
    	jsonInputString=swout.toString();
		java.lang.System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
		URL url = new URL(urlpath);
		HttpsURLConnection con = (HttpsURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		con.setDoOutput(true);
		try {
			OutputStream os = con.getOutputStream();
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println(" OS :: "+e.getMessage());
		}
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8")); 
			String tmpStr = null;
			String s="";
			int i=0;
            while((tmpStr = br.readLine()) != null){
                //System.out.println(tmpStr);
                s=s+tmpStr;
                i++;
            }
            s=s.trim();
           // System.out.println("------checksum result:--------"+s);
            res=s;
		 
		} catch(Exception e)	{
			res="{\"ERROR\":\""+e.getMessage()+"\"}";
			e.printStackTrace();
			System.out.println(e);
		}
	}catch (Exception e) {
		e.printStackTrace();
		res="{\"ERROR\":\""+e.getMessage()+"\"}";
		// TODO: handle exception
	}
	return res;
}
}
