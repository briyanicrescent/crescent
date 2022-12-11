package com.hotel.crescent.controller;

import java.net.BindException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.model.Response;
import com.hotel.crescent.service.exception.ExceptionService;

@ControllerAdvice
public class ExceptionController {
	
	Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@Autowired
	ExceptionService exceptionService;
	
	@ExceptionHandler(value = Exception.class)
    public <T> ResponseEntity<Response<T>> genericException(Exception exception) {		
		String errorId = exceptionService.generateErrorId();
		logger.error("Generic Exception :: Error Id :: "+errorId, exception);
		return new ResponseEntity<Response<T>>(new Response<T>().technicalError(errorId), HttpStatus.OK);
    }
	
	@ExceptionHandler(value = AccessDeniedException.class)
    public <T> ResponseEntity<Response<T>> accessDeniedException(Exception exception) {
		return new ResponseEntity<Response<T>>(new Response<T>().authError(Constants.ERROR_CODE_ACCESS_DENIED), HttpStatus.OK);
    }
	
	@ExceptionHandler(value = BindException.class)
    public <T> ResponseEntity<Response<T>> bindException(Exception exception) {
		String errorId = exceptionService.generateErrorId();
		logger.error("Input Validation Exception :: Error Id :: "+errorId, exception);
		return new ResponseEntity<Response<T>>(new Response<T>().technicalError(errorId), HttpStatus.OK);
    }
}
