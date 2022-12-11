package com.hotel.crescent.exception;

public class TokenExpiredException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public TokenExpiredException(String string) {
		super(string);
	}
	
	public TokenExpiredException(Throwable e) {
		super(e);
	}
}

