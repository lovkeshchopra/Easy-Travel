package com.easytravel.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {

	public static Boolean isValidMobileNumber(String s) {
		Pattern p = Pattern.compile("[6-9]{1}[0-9]{9}");
		Matcher m = p.matcher(s);
		return (m.find() && m.group().equals(s));
	}

	public static Boolean isValidAadhaarNumber(String s) {
		Pattern p = Pattern.compile("[2-9]{1}[0-9]{11}");
		Matcher m = p.matcher(s);
		return (m.find() && m.group().equals(s));
	}

	public static Boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;

		return pat.matcher(email).matches();
	}
}
