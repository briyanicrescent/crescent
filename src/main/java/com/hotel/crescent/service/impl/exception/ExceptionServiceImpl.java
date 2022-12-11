package com.hotel.crescent.service.impl.exception;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.hotel.crescent.service.exception.ExceptionService;

@Service
public class ExceptionServiceImpl implements ExceptionService{

	@Override
	public String generateErrorId() {
		return UUID.randomUUID().toString();
	}
}
