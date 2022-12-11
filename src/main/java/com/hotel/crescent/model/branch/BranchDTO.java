package com.hotel.crescent.model.branch;

import com.hotel.crescent.model.BaseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "branch")
public class BranchDTO extends BaseDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Integer addressId;
	private String contactNo;
	private Double openingTime;
	private Double closingTime;
	private String status;
	
	public BranchDTO() {
		super();
	}
	
	public BranchDTO(String name, String contactNo, String status) {
		this.name = name;
		this.contactNo = contactNo;
		this.status = status;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Double getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(Double openingTime) {
		this.openingTime = openingTime;
	}
	public Double getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(Double closingTime) {
		this.closingTime = closingTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
