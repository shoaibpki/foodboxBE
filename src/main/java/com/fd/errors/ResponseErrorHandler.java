package com.fd.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fd.model.ErrorMessage;

@ControllerAdvice
public class ResponseErrorHandler extends ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public ErrorMessage genericExHandler(Exception ex) {
		ErrorMessage em = new ErrorMessage(
				HttpStatus.INTERNAL_SERVER_ERROR, 
				ex.getMessage()
			);
		return em;
		
	}
	
	
}
