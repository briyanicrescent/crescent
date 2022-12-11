package com.hotel.crescent.repo.delivery;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hotel.crescent.model.delivery.DeliveryChargesBranchDTO;

public interface DeliveryChargesBranchRepository extends CrudRepository<DeliveryChargesBranchDTO, Integer>{
	
	@Query("select charges from DeliveryChargesBranchDTO charges, BranchDTO branch where charges.branchId = branch.id and "
			+ "branch.name=:branchName and charges.pincode=:pincode")
	public DeliveryChargesBranchDTO findByBranchAndPincode(String branchName, String pincode);

}
