package com.hotel.crescent.model.auth;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

public class OAuthToken implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OAuthToken(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}
	
	@NotBlank
	private String accessToken;
	@NotBlank
	private String refreshToken;
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
