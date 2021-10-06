package com.example.userdetails.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.userdetails.exceptions.DaoException;
import com.example.userdetails.exceptions.DelegationException;
import com.example.userdetails.exceptions.ErrorModel;

@RestControllerAdvice
public class ExceptionController{

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ExceptionHandler(value = DaoException.class)
	public ResponseEntity<Object> daoException(DaoException exception) {
		ErrorModel errorModel = new ErrorModel();
		errorModel.setTimestamp(new Date());
		errorModel.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorModel, HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(value = DelegationException.class)
	public ResponseEntity<Object> delegationException(DelegationException exception) {
		ErrorModel errorModel = new ErrorModel();
		errorModel.setTimestamp(new Date());
		errorModel.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorModel, HttpStatus.SERVICE_UNAVAILABLE);
	}
}
