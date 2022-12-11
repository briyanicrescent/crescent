package com.hotel.crescent.service.impl.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.hotel.crescent.factory.user.UserServiceFactory;
import com.hotel.crescent.model.auth.IDToken;
import com.hotel.crescent.model.auth.UserAuthInfo;
import com.hotel.crescent.model.user.UserDTO;
import com.hotel.crescent.service.auth.AuthInfoService;
import com.hotel.crescent.service.user.UserService;

@Service
public class UserAuthInfoService implements AuthInfoService{
	
	@Autowired
	ApplicationContext context;
	
	UserService userService;

	@Override
	public UserAuthInfo get(IDToken idToken) {
		userService = UserServiceFactory.get(context, UserServiceFactory.userServices.get(idToken.getType()));
		UserDTO userDTO = userService.getOrRegisterUser(idToken);
		return new UserAuthInfo(userDTO.getId(), userDTO.getRole());
	}
	
	public UserAuthInfo get() {
		return (UserAuthInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

}
