package com.lms.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lms.config.ResponseStructure;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(InvalidLoginException.class)
	public ResponseEntity<ResponseStructure<String>> handleInvalidLogin(InvalidLoginException ex) {
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setMessage(ex.getMessage());
		response.setData(null);
		return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserAlreadyExists(UserAlreadyExistsException ex) {
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.CONFLICT.value());
		response.setMessage(ex.getMessage());
		response.setData(null);
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(UserIdnotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleUserNotFound(UserIdnotFoundException ex) {
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage(ex.getMessage());
		response.setData(null);
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(FieldcannotbeEmpty.class)
	public ResponseEntity<ResponseStructure<String>> handleFieldEmpty(FieldcannotbeEmpty ex) {
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		response.setMessage(ex.getMessage());
		response.setData(null);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseStructure<String>> handleGenericException(Exception ex) {
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setMessage("An unexpected error occurred: " + ex.getMessage());
		response.setData(null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}