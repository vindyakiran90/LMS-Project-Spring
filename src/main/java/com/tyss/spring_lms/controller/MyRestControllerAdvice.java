package com.tyss.spring_lms.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tyss.spring_lms.beans.LMSResponse;
import com.tyss.spring_lms.exception.LMSException;

@RestControllerAdvice
public class MyRestControllerAdvice {
	@ExceptionHandler
	public LMSResponse myExceptionHandler(LMSException exception) {
		LMSResponse response = new LMSResponse();
		response.setError(true);
		response.setMessage(exception.getMessage());
		return response;
	}
}
