package com.hotel.crescent.repo.branch;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hotel.crescent.model.branch.BranchDTO;

@Repository
public interface BranchRepository extends CrudRepository<BranchDTO, Integer>{
	
	@Query("select new java.lang.String(name) from BranchDTO where status=coalesce(:status,status)")
	List<String> findByStatus(String status);

}
