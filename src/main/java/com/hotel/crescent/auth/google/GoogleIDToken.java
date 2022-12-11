package com.hotel.crescent.auth.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.model.auth.IDToken;

public class GoogleIDToken extends IDToken{
	
	private GoogleIdToken token;
	
	GoogleIDToken(GoogleIdToken token){
		this.token = token;
	}

	@Override
	public String getName() {
		return (String)token.getPayload().get("name");
	}

	@Override
	public String getEmail() {
		return token.getPayload().getEmail();
	}

	@Override
	public String getMobileNo() {
		return "";
	}

	@Override
	public String getType() {
		return Constants.GOOGLE;
	}

	

}
