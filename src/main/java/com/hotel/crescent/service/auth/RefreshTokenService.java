package com.hotel.crescent.service.auth;

import com.hotel.crescent.model.auth.AuthInfo;

public interface RefreshTokenService {
	
	String create();
	
	boolean isValidToken(AuthInfo authInfo, String token);
	
	boolean isTokenBlacklisted(AuthInfo authInfo, String token);
	
	void invalidateAllTokens(AuthInfo authInfo);
	
	void writeToBLCache(AuthInfo authInfo, String token);
	
	void writeToCache(AuthInfo authInfo, String token);
	
	boolean isRefreshInProgress(AuthInfo authInfo);
	
	String writeToInProgressCache(AuthInfo authInfo);
	
	void evictInProgressCache(AuthInfo authInfo, String uuid);
	
	String readRefreshTokenCache(AuthInfo authInfo);

}
