package com.example.userdetails.exceptions;

public class DaoException extends RuntimeException {
	
	private String msg;
	
	public DaoException(String msg) {
		super(msg);
		this.msg = msg;
	}
}
