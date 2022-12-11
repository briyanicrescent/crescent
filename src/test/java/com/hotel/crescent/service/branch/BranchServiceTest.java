package com.hotel.crescent.service.branch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.model.branch.BranchFactory;
import com.hotel.crescent.repo.branch.BranchRepository;
import com.hotel.crescent.service.impl.branch.BranchServiceImpl;

@SpringJUnitConfig(classes = {BranchServiceImpl.class, BranchFactory.class})
public class BranchServiceTest {
	
	@MockBean
	BranchRepository branchRepo;
	
	@Autowired
	BranchService branchService;
	
	@Autowired
	BranchFactory branchFactory;
	
	@Test
	public void testGetActiveBranches() {
		Mockito.when(branchRepo.findByStatus(Constants.STATUS_A)).thenReturn(branchFactory.getActiveInactiveBranchNames(2, 0));
		assertEquals(branchService.getActiveBranches().size(), 2);
	}

}
