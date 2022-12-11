package com.hotel.crescent.controller.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.crescent.model.Response;
import com.hotel.crescent.model.product.ProductDTO;
import com.hotel.crescent.service.product.ProductService;

@RestController
@RequestMapping(value="/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping(value = "/{branchName}")
	public Response<List<ProductDTO>> getActiveProducts(@PathVariable String branchName){
		return new Response<List<ProductDTO>>().success(productService.getActiveProducts(branchName));
	}

}
