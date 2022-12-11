package com.hotel.crescent.repo.user;

import org.springframework.data.repository.CrudRepository;

import com.hotel.crescent.model.user.UserDTO;

public interface UserRepository extends CrudRepository<UserDTO, Integer>{
	
	UserDTO findByEmail(String email);

}
