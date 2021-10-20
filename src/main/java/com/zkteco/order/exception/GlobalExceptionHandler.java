package com.zkteco.order.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseStatus
public class GlobalExceptionHandler {

	// handling specific exception
	@ExceptionHandler(OrderNotFoundException.class)
	public ResponseEntity<ErrorMessage> handleOrderNotFoundException(OrderNotFoundException exception, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.NOT_FOUND, exception.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);

	} 

	@ExceptionHandler(APIException.class)
	public ResponseEntity<ErrorMessage> handleAPIException(APIException exception, WebRequest request) {

		ErrorMessage errorMessage = new ErrorMessage(new Date(),  HttpStatus.NOT_FOUND, exception.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

}
