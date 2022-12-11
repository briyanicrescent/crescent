package com.hotel.crescent.service.auth;

import com.hotel.crescent.exception.AuthException;
import com.hotel.crescent.exception.TokenExpiredException;
import com.hotel.crescent.model.auth.AuthInfo;
import com.hotel.crescent.model.auth.IDTokenCredential;
import com.hotel.crescent.model.auth.OAuthToken;

public interface OAuthService {
	
	OAuthToken authorize(IDTokenCredential credential) throws AuthException;
	
	boolean isTokenBlacklisted(AuthInfo authInfo, String token);
	
	AuthInfo validateToken(String token) throws AuthException, TokenExpiredException;
	
	OAuthToken getRefreshedToken(OAuthToken token) throws AuthException;

}
