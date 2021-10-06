package com.example.userdetails.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		List<ErrorModel> l = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			ErrorModel em = new ErrorModel();
			em.setTimestamp(new Date());
			em.setMessage(error.getDefaultMessage());
			em.setDetails(((FieldError) error).getField());
			l.add(em);
		});
		return new ResponseEntity<>(l, HttpStatus.SERVICE_UNAVAILABLE);
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
