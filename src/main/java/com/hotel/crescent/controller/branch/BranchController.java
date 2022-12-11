package com.hotel.crescent.controller.branch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.crescent.model.Response;
import com.hotel.crescent.service.branch.BranchService;

@RestController
@RequestMapping(value="/branch")
public class BranchController {
	
	@Autowired
	BranchService branchService;
	
	@GetMapping(value = "/active")
	public Response<List<String>> getActiveBranches(){
		return new Response<List<String>>().success(branchService.getActiveBranches());
	}

}
