package com.target.myretail.controllers;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.target.myretail.exceptions.ErrorMessage;
import com.target.myretail.exceptions.ProductNotFoundException;

@ControllerAdvice
public class ExceptionController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
//	@ExceptionHandler(ProductNotFoundException.class)
//	@ExceptionHandler(Exception.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	@ResponseBody
//	public ErrorMessage processProductNotFoundException(ProductNotFoundException e) {
//		ErrorMessage errorMessage = new ErrorMessage();
//		errorMessage.setMessage(e.getMessage());
//		return errorMessage;
//	}
	
	@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="IOException occured")
	@ExceptionHandler(IOException.class)
	public void handleIOException(){
		logger.error("IOException handler executed");
		//returning 404 error code
	}

}
