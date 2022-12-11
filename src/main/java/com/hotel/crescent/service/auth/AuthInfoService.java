package com.hotel.crescent.service.auth;

import com.hotel.crescent.model.auth.AuthInfo;
import com.hotel.crescent.model.auth.IDToken;

public interface AuthInfoService {
	
	AuthInfo get(IDToken idToken);
	
	AuthInfo get();

}
