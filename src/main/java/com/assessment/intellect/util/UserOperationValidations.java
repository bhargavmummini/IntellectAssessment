package com.assessment.intellect.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.assessment.intellect.domain.ErrorDetails;
import com.assessment.intellect.domain.UserDetails;

import ch.qos.logback.core.boolex.Matcher;

public class UserOperationValidations {
	private static final String EMAIL_PATTERN =
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

		
	public static List<ErrorDetails> validateUserID(UserDetails userDetails){
		List<ErrorDetails> errorList = new ArrayList<>();
		if(!(userDetails.getUserID() != null && userDetails.getUserID().length() >= 6)){
			errorList.add(new ErrorDetails(UserDetailsConstants.MISSING_FIELD, "userID", "Invalid userID"));
		}
		return errorList;
	}
	public static List<ErrorDetails> validateCreateUser(UserDetails userDetails){
		List<ErrorDetails> errorList = new ArrayList<>();
		if(!validateName(userDetails.getfName())){
			errorList.add(new ErrorDetails(UserDetailsConstants.MISSING_FIELD, "fName", "Invalid FirstName"));
		}
		if(!validateName(userDetails.getlName())){
			errorList.add(new ErrorDetails(UserDetailsConstants.MISSING_FIELD, "lName", "Invalid FirstName"));
		}
		if(!validateEmail(userDetails.getEmail())){
			errorList.add(new ErrorDetails(UserDetailsConstants.MISSING_FIELD, "email", "Invalid FirstName"));
		}
		if(!validateDate(userDetails.getBirthDate())){
			errorList.add(new ErrorDetails(UserDetailsConstants.MISSING_FIELD, "birthDate", "Invalid FirstName"));
		}
		if(!validatePin(userDetails.getPinCode())){
			errorList.add(new ErrorDetails(UserDetailsConstants.MISSING_FIELD, "pinCode", "Invalid FirstName"));
		}
		return errorList;
		
	}
	private static boolean validateEmail(String email) {
		if(email == null){
			return false;
		}

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		java.util.regex.Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	private static boolean validatePin(String pinCode) {
		if(pinCode != null && pinCode.length() == 6){
			return true;
		}
		return false;
	}
	private static boolean validateDate(String birthDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		try{
			sdf.parse(birthDate);
			return true;
		}catch(Exception e){
			return false;	
		}
	}
	private static boolean validateName(String name){
		if(name != null && name.length()>4){
			return true;
		}
		return false;
	}
}
