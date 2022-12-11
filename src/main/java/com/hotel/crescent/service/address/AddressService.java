package com.hotel.crescent.service.address;

import java.util.Optional;

import com.hotel.crescent.model.user.AddressDTO;

public interface AddressService {
	
	Optional<AddressDTO> get(Integer id);

	AddressDTO createForConsumer(AddressDTO address);

	AddressDTO update(AddressDTO address);

}
