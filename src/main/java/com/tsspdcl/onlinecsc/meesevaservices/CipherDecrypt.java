package com.tsspdcl.onlinecsc.meesevaservices;

import java.nio.charset.StandardCharsets;

import javax.xml.bind.DatatypeConverter;

public class CipherDecrypt {
	public static String encode(String value) throws Exception {
		return  DatatypeConverter.printBase64Binary(value.getBytes(StandardCharsets.UTF_8)); // use "utf-8" if java 6
	}

	public static String decode(String value) throws Exception {
	    byte[] decodedValue = DatatypeConverter.parseBase64Binary(value);
	    return new String(decodedValue, StandardCharsets.UTF_8); // use "utf-8" if java 6
	}
	public static void main(String args[]) throws Exception{
		//String original = "NR90521451227|31SRN20200902170850|MSEVA|31SRN0001076388|2045.5|45.00|TSGOPR0005";
		String original = "sivaji";
		System.out.println(encode(original));
	   /*byte[] bytes = original.getBytes(StandardCharsets.UTF_8);
	   String base64Encoded = new Base64().encodeToString(bytes);
	   System.out.println("original text: " + original); 
	   System.out.println("Base64 encoded text: " + base64Encoded);

	   // Decode
	   byte[] asBytes = new Base64().decode(base64Encoded);
	   String base64Decoded = new String(asBytes, StandardCharsets.UTF_8);
	   System.out.println("Base64 decoded text: " + base64Decoded);*/
	   System.out.println(decode(encode(original)));
   }
}
