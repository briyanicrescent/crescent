package com.hotel.crescent.service.impl.auth;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.hotel.crescent.exception.AccessTokenServiceException;
import com.hotel.crescent.exception.AuthException;
import com.hotel.crescent.exception.TokenExpiredException;
import com.hotel.crescent.factory.auth.IDTokenValidatorFactory;
import com.hotel.crescent.model.auth.AuthInfo;
import com.hotel.crescent.model.auth.IDToken;
import com.hotel.crescent.model.auth.IDTokenCredential;
import com.hotel.crescent.model.auth.OAuthToken;
import com.hotel.crescent.service.auth.AccessTokenService;
import com.hotel.crescent.service.auth.AuthInfoService;
import com.hotel.crescent.service.auth.IDTokenValidator;
import com.hotel.crescent.service.auth.OAuthService;
import com.hotel.crescent.service.auth.RefreshTokenService;

public abstract class OAuthServiceImpl implements OAuthService{
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	AccessTokenService accessTokenService;
	
	@Autowired
	RefreshTokenService refreshTokenService;
	
	@Autowired
	AuthInfoService authInfoService;

	@Override
	public OAuthToken authorize(IDTokenCredential credential) throws AuthException {
		try {
			IDTokenValidator validator = IDTokenValidatorFactory.get(context, credential.getType());
			IDToken idToken = validator.validate(credential.getCredential());
			if(idToken == null) {
				throw new AuthException("Invalid OAuth Credential");
			}
			else {
				AuthInfo authInfo = authInfoService.get(idToken);
				OAuthToken oAuthToken = createOAuthToken(authInfo);
				refreshTokenService.writeToCache(authInfo, oAuthToken.getRefreshToken());
				return oAuthToken;
			}
		}
		catch(GeneralSecurityException | IOException | AccessTokenServiceException e) {
			throw new AuthException(e);
		}
	}

	@Override
	public boolean isTokenBlacklisted(AuthInfo authInfo, String token) {
		return accessTokenService.isTokenBlacklisted(authInfo, token);
	}

	@Override
	public AuthInfo validateToken(String token) throws AuthException, TokenExpiredException {
		try {
			return accessTokenService.validate(token);
		}
		catch(AccessTokenServiceException e) {
			throw new AuthException(e);
		}
	}

	public OAuthToken getRefreshedToken(OAuthToken token, AuthInfo authInfo) throws AuthException {
		try {
			if(authInfo==null) {
				authInfo = accessTokenService.getAuthInfo(token.getAccessToken());
			}
			if(refreshTokenService.isValidToken(authInfo, token.getRefreshToken())) {
				OAuthToken refreshedToken = createOAuthToken(authInfo);
				refreshTokenService.writeToCache(authInfo, refreshedToken.getRefreshToken());
				accessTokenService.writeToRefreshedCache(authInfo, refreshedToken.getAccessToken());
				refreshTokenService.writeToBLCache(authInfo, token.getRefreshToken());
				accessTokenService.writeToBLCache(authInfo, token.getAccessToken());
				return refreshedToken;
			}
			else if(refreshTokenService.isTokenBlacklisted(authInfo, token.getRefreshToken())){
				refreshTokenService.invalidateAllTokens(authInfo);
			}
		} catch (AccessTokenServiceException e) {
			throw new AuthException(e);
		}
		return null;
	}
	
	private OAuthToken createOAuthToken(AuthInfo authInfo) throws AccessTokenServiceException {
		String accessToken = accessTokenService.create(authInfo);
		String refreshToken = refreshTokenService.create();
		return new OAuthToken(accessToken,refreshToken);
	}

}
