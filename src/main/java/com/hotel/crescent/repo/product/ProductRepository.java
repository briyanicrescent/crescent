package com.hotel.crescent.repo.product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotel.crescent.model.product.ProductDTO;

@Repository
public interface ProductRepository extends CrudRepository<ProductDTO, Integer>{
	
	@Query("select product from ProductDTO product, BranchDTO branch where product.branchId = branch.id and branch.name=:branchName "
			+ "and product.status=:status")
	public List<ProductDTO> findByBranchNameAndStatus(String branchName, String status);

}
