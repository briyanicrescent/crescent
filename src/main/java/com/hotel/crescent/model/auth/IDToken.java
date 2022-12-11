package com.hotel.crescent.model.auth;

public abstract class IDToken {
	
	public abstract String getType();
	public abstract String getName();
	public abstract String getEmail();
	public abstract String getMobileNo();

}
