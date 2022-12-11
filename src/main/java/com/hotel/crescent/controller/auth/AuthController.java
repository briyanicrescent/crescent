package com.hotel.crescent.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.crescent.exception.AuthException;
import com.hotel.crescent.model.Response;
import com.hotel.crescent.model.auth.IDTokenCredential;
import com.hotel.crescent.model.auth.OAuthToken;
import com.hotel.crescent.service.auth.OAuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {
	
	@Autowired
	OAuthService oAuthService;
	
	@PostMapping(value = "/authorize")
	public Response<OAuthToken> getAccessToken(@Valid @RequestBody IDTokenCredential credential) throws AuthException {
		return new Response<OAuthToken>().success(oAuthService.authorize(credential));
	}
	
	@PostMapping(value = "/getRefreshedToken")
	public Response<OAuthToken> getRefreshedToken(@Valid @RequestBody OAuthToken token) throws AuthException {
		return new Response<OAuthToken>().success(oAuthService.getRefreshedToken(token));
	}

}
