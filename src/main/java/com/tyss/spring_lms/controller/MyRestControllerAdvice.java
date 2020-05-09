package com.tyss.spring_lms.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tyss.spring_lms.beans.LMSResponse;
import com.tyss.spring_lms.exception.CustomException;

@RestControllerAdvice
public class MyRestControllerAdvice {
	@ExceptionHandler
	public LMSResponse myExceptionHandler(CustomException exception) {
		LMSResponse response = new LMSResponse();
		response.setError(true);
		response.setMessage(exception.getMessage());
		return response;
	}
}
