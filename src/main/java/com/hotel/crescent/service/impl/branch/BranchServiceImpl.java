package com.hotel.crescent.service.impl.branch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.repo.branch.BranchRepository;
import com.hotel.crescent.service.branch.BranchService;

@Service
public class BranchServiceImpl implements BranchService{
	
	@Autowired
	BranchRepository branchRepo;
	
	public List<String> getActiveBranches(){
		return branchRepo.findByStatus(Constants.STATUS_A);
	}

}
