package com.farm.farm_management;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.farm.farm_management.pojo.BaseResponse;
import com.farm.farm_management.pojo.FarmData;
import com.farm.farm_management.pojo.HarvestData;
import com.farm.farm_management.pojo.PlantingData;
import com.farm.farm_management.repo.*;
import com.farm.farm_management.service.AppService;

import com.farm.farm_management.controller.ApplicationController;
import com.farm.farm_management.pojo.FarmData;
import com.farm.farm_management.pojo.HarvestData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
class FarmManagementApplicationTests {
	@Autowired
	CropTypeRepo cropTypeRepo;
	@Autowired
	SeasonRepo seasonRepo;
	@Autowired
	FarmRepo farmRepo;
	@Autowired
	PlantingRepo plantingRepo;
	@Autowired
	HarvestRepo harvestRepo;

	@InjectMocks
	ApplicationController applicationController;
	@Autowired
	AppService appService;
	@Mock
	BaseResponse baseRepository;
	@Test
	void contextLoads() {
	}
	@Test
	public void getListOfSeason() throws Exception {
		/*String uri = "/list_all_season";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//list_all_season
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		List<SeasonEntity> getAllSeason = seasonRepo.findAll();

		//Product[] productlist = super.mapFromJson(content, Product[].class);
		assertTrue(getAllSeason.stream().count() > 0);*/

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		AppService appService = new AppService();
		BaseResponse baseResponse = appService.listAllSeason();
		String code = baseResponse.getStatusCode();

		assertThat(baseResponse.getStatusCode()).isEqualTo(200);

	}
}
