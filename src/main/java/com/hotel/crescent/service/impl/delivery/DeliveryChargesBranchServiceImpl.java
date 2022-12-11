package com.hotel.crescent.service.impl.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.factory.delivery.DeliveryChargesFactory;
import com.hotel.crescent.model.delivery.DeliveryChargesBranchDTO;
import com.hotel.crescent.model.delivery.DeliveryChargesDTO;
import com.hotel.crescent.repo.delivery.DeliveryChargesBranchRepository;
import com.hotel.crescent.service.delivery.DeliveryChargesService;

@Service
public class DeliveryChargesBranchServiceImpl implements DeliveryChargesService{
	
	static {
		DeliveryChargesFactory.register(Constants.BRANCH, DeliveryChargesBranchServiceImpl.class);
	}
	
	@Autowired
	DeliveryChargesBranchRepository repo;

	@Override
	public DeliveryChargesDTO get(DeliveryChargesDTO deliveryChargesDTO) {
		DeliveryChargesBranchDTO request = (DeliveryChargesBranchDTO)deliveryChargesDTO;
		return repo.findByBranchAndPincode(request.getBranchName(), request.getPincode());
	}

}
