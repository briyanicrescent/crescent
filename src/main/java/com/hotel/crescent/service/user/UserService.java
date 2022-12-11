package com.hotel.crescent.service.user;

import java.util.Optional;

import com.hotel.crescent.exception.AuthException;
import com.hotel.crescent.model.auth.IDToken;
import com.hotel.crescent.model.user.UserDTO;

public interface UserService{

	UserDTO getOrRegisterUser(IDToken idToken);
	
	Optional<UserDTO> get();
	
	UserDTO update(UserDTO userDTO) throws AuthException;

}
