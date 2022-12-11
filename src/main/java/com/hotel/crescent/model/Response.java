package com.hotel.crescent.model;

import org.jboss.logging.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.crescent.constants.Constants;

public class Response<T> {
	
	Logger logger = Logger.getLogger(Response.class);
	
	ObjectMapper mapper = new ObjectMapper();
	
	private String status;
	private String errormsg;
	private String errortype;
	private String errorcode;
	private String errorid;
	private T response;
	
	public Response<T> success(T t){
		this.status = Constants.REST_STATUS_SUCCESS;
		this.response = t;
		return this;
	}
	
	public Response<T> technicalError(String errorId){
		this.status = Constants.REST_STATUS_FAILED;
		this.errortype = Constants.ERROR_TYPE_TECHNICAL;
		this.errorcode = Constants.ERROR_CODE_TECHNICAL;
		this.errorid = errorId;
		return this;
	}
	
	public Response<T> authError(String errorCode){
		this.status = Constants.REST_STATUS_FAILED;
		this.errortype = Constants.ERROR_TYPE_AUTH;
		this.errorcode = errorCode;
		return this;
	}
	
	public String toString(){
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			logger.error(e);
		}
		return null;
	}

	public String getStatus() {
		return status;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public String getErrortype() {
		return errortype;
	}

	public String getErrorcode() {
		return errorcode;
	}

	public String getErrorid() {
		return errorid;
	}

	public T getResponse() {
		return response;
	}
}

