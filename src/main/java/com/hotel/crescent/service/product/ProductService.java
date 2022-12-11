package com.hotel.crescent.service.product;

import java.util.List;

import com.hotel.crescent.model.product.ProductDTO;

public interface ProductService {

	List<ProductDTO> getActiveProducts(String branchName);

}
