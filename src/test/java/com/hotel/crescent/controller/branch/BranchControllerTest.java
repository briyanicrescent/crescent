package com.hotel.crescent.controller.branch;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hotel.crescent.constants.Constants;
import com.hotel.crescent.model.Response;
import com.hotel.crescent.model.branch.BranchFactory;
import com.hotel.crescent.service.branch.BranchService;

@WebMvcTest(BranchController.class)
@Import(BranchController.class)
@SpringJUnitConfig(classes = {BranchFactory.class})
public class BranchControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	BranchService branchService;
	
	@Autowired
	BranchFactory branchFactory;
	
	ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void testGetActiveBranches() throws Exception {
		Mockito.when(branchService.getActiveBranches()).thenReturn(branchFactory.getActiveInactiveBranchNames(2, 0));
		MvcResult mvcResult = mockMvc.perform(get("/branch/active"))
				.andExpect(status().isOk())
				.andReturn();
		Response<List<String>> result = mapper.readValue(mvcResult.getResponse().getContentAsString(),Response.class);
		assertEquals(result.getStatus(), Constants.REST_STATUS_SUCCESS);
		assertEquals(result.getResponse().size(), 2);
	}

}
