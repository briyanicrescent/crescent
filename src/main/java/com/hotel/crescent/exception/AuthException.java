package com.hotel.crescent.exception;

public class AuthException extends Exception{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public AuthException(String string) {
		super(string);
	}
	
	public AuthException(Throwable e) {
		super(e);
	}
}
