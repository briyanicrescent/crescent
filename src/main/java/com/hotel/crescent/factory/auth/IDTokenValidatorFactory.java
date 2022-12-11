package com.hotel.crescent.factory.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import com.hotel.crescent.service.auth.IDTokenValidator;

public class IDTokenValidatorFactory {

	public static final Map<String,Class<? extends IDTokenValidator>> validators = new HashMap<String,Class<? extends IDTokenValidator>>();
	
	public static void register(String type, Class<? extends IDTokenValidator> validator) {
		if(StringUtils.hasText(type) && validator!=null) {
			validators.put(type, validator);
		}
	}
	
	public static IDTokenValidator get(ApplicationContext context, String type){
		if(validators.containsKey(type))
			return context.getBean(validators.get(type));
		return null;
	}

}
