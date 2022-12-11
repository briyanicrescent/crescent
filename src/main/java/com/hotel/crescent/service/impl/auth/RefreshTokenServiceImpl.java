package com.hotel.crescent.service.impl.auth;

import java.util.Deque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.model.auth.AuthInfo;
import com.hotel.crescent.service.auth.RefreshTokenService;

public abstract class RefreshTokenServiceImpl implements RefreshTokenService{
	
	@Autowired
	CacheManager cacheManager;

	@Value(value = "${auth.jwt.bl-refresh-token-keep}")
	Integer blMaxCount;
	
	@Override
	public void writeToBLCache(AuthInfo authInfo, String token) {
		ValueWrapper cacheRecord = cacheManager.getCache(Constants.ID_BL_REFRESH_TOKEN).get(authInfo.getSubject());
		if(cacheRecord!=null) {
			Deque<String> blTokens = (Deque<String>)cacheRecord.get();
			if(blTokens.size() >= blMaxCount) {
				blTokens.pop();
			}
			blTokens.add(token);
			cacheManager.getCache(Constants.ID_BL_REFRESH_TOKEN).put(authInfo.getSubject(), blTokens);
		}
	}

	@Override
	public void writeToCache(AuthInfo authInfo, String token) {
		cacheManager.getCache(Constants.ID_REFRESH_TOKEN).put(authInfo.getSubject(), token);	
	}
	
	@Override
	public boolean isValidToken(AuthInfo authInfo, String token) {
		String tokenInRecords = (String)cacheManager.getCache(Constants.ID_REFRESH_TOKEN)
				.get(authInfo.getSubject()).get();
		if(token.equals(tokenInRecords)) {
			return true;
		}
		return false;
	}
	
	@Override
	public boolean isTokenBlacklisted(AuthInfo authInfo, String token) {
		ValueWrapper cacheRecord = cacheManager.getCache(Constants.ID_BL_REFRESH_TOKEN).get(authInfo.getSubject());
		if(cacheRecord!=null) {
			Deque<String> blTokens = (Deque<String>)cacheRecord.get();
			if(blTokens.contains(token)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void invalidateAllTokens(AuthInfo authInfo) {
		String tokenInRecords = (String)cacheManager.getCache(Constants.ID_REFRESH_TOKEN)
				.get(authInfo.getSubject()).get();
		writeToBLCache(authInfo, tokenInRecords);
		cacheManager.getCache(Constants.ID_REFRESH_TOKEN).evict(authInfo.getSubject());
	}
	
	@Override
	public boolean isRefreshInProgress(AuthInfo authInfo) {
		ValueWrapper cacheRecord = cacheManager.getCache(Constants.ID_REFRESH_IN_PROGRESS).get(authInfo.getSubject());
		if(cacheRecord!=null && cacheRecord.get()!=null) {
			return true;
		}
		return false;
	}
	
	@Override
	public String readRefreshTokenCache(AuthInfo authInfo) {
		return (String)cacheManager.getCache(Constants.ID_REFRESH_TOKEN).get(authInfo.getSubject()).get();
	}

}
