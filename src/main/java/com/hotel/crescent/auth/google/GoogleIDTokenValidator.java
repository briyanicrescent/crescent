package com.hotel.crescent.auth.google;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.factory.auth.IDTokenValidatorFactory;
import com.hotel.crescent.model.auth.IDToken;
import com.hotel.crescent.service.auth.IDTokenValidator;

@Component
public class GoogleIDTokenValidator implements IDTokenValidator{
	
	static {
		IDTokenValidatorFactory.register(Constants.GOOGLE, GoogleIDTokenValidator.class);
	}
	
	@Value(value = "${auth.google.client-id}")
	String clientId;

	@Override
	public IDToken validate(String token) throws GeneralSecurityException, IOException {
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
			    .setAudience(Collections.singletonList(clientId))
			    .build();
		GoogleIdToken idToken = verifier.verify(token);
		if(idToken!=null) {
			return new GoogleIDToken(idToken);
		}
		return null; 
	}

}
