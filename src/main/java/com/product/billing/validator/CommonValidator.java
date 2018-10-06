package com.product.billing.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonValidator {
	
	 private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";  
	 private static final String NUMARIC = "[0-9]+";  
	 private static final String STRING_PATTERN = "[a-zA-Z\\s]+";  
	 private static final String MOBILE_PATTERN = "[0-9]{10}";  
	 private static final String PIN_PATTERN = "[0-9]{6}";  
	 private static final String WEB_SITE="(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?";

	 private Pattern pattern;  
	 private Matcher matcher;  
	 
	 public boolean isValidString(String str) {
		pattern = Pattern.compile(STRING_PATTERN); 
		matcher = pattern.matcher(str);  
  	   	return (!matcher.matches()) ?false:true;  
	 }
	 
	 public boolean isNumaric(String str) {
		pattern = Pattern.compile(NUMARIC); 
		matcher = pattern.matcher(str);  
  	   	return (!matcher.matches()) ?false:true;  
	 }
	 
	 public boolean isMobileNo(String str) {
		pattern = Pattern.compile(MOBILE_PATTERN); 
		matcher = pattern.matcher(str);  
  	   	return (!matcher.matches()) ?false:true;  
	 }
	 
	 public boolean isEmail(String str) {
		pattern = Pattern.compile(EMAIL_PATTERN); 
		matcher = pattern.matcher(str);  
  	   	return (!matcher.matches()) ?false:true;  
	 }
	 
	 public boolean isWebSite(String str) {
		pattern = Pattern.compile(WEB_SITE); 
		matcher = pattern.matcher(str);  
  	   	return (!matcher.matches()) ?false:true;  
	 }
	 
	 public boolean isValidPIN(String str) {
		pattern = Pattern.compile(PIN_PATTERN); 
		matcher = pattern.matcher(str);  
  	   	return (!matcher.matches()) ?false:true;  
	 }
}