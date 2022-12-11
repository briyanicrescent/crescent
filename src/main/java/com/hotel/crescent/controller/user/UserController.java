
package com.hotel.crescent.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.crescent.exception.AuthException;
import com.hotel.crescent.groups.user.UpdateUser;
import com.hotel.crescent.model.Response;
import com.hotel.crescent.model.user.UserDTO;
import com.hotel.crescent.service.user.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/user")
@Validated
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping(value = "/details")
	@PreAuthorize("hasAnyRole('A','C')")
	public Response<UserDTO> get(){
		return new Response<UserDTO>().success(userService.get().orElse(null));
	}
	
	@PatchMapping
	@PreAuthorize("hasAnyRole('A','C')")
	@Validated(value = {UpdateUser.class})
	public Response<UserDTO> update(@Valid @RequestBody UserDTO userDTO) throws AuthException{
		return new Response<UserDTO>().success(userService.update(userDTO));
	}
	
}
