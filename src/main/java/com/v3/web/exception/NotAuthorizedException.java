package com.v3.web.exception;

public class NotAuthorizedException extends Exception {
	private static final long serialVersionUID = -2543458906196843827L;

	public NotAuthorizedException(String msg, Throwable t){
		super(msg, t);
	}

	public NotAuthorizedException(String msg){
		super(msg);
	}
}
