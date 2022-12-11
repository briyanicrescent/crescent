package com.hotel.crescent.model.delivery;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "delivery_charges_branch")
public class DeliveryChargesBranchDTO extends DeliveryChargesDTO{

	private Integer branchId;
	
	@NotBlank(groups = {ReadDeliveryCharges.class})
	@Transient
	private String branchName;
	
	@NotBlank(groups = {ReadDeliveryCharges.class})
	private String pincode;
	
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

}
