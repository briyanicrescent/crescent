package com.hotel.crescent.model.branch;

import java.util.ArrayList;
import java.util.List;

import org.mockito.Mockito;
import org.springframework.stereotype.Component;

import com.hotel.crescent.constants.Constants;

@Component
public class BranchFactory {
	
	public BranchDTO getActiveBranch() {
		return new BranchDTO(Mockito.anyString(), Mockito.anyString(), Constants.STATUS_A);
	}
	
	public BranchDTO getInactiveBranch() {
		return new BranchDTO(Mockito.anyString(), Mockito.anyString(), Constants.STATUS_I);
	}
	
	public List<BranchDTO> getActiveInactiveBranches(int activeCount, int inactiveCount) {
		List<BranchDTO> list = new ArrayList<BranchDTO>();
		while(activeCount>0) {
			list.add(getActiveBranch());
			activeCount--;
		}
		while(inactiveCount>0) {
			list.add(getInactiveBranch());
			inactiveCount--;
		}
		return list;
	}
	
	public List<String> getActiveInactiveBranchNames(int activeCount, int inactiveCount) {
		List<String> list = new ArrayList<String>();
		while(activeCount>0) {
			list.add("branch"+activeCount);
			activeCount--;
		}
		while(inactiveCount>0) {
			list.add("branch"+inactiveCount);
			inactiveCount--;
		}
		return list;
	}

}
