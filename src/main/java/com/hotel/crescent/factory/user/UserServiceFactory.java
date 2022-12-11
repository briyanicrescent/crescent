package com.hotel.crescent.factory.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.service.user.UserService;

public class UserServiceFactory {

	public static final Map<String,Class<? extends UserService>> services = new HashMap<String,Class<? extends UserService>>();
	
	public static final Map<String,String> userServices = new HashMap<String,String>();
	
	static {
		userServices.put(Constants.GOOGLE, Constants.EMAIL);
	}
	
	public static void register(String type, Class<? extends UserService> service) {
		if(StringUtils.hasText(type) && service!=null) {
			services.put(type, service);
		}
	}
	
	public static UserService get(ApplicationContext context, String type){
		if(services.containsKey(type))
			return context.getBean(services.get(type));
		return null;
	}

}
