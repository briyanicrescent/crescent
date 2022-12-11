package com.hotel.crescent.model;

import java.time.LocalDate;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseDTO {
	
	private Integer updatedBy;
	private LocalDate updatedDate;
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public BaseDTO buildWithUpdatedDetails() {
		this.updatedBy = 1;
		this.updatedDate = LocalDate.now();
		return this;
	}

}
