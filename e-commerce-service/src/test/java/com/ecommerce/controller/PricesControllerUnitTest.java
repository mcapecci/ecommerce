package com.ecommerce.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.ecommerce.dto.response.PricesResponseDto;
import com.ecommerce.service.IPricesService;

/**
 * PricesControllerUnitTest
 * 
 * @author Eva Magalí Capecci
 *
 */
@RunWith(MockitoJUnitRunner.class)
@EnableWebMvc
public class PricesControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private IPricesService service;

	@InjectMocks
	private PricesController controller;

	private static String baseUrl = "/api/v1";
	private static MultiValueMap<String, String> params;
	
	private static PricesResponseDto responseDto;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).addPlaceholderValue("ecommerce.base-url", baseUrl)
				.build();

	}

	@BeforeClass
	public static void beforeClass() throws Exception {
		params = new LinkedMultiValueMap<>();
		params.add("brandId", "1");
		params.add("productId", "35455");
		params.set("date", "2023-01-14T16:00:00");
		
		String startDate = "2023-01-02T00:00:00";
		String endDate = "2023-02-06T00:00:00";
		responseDto = PricesResponseDto.builder().brandId(1L).productId(35455L)
				.currency("EUR").price(BigDecimal.valueOf(35.50)).startDate(LocalDateTime.parse(startDate))
				.endDate(LocalDateTime.parse(endDate))
				.build();
	}
	
	@Test
	public void getPriceByProductBrandAndDate() throws Exception {

		
		when(service.findByProductBrandAndDate(anyLong(), anyLong(), any())).thenReturn(responseDto);

		mockMvc.perform(get(baseUrl + "/price").params(params).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$.productId", Matchers.is(35455)))
		.andExpect(jsonPath("$.brandId", Matchers.is(1)));
	}

	/*
	@Test
	public void getPriceByProductBrandAndDate_ProductIdNotFound() throws Exception {

		when(service.findByProductBrandAndDate(1L, 35455L, LocalDateTime.parse("2023-01-14T16:00:00")))
				.thenThrow(new ResourceNotFoundException("Message test getPriceByProductBrandAndDate - ResourceNotFound"));

		mockMvc.perform(get(baseUrl + "/price").params(params)
				.content(objectMapper.writeValueAsString(any()))).andDo(MockMvcResultHandlers.print())
		.andExpect(status().isNotFound()).andExpect(jsonPath("$.httpCode", Matchers.is(404)))
				
				.andExpect(jsonPath("$.httpMessage", Matchers.is("Not Found")))
				.andExpect(jsonPath("$.moreInformation[0].field", Matchers.is("API")))
				.andExpect(jsonPath("$.moreInformation[0].value.message",
						Matchers.is("Message test findById - ResourceNotFound")))
				.andExpect(jsonPath("$.moreInformation[0].value.errorCode").value(4));
	}
	*/
	
}
