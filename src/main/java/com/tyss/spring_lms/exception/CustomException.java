package com.tyss.spring_lms.exception;

public class CustomException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -828841859517371860L;

	public CustomException(String msg) {
		super(msg);
	}
}
