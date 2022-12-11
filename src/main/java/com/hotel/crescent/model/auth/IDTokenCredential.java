package com.hotel.crescent.model.auth;

import jakarta.validation.constraints.NotBlank;

public class IDTokenCredential {
	
	@NotBlank
	private String credential;
	@NotBlank
	private String type;
	
	public String getCredential() {
		return credential;
	}
	public void setCredential(String credential) {
		this.credential = credential;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
