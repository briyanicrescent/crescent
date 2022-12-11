package com.hotel.crescent.service.auth;

import com.hotel.crescent.exception.AccessTokenServiceException;
import com.hotel.crescent.exception.TokenExpiredException;
import com.hotel.crescent.model.auth.AuthInfo;

public interface AccessTokenService {
	
	String create(AuthInfo authInfo) throws AccessTokenServiceException;
	
	AuthInfo validate(String token) throws AccessTokenServiceException, TokenExpiredException;
	
	//Gets AuthInfo from token whether or not the token is expired
	AuthInfo getAuthInfo(String token) throws AccessTokenServiceException;
	
	boolean isTokenBlacklisted(AuthInfo authInfo, String token);
	
	void writeToBLCache(AuthInfo authInfo, String token);

	void writeToRefreshedCache(AuthInfo authInfo, String token);

	String readRefreshedCache(AuthInfo authInfo);

}
