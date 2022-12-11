package com.hotel.crescent.service.auth;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.hotel.crescent.model.auth.IDToken;

public interface IDTokenValidator {
	
	public IDToken validate(String token) throws GeneralSecurityException, IOException;

}
