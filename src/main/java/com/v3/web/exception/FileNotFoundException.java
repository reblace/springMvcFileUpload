package com.v3.web.exception;

public class FileNotFoundException extends Exception {
	private static final long serialVersionUID = 2373355544549114019L;

	public FileNotFoundException(String msg, Throwable t){
		super(msg, t);
	}

	public FileNotFoundException(String msg){
		super(msg);
	}
}
