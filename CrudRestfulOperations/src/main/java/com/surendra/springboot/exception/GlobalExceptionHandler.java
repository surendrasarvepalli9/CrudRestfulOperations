package com.surendra.springboot.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	//  handle specific exceptions
	@ExceptionHandler(ResourceNotFoundException.class)

	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {

		ErrorDetails details = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(details,HttpStatus.NOT_FOUND);
	}
	
//  handle API exceptions
	@ExceptionHandler(APIException.class)

	public ResponseEntity<?> handleResourceNotFoundException(APIException exception, WebRequest request) {

		ErrorDetails details = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(details,HttpStatus.NOT_FOUND);
	}
	
	
	//handle other exceptions
	
		@ExceptionHandler(Exception.class)

		public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {

			ErrorDetails details = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
			
			return new ResponseEntity(details,HttpStatus.INTERNAL_SERVER_ERROR);
		}

}
