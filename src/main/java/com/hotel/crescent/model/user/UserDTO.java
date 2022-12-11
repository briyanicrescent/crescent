package com.hotel.crescent.model.user;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.groups.user.CreateUser;
import com.hotel.crescent.groups.user.UpdateUser;
import com.hotel.crescent.model.BaseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "user")
public class UserDTO extends BaseDTO{
	
	public UserDTO() {
	}
	
	public UserDTO(String name) {
		this.name = name;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(groups = UpdateUser.class)
	private Integer id;
	
	@NotBlank(groups = CreateUser.class)
	private String name;
	
	@NotBlank(groups = CreateUser.class)
	@Email
	private String email;
	
	@NotBlank(groups = CreateUser.class)
	private String mobileNo;
	
	@NotBlank(groups = CreateUser.class)
	private String role;
	
	@NotBlank(groups = CreateUser.class)
	private Integer addressId;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Integer getAddressId() {
		return addressId;
	}
	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	} 
	
	public UserDTO buildWithEmail(String email) {
		this.email = email;
		this.role = Constants.CUSTOMER;
		return this;
	}

}
