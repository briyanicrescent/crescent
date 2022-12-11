package com.hotel.crescent.service.impl.address;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.hotel.crescent.factory.address.AddressConsumerFactory;
import com.hotel.crescent.model.user.AddressDTO;
import com.hotel.crescent.repo.address.AddressRepository;
import com.hotel.crescent.service.address.AddressConsumer;
import com.hotel.crescent.service.address.AddressService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	AddressRepository repo;
	
	AddressConsumer consumer;

	@Override
	public Optional<AddressDTO> get(Integer id) {
		return repo.findById(id);
	}

	@Transactional
	@Override
	public AddressDTO createForConsumer(AddressDTO address) {
		address.buildWithUpdatedDetails();
		AddressDTO updatedAddress = repo.save(address);
		consumer = AddressConsumerFactory.get(context, address.getType());
		consumer.updateAddress(address.getConsumer(), updatedAddress.getId());
		return updatedAddress;
	}
	
	@Transactional
	@Override
	public AddressDTO update(AddressDTO address) {
		Optional<AddressDTO> dbAddress = repo.findById(address.getId());
		if(dbAddress.isPresent()) {
			AddressDTO updatingAddress = dbAddress.get();
			updatingAddress.setAddress(address.getAddress());
			updatingAddress.setPincode(address.getPincode());
			updatingAddress.buildWithUpdatedDetails();
			return repo.save(updatingAddress);
		}
		else {
			throw new EntityNotFoundException("Address not found");
		}
	}

}
