package com.hotel.crescent.service.impl.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.factory.user.UserServiceFactory;
import com.hotel.crescent.model.auth.IDToken;
import com.hotel.crescent.model.user.UserDTO;
import com.hotel.crescent.repo.user.UserRepository;

@Service
public class UserServiceUsingEmail extends UserServiceImpl{
	
	static {
		UserServiceFactory.register(Constants.EMAIL, UserServiceUsingEmail.class);
	}
	
	@Autowired
	UserRepository userRepo;

	@Override
	public UserDTO getOrRegisterUser(IDToken idToken) {
		UserDTO userDTO = userRepo.findByEmail(idToken.getEmail());
		if(userDTO!=null) {
			return userDTO;
		}
		return registerUser(idToken);
	}
	
	private UserDTO registerUser(IDToken idToken) {
		UserDTO userDTO = new UserDTO(idToken.getName()).buildWithEmail(idToken.getEmail());
		userDTO.buildWithUpdatedDetails();
		return userRepo.save(userDTO);
	}

}
