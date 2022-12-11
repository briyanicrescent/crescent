package com.hotel.crescent.factory.delivery;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import com.hotel.crescent.service.delivery.DeliveryChargesService;

public class DeliveryChargesFactory {

	public static final Map<String,Class<? extends DeliveryChargesService>> services = new HashMap<String,Class<? extends DeliveryChargesService>>();
	
	public static void register(String type, Class<? extends DeliveryChargesService> service) {
		if(StringUtils.hasText(type) && service!=null) {
			services.put(type, service);
		}
	}
	
	public static DeliveryChargesService get(ApplicationContext context, String type){
		if(services.containsKey(type))
			return context.getBean(services.get(type));
		return null;
	}

}
