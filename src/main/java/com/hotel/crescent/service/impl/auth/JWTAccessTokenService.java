package com.hotel.crescent.service.impl.auth;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.exception.AccessTokenServiceException;
import com.hotel.crescent.exception.JWTSignerException;
import com.hotel.crescent.exception.TokenExpiredException;
import com.hotel.crescent.model.auth.AuthInfo;
import com.hotel.crescent.service.auth.AccessTokenService;
import com.hotel.crescent.service.auth.JWTSigner;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;

@Service
public class JWTAccessTokenService implements AccessTokenService{
	
	//Expiry in seconds
	@Value(value = "${auth.jwt.expiry-access-token}")
	Integer expiry;
	
	@Value(value = "${auth.jwt.bl-access-token-keep}")
	Integer blMaxCount;
	
	@Autowired
	JWTSigner signer;
	
	@Autowired
	CacheManager cacheManager;

	@Override
	public String create(AuthInfo authInfo) throws AccessTokenServiceException {
		LocalDateTime currentTime = LocalDateTime.now();
		JwtBuilder jwt = Jwts.builder()
			.setClaims(buildClaims(authInfo))
			.setSubject(String.valueOf(authInfo.getSubject()))
			.setIssuedAt(Timestamp.valueOf(currentTime))
			.setExpiration(Timestamp.valueOf(currentTime.plusSeconds(expiry)));
		try {
			return signer.sign(jwt).compact();
		}
		catch(JWTSignerException e) {
			throw new AccessTokenServiceException(e);
		}
	}
	
	private Map<String,Object> buildClaims(AuthInfo authInfo){
		Map<String,Object> claims = new HashMap<String,Object>();
		claims.put(Constants.AUTH, authInfo);
		return claims;
	}
	
	private AuthInfo getAuthClaim(Claims claims){
		return claims.get(Constants.AUTH, AuthInfo.class);
	}
	
	private AuthInfo customParseGetAuthClaim(Claims claims){
		Object obj = claims.get(Constants.AUTH);
		return AuthInfo.getMapper().convertValue(obj, AuthInfo.class);
	}

	@Override
	public AuthInfo validate(String token) throws AccessTokenServiceException, TokenExpiredException {
		try {
			JwtParserBuilder parserBuilder = signer.setSignKey(Jwts.parserBuilder());
			return customParseGetAuthClaim(parserBuilder.build().parseClaimsJws(token).getBody());
		}
		catch(JWTSignerException e) {
			throw new AccessTokenServiceException(e);
		}
		catch(ExpiredJwtException e) {
			throw new TokenExpiredException(e);
		}
	}

	@Override
	public AuthInfo getAuthInfo(String token) throws AccessTokenServiceException {
		try {
			JwtParserBuilder parserBuilder = signer.setSignKey(Jwts.parserBuilder());
			return customParseGetAuthClaim(parserBuilder.build().parseClaimsJws(token).getBody());
		}
		catch(JWTSignerException e) {
			throw new AccessTokenServiceException(e);
		}
		catch(ExpiredJwtException e) {
			return customParseGetAuthClaim(e.getClaims());
		}
	}

	@Override
	public boolean isTokenBlacklisted(AuthInfo authInfo, String token) {
		ValueWrapper cacheRecord = cacheManager.getCache(Constants.ID_BL_ACCESS_TOKEN).get(authInfo.getSubject());
		if(cacheRecord!=null) {
			Deque<String> blTokens = (Deque<String>)cacheManager.getCache(Constants.ID_BL_ACCESS_TOKEN)
					.get(authInfo.getSubject()).get();
			if(blTokens.contains(token)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void writeToBLCache(AuthInfo authInfo, String token) {
		ValueWrapper cacheRecord = cacheManager.getCache(Constants.ID_BL_ACCESS_TOKEN).get(authInfo.getSubject());
		if(cacheRecord!=null) {
			Deque<String> blTokens = (Deque<String>)cacheRecord.get();
			if(blTokens.size() >= blMaxCount) {
				blTokens.pop();
			}
			blTokens.add(token);
			cacheManager.getCache(Constants.ID_BL_ACCESS_TOKEN).put(authInfo.getSubject(), blTokens);
		}
	}
	
	@Override
	public void writeToRefreshedCache(AuthInfo authInfo, String token) {
		cacheManager.getCache(Constants.ID_REFRESHED_ACCESS_TOKEN).put(authInfo.getSubject(), token);
	}
	
	@Override
	public String readRefreshedCache(AuthInfo authInfo) {
		return (String)cacheManager.getCache(Constants.ID_REFRESHED_ACCESS_TOKEN).get(authInfo.getSubject()).get();
	}

}
