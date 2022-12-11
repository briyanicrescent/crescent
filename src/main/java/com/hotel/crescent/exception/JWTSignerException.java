package com.hotel.crescent.exception;

public class JWTSignerException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public JWTSignerException(String string) {
		super(string);
	}
	
	public JWTSignerException(Throwable e) {
		super(e);
	}
}
