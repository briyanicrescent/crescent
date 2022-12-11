package com.hotel.crescent.service.impl.auth;

import java.util.UUID;

import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.stereotype.Service;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.model.auth.AuthInfo;

@Service
public class RandomRefreshTokenService extends RefreshTokenServiceImpl{

	@Override
	public String create() {
		return UUID.randomUUID().toString();
	}
	
	public String writeToInProgressCache(AuthInfo authInfo) {
		String uuid = UUID.randomUUID().toString();
		cacheManager.getCache(Constants.ID_REFRESH_IN_PROGRESS).put(authInfo.getSubject(),uuid);
		return uuid;
	}
	
	public void evictInProgressCache(AuthInfo authInfo, String uuid) {
		ValueWrapper cacheRecord = cacheManager.getCache(Constants.ID_REFRESH_IN_PROGRESS).get(authInfo.getSubject());
		if(cacheRecord!=null && cacheRecord.get()!=null && cacheRecord.get().equals(uuid)) {
			cacheManager.getCache(Constants.ID_REFRESH_IN_PROGRESS).evict(authInfo.getSubject());
		}
	}

}
