package com.v3.web.exception;

public class UnsupportedFormatException extends Exception {
	private static final long serialVersionUID = -5662969451225657881L;

	public UnsupportedFormatException(String msg, Throwable t) {
		super(msg, t);
	}

	public UnsupportedFormatException(String msg) {
		super(msg);
	}
}
