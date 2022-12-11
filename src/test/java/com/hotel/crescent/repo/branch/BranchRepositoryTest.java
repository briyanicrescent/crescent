package com.hotel.crescent.repo.branch;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.model.branch.BranchDTO;
import com.hotel.crescent.model.branch.BranchFactory;

@DataJpaTest
public class BranchRepositoryTest {
	
	@Autowired
	BranchRepository branchRepo;
	
	@Autowired
	BranchFactory branchFactory;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@BeforeEach
	public void loadBranches() {
		List<BranchDTO> branches = branchFactory.getActiveInactiveBranches(2, 1);
		branches.forEach(branch->entityManager.persist(branch));
	}
	
	@Test
	public void testFindByStatus() {
		assertEquals(branchRepo.findByStatus(Constants.STATUS_A).size(), 2);
		assertEquals(branchRepo.findByStatus(null).size(), 3);
	}
	

}
