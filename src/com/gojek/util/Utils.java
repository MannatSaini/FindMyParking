package com.gojek.util;

public class Utils {
	
	public static Boolean isStringNullOrEmpty( String input){
		Boolean isStringNullOrEmpty=Boolean.FALSE;
		if (input ==null || input.trim().equalsIgnoreCase("")){
			isStringNullOrEmpty=Boolean.TRUE;
		}
		 
		return isStringNullOrEmpty;
	}
	

}
