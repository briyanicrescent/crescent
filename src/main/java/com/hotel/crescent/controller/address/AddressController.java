package com.hotel.crescent.controller.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.crescent.model.Response;
import com.hotel.crescent.model.user.AddressDTO;
import com.hotel.crescent.service.address.AddressService;

@RestController
@RequestMapping("/address")
@Validated
public class AddressController {
	
	@Autowired
	AddressService addressService;

	@PreAuthorize("hasAnyRole('A','C')")
	@GetMapping(value = "/{id}")
	public Response<AddressDTO> get(@PathVariable Integer id){
		return new Response<AddressDTO>().success(addressService.get(id).get());
	}
	
	@PreAuthorize("hasAnyRole('A','C')")
	@PutMapping
	public Response<AddressDTO> createForConsumer(@RequestBody AddressDTO address){
		return new Response<AddressDTO>().success(addressService.createForConsumer(address));
	}
	
	@PreAuthorize("hasAnyRole('A','C')")
	@PatchMapping
	public Response<AddressDTO> update(@RequestBody AddressDTO address){
		return new Response<AddressDTO>().success(addressService.update(address));
	}
}
