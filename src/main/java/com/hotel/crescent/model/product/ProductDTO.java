package com.hotel.crescent.model.product;

import com.hotel.crescent.model.BaseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")
public class ProductDTO extends BaseDTO{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String nameTamil;
	private String description;
	private String descriptionTamil;
	private String image;
	private Double price;
	private Integer branchId;
	private String status;
	private boolean isBreakfast;
	private boolean isLunch;
	private boolean isDinner;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescriptionTamil() {
		return descriptionTamil;
	}
	public void setDescriptionTamil(String descriptionTamil) {
		this.descriptionTamil = descriptionTamil;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getBranchId() {
		return branchId;
	}
	public void setBranchId(Integer branchId) {
		this.branchId = branchId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isBreakfast() {
		return isBreakfast;
	}
	public void setBreakfast(boolean isBreakfast) {
		this.isBreakfast = isBreakfast;
	}
	public boolean isLunch() {
		return isLunch;
	}
	public void setLunch(boolean isLunch) {
		this.isLunch = isLunch;
	}
	public boolean isDinner() {
		return isDinner;
	}
	public void setDinner(boolean isDinner) {
		this.isDinner = isDinner;
	}
	public String getNameTamil() {
		return nameTamil;
	}
	public void setNameTamil(String nameTamil) {
		this.nameTamil = nameTamil;
	}

}
