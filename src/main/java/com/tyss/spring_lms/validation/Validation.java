package com.tyss.spring_lms.validation;

import java.util.regex.Pattern;

import javax.xml.bind.ValidationException;


public class Validation {
	
	public boolean validateFirstName(String regFirstName) throws ValidationException {
		boolean result = false;
		if(Pattern.matches("^[a-zA-Z]*$", regFirstName)) {
			result = true;
		} else {
			result = false;
			throw new ValidationException("Name should contain alphabets");
		}
		return result;
	}

	public boolean validateLastName(String regLastName) throws ValidationException {
		boolean result = false;
		if(Pattern.matches("^[a-zA-Z]+(['-][a-zA-Z]+)*$", regLastName)) {
			result = true;
		} else {
			result = false;
			throw new ValidationException("Name should contain alphabets");
		}
		return result;
	}
		
	public boolean validateId(int id) throws ValidationException{
		//Pattern pattern = Pattern.compile("[1-9]{1}[0-9]{4}");
		//Matcher matcher = pattern.matcher(String.valueOf(id));
		boolean result = false;
		if(Pattern.matches("[1-9]{1}[0-9]{4}", String.valueOf(id))) {
			result = true; 
		} else {
			result = false;
			throw new ValidationException("Id should contain exactly 5 digits");
		}
		return result;
	}

	public boolean validatephoneNo(long phoneNo) throws ValidationException{
		//Pattern pattern = Pattern.compile("[1-9]{5}");
		//Matcher matcher = pattern.matcher(String.valueOf(id));
		boolean result = false;
		if(Pattern.matches("(\\+)*(0|91)?[7-9]{1}\\d{9}", String.valueOf(phoneNo))) {
			result = true; 
		} else {
			result = false;
			throw new ValidationException("Phone number should contain exactly 10 digits which starts with digit 0/91"
					+ "and then 7/8/9");
		}
		return result;
	}
	
	public boolean validateEmail(String email) throws ValidationException  {
		boolean result = false;
		if(Pattern.matches("^[\\w]+(\\.[\\w]+)*@[a-zA-Z]+(\\.[a-zA-Z{2}])*(\\.[a-zA-Z]{3})$", email)) {
			result = true;
		} else {
			result = false;
			throw new ValidationException("Invalid Email");
		}
		return result;
	}

	public boolean validatePassword(String regPassword) throws ValidationException {
		boolean result = false;
		if(Pattern.matches("^((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@_#$%!]).{6,})$", regPassword)) {
			result = true;
		} else {
			result = false;
			throw new ValidationException("Password should contain more than 6 characters, \n"
			+ "special characters, \n digits \n"
					+ "white space characters are not allowed");
		}
		return result;
	}
	
	public boolean validateBookId(int bookId) throws ValidationException {
		boolean result = false;
		if(Pattern.matches("[1-9]{1}[0-9]{3}", String.valueOf(bookId))) {
			result = true;
		} else {
			result = false;
			throw new ValidationException("Book Id should contain exactly 4 digits");
		}
		return result;
	}
	
	public boolean validateBook(String book) throws ValidationException {
		boolean result = false;
		if(Pattern.matches("^[a-zA-Z\\s]*$", book)) {
			result = true;
		} else {
			result = false;
			throw new ValidationException("It must contain characters");
		}
		return result;
	}
	
	public boolean validateCopies(int copies) throws ValidationException {
		boolean result = false;
		if(Pattern.matches("[1-9]{1}[0-9]{1}", String.valueOf(copies))) {
			result = true;
		} else {
			result = false;
			throw new ValidationException("It must contain 2 digits");
		}
		return result;
	}
}
