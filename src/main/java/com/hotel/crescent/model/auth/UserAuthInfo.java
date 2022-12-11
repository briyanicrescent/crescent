package com.hotel.crescent.model.auth;

public class UserAuthInfo extends AuthInfo{
	
	public UserAuthInfo() {
		super();
	}
	
	public UserAuthInfo(Integer userId, String role){
		this.userId = userId;
		this.setRole(role);
	}
	
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}

	@Override
	public String getSubject() {
		return String.valueOf(userId);
	}

	@Override
	public String getType() {
		return this.getClass().getSimpleName();
	}
}
