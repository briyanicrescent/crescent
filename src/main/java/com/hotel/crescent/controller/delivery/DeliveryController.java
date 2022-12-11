package com.hotel.crescent.controller.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.crescent.factory.delivery.DeliveryChargesFactory;
import com.hotel.crescent.model.Response;
import com.hotel.crescent.model.delivery.DeliveryChargesDTO;
import com.hotel.crescent.model.delivery.ReadDeliveryCharges;
import com.hotel.crescent.service.delivery.DeliveryChargesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/delivery")
@Validated
public class DeliveryController {
	
	@Autowired
	ApplicationContext context;
	
	DeliveryChargesService chargesService;
	
	@PostMapping(value = "/charges")
	@Validated(value = {ReadDeliveryCharges.class})
	public Response<DeliveryChargesDTO> getCharges(@Valid @RequestBody DeliveryChargesDTO request) {
		chargesService = DeliveryChargesFactory.get(context, request.getType());
		return new Response<DeliveryChargesDTO>().success(chargesService.get(request));
	}

}
