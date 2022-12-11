package com.hotel.crescent.exception;

public class AccessTokenServiceException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public AccessTokenServiceException(String string) {
		super(string);
	}
	
	public AccessTokenServiceException(Throwable e) {
		super(e);
	}

}
