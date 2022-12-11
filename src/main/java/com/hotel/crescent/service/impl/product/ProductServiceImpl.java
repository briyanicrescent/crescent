package com.hotel.crescent.service.impl.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.model.product.ProductDTO;
import com.hotel.crescent.repo.product.ProductRepository;
import com.hotel.crescent.service.product.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductRepository productRepo;

	@Override
	public List<ProductDTO> getActiveProducts(String branchName) {
		return productRepo.findByBranchNameAndStatus(branchName, Constants.STATUS_A);
	}

}
