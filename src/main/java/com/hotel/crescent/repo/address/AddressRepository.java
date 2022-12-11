package com.hotel.crescent.repo.address;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotel.crescent.model.user.AddressDTO;

@Repository
public interface AddressRepository extends CrudRepository<AddressDTO, Integer>{

}
