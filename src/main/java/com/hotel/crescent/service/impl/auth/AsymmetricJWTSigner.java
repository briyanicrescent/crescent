package com.hotel.crescent.service.impl.auth;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hotel.crescent.exception.JWTSignerException;
import com.hotel.crescent.service.auth.JWTSigner;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;

@Service
public class AsymmetricJWTSigner implements JWTSigner{
	
	@Value(value = "${auth.jwt.key-store-path}")
	String keyStorePath;
	
	@Value(value = "${auth.jwt.key-store-password}")
	char[] keyStorePassword;
	
	@Value(value = "${auth.jwt.key-alias}")
	String keyAlias;
	
	@Value(value = "${auth.jwt.key-password}")
	char[] keyPassword;
	
	KeyStore keyStore;
	
	@PostConstruct
	public void init() throws JWTSignerException {
		try {
			keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
			keyStore.load(new FileInputStream(keyStorePath), keyStorePassword);
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			throw new JWTSignerException(e);
		}
	}

	@Override
	public JwtBuilder sign(JwtBuilder jwt) throws JWTSignerException {
	    return jwt.signWith(getPrivateKey(), SignatureAlgorithm.RS512);	
	}

	@Override
	public JwtParserBuilder setSignKey(JwtParserBuilder jwt) throws JWTSignerException {
		return jwt.setSigningKey(getPublicKey());
	}
	
	public Key getPrivateKey() throws JWTSignerException {
		try {
			return keyStore.getKey(keyAlias, keyPassword);
		} catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
			throw new JWTSignerException(e);
		}
	}
	
	public PublicKey getPublicKey() throws JWTSignerException {
		try {
			return keyStore.getCertificate(keyAlias).getPublicKey();
		} catch (KeyStoreException e) {
			throw new JWTSignerException(e);
		}
	}

}
