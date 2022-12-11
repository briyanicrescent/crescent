package com.hotel.crescent.service.impl.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.crescent.exception.AccessTokenServiceException;
import com.hotel.crescent.exception.AuthException;
import com.hotel.crescent.model.auth.AuthInfo;
import com.hotel.crescent.model.auth.OAuthToken;
import com.hotel.crescent.service.auth.RefreshTokenService;

@Service
public class OAuthServiceProxyImpl extends OAuthServiceImpl{
	
	@Autowired
	RefreshTokenService refreshTokenService;
	
	@Override
	public OAuthToken getRefreshedToken(OAuthToken token) throws AuthException {
		try {
			AuthInfo authInfo = accessTokenService.getAuthInfo(token.getAccessToken());
			if(refreshTokenService.isRefreshInProgress(authInfo)) {
				while(refreshTokenService.isRefreshInProgress(authInfo)) {
					Thread.sleep(1000);
				}
				OAuthToken refreshedToken = getRefreshedTokenFromCache(authInfo);
				if(refreshedToken == null) {
					return lockAndRefreshToken(token, authInfo);
				}
				return refreshedToken;
			}
			else {
				return lockAndRefreshToken(token, authInfo);
			}
		} catch (AccessTokenServiceException | InterruptedException e) {
			throw new AuthException(e);
		}
	}
	
	private OAuthToken lockAndRefreshToken(OAuthToken token, AuthInfo authInfo) throws AuthException {
		String uuid = null;
		try {
			uuid = refreshTokenService.writeToInProgressCache(authInfo);
			OAuthToken refreshedToken = super.getRefreshedToken(token, authInfo);
			return refreshedToken;
		}
		finally {
			refreshTokenService.evictInProgressCache(authInfo, uuid);
		}
	}
	
	private OAuthToken getRefreshedTokenFromCache(AuthInfo authInfo) {
		String accessToken = accessTokenService.readRefreshedCache(authInfo);
		if(accessToken!=null) {
			return new OAuthToken(accessToken, refreshTokenService.readRefreshTokenCache(authInfo));
		}
		return null;
	}

}
