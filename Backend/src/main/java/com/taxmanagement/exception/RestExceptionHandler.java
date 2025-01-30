package com.taxmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //adding globally to all components(handle exceptions across the entire application in a global, not duplicated manner.)
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)//define methods that handle exceptions thrown by controllers.
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException ex) {
		return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}

	// Other exception handlers...
}
