package com.hotel.crescent.service.auth;

import com.hotel.crescent.exception.JWTSignerException;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParserBuilder;

public interface JWTSigner {
	
	JwtBuilder sign(JwtBuilder jwt) throws JWTSignerException;
	
	JwtParserBuilder setSignKey(JwtParserBuilder jwt) throws JWTSignerException;

}
