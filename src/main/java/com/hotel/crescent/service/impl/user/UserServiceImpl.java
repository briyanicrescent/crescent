package com.hotel.crescent.service.impl.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.exception.AuthException;
import com.hotel.crescent.factory.address.AddressConsumerFactory;
import com.hotel.crescent.model.address.AddressConsumerDTO;
import com.hotel.crescent.model.auth.UserAuthInfo;
import com.hotel.crescent.model.user.UserDTO;
import com.hotel.crescent.repo.user.UserRepository;
import com.hotel.crescent.service.address.AddressConsumer;
import com.hotel.crescent.service.auth.AuthInfoService;
import com.hotel.crescent.service.user.UserService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;

@Service
public abstract class UserServiceImpl implements UserService, AddressConsumer{
	
	static {
		AddressConsumerFactory.register(Constants.USER, UserServiceImpl.class);
	}
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	AuthInfoService userAuthInfoService;
	
	//Get User Details based on logged in user auth info
	@Override
	public Optional<UserDTO> get() {
		UserAuthInfo authInfo = (UserAuthInfo) userAuthInfoService.get();
		return userRepo.findById(authInfo.getUserId());
	}
	
	@Transactional
	@Override
	public UserDTO update(UserDTO userDTO) throws AuthException {
		UserAuthInfo authInfo = (UserAuthInfo) userAuthInfoService.get();
		if(authInfo.getUserId().equals(userDTO.getId()) || authInfo.getRole().equals(Constants.ADMIN)) {
			Optional<UserDTO> dbUserDTO = userRepo.findById(userDTO.getId());
			if(dbUserDTO.isPresent()) {
				UserDTO updatingUserDTO = dbUserDTO.get();
				updatingUserDTO.setName(userDTO.getName());
				updatingUserDTO.setMobileNo(userDTO.getMobileNo());
				updatingUserDTO.buildWithUpdatedDetails();
				return userRepo.save(updatingUserDTO);
			}
			else {
				throw new EntityNotFoundException("User not found");
			}
		}
		else {
			throw new AuthException("Unauthorized Action :: Update User");
		}
	}
	

	@Transactional(value = TxType.REQUIRED)
	@Override
	public void updateAddress(AddressConsumerDTO consumer, Integer id) {
		Optional<UserDTO> dbUserDTO = userRepo.findById(consumer.getId());
		if(dbUserDTO.isPresent()) {
			UserDTO updatingUserDTO = dbUserDTO.get();
			updatingUserDTO.setAddressId(id);
			updatingUserDTO.buildWithUpdatedDetails();
			userRepo.save(updatingUserDTO);
		}
		else {
			throw new EntityNotFoundException("User not found");
		}
		
	}

}
