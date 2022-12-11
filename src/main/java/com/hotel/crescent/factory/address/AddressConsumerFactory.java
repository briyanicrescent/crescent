package com.hotel.crescent.factory.address;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import com.hotel.crescent.service.address.AddressConsumer;

public class AddressConsumerFactory {
	
public static final Map<String,Class<? extends AddressConsumer>> services = new HashMap<String,Class<? extends AddressConsumer>>();
	
	public static void register(String type, Class<? extends AddressConsumer> service) {
		if(StringUtils.hasText(type) && service!=null) {
			services.put(type, service);
		}
	}
	
	public static AddressConsumer get(ApplicationContext context, String type){
		if(services.containsKey(type))
			return context.getBean(services.get(type));
		return null;
	}

}
