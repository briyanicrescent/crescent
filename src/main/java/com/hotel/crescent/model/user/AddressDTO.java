package com.hotel.crescent.model.user;

import com.hotel.crescent.groups.address.CreateAddress;
import com.hotel.crescent.groups.address.UpdateAddress;
import com.hotel.crescent.model.BaseDTO;
import com.hotel.crescent.model.address.AddressConsumerDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "address")
public class AddressDTO extends BaseDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotBlank(groups = UpdateAddress.class)
	private Integer id;
	
	@NotBlank(groups = CreateAddress.class)
	private String address;
	
	@NotBlank(groups = CreateAddress.class)
	private String pincode;
	
	@Transient
	@NotBlank(groups = CreateAddress.class)
	private String type;
	
	@Transient
	@NotBlank(groups = CreateAddress.class)
	private AddressConsumerDTO consumer;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public AddressConsumerDTO getConsumer() {
		return consumer;
	}
	public void setConsumer(AddressConsumerDTO consumer) {
		this.consumer = consumer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
