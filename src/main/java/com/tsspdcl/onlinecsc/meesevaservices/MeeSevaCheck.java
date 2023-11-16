package com.tsspdcl.onlinecsc.meesevaservices;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.URL;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HttpsURLConnection;
//import org.apache.axis2.util.Base64;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MeeSevaCheck {
	
	/*public static void main(String args[])
	{
		try
		{
			String jstr="{\"requestid\":\"31SRN20200902170850\",\"staffcode\":\"TSGOPR0005\",\"centrecode\":\"ESEVA-SRN\",\"distcode\":\"31\"}";
			Base64 b=new Base64();
			String encstr=b.encode(jstr.getBytes());
			byte[] decstr=b.decode(encstr);
			String ds=new String(decstr);
			JSONParser parser = new JSONParser();
			JSONObject jobj = (JSONObject)parser.parse(ds);
			System.out.println("Staff Code:"+jobj.get("staffcode"));
			System.out.println("Centrecode Code:"+jobj.get("centrecode"));
			System.out.println(getSHA256("eyJyZXF1ZXN0aWQiOiIzMVNSTjIwMjAwOTAyMTcwODUwIiwic3RhZmZjb2RlIjoiVFNHT1BSMDAwNSIsImNlbnRyZWNvZGUiOiJFU0VWQS1TUk4iLCJkaXN0Y29kZSI6IjMxIn0="));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}*/
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
	public static boolean validateCheck(String msg,String chkmsg)
	{
		//System.out.println("Validate Check...."+msg+"\n"+chkmsg);
		String chkg=getSHA256(msg);
		if(chkmsg.equals(chkg))
			return true;
		else
			return false;
	}
	private static String getSHA256(String base64String) 
	{
		String secret="";
		try {
			secret="lrs@#$789cpeseva@";
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(),	"HmacSHA256");
			sha256_HMAC.init(secret_key);
			byte raw[] = sha256_HMAC.doFinal(base64String.getBytes());
			StringBuffer ls_sb=new StringBuffer();
			for(int i=0;i<raw.length;i++)
				ls_sb.append(char2hex(raw[i]));
			return ls_sb.toString(); //step 6
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String char2hex(byte x)
	{
		char arr[]={
		'0','1','2','3',
		'4','5','6','7',
		'8','9','A','B',
		'C','D','E','F'
		};
		char c[] = {arr[(x & 0xF0)>>4],arr[x & 0x0F]};
		return (new String(c));
	}
	
	@SuppressWarnings("unchecked")
	public static String sendPayment(String reqid, String pwd, String appno, String appname, String mobile, Double amount)
	{
		String res="";
		String jsonInputString="";
		//String urlpath="https://tsstaging.meeseva.telangana.gov.in/meesevaredirectsrvs/CommonPaymentRequest.htm/paymentreq";
		String urlpath="https://ts.meeseva.telangana.gov.in/meesevaredirectsrvs/CommonPaymentRequest.htm/paymentreq";
		System.out.println(urlpath);
		
		try	{
			JSONObject jobjip=new JSONObject();
			jobjip.put("requestid", reqid);
			jobjip.put("password", pwd);
			jobjip.put("appno", appno);
			jobjip.put("appname", appname);
			jobjip.put("mobileno", mobile);
			jobjip.put("amount", amount);
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
	            System.out.println(i+" s:"+s);
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
