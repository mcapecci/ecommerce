package com.ecommerce.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.ecommerce.configuration.TestConfiguration;

/**
 * PricesControllerTest
 * 
 * @author Eva Magalí Capecci
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = TestConfiguration.class)
@ActiveProfiles("local")
@TestPropertySource("classpath:application-local.yml")
public class PricesControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	private static String baseUrl = "/api/v1";
	private static MultiValueMap<String, String> params;

	@Before
	public void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@BeforeClass
	public static void beforeClass() throws Exception {
		params = new LinkedMultiValueMap<>();
		params.add("brandId", "1");
		params.add("productId", "35455");
		params.add("date", "");
		params.add("sortBy", "id");
		params.add("direction", "ASC");
		params.add("page", "0");
		params.add("size", "5");
	}

	/**
	 * Test 1
	 * 
	 * When:
	 * 
	 * petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
	 * 
	 * Then: result success.
	 */
	@Test
	public void getAllTest1() throws Exception {
		params.set("date", "2020-06-14T10:00:00");

		mockMvc.perform(get(baseUrl + "/prices").params(params)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.content", hasSize(1)))
				.andExpect(jsonPath("$.content[0].productId", Matchers.is(35455)))
				.andExpect(jsonPath("$.content[0].brandId", Matchers.is(1)))
				.andExpect(jsonPath("$.content[0].startDate", Matchers.is("2020-06-14T00:00:00")))
				.andExpect(jsonPath("$.content[0].endDate", Matchers.is("2020-12-31T23:59:59")))
				.andExpect(jsonPath("$.content[0].price", Matchers.is(35.50)))
				.andExpect(jsonPath("$.content[0].currency", Matchers.is("EUR")))
				.andExpect(jsonPath("$.content[0].priceList", Matchers.is(1)));

	}

	/**
	 * Test 2
	 * 
	 * When:
	 * 
	 * petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
	 *
	 * 
	 * Then: result success.
	 */
	@Test
	public void getAllTest2() throws Exception {

		params.set("date", "2020-06-14T16:00:00");

		mockMvc.perform(get(baseUrl + "/prices").params(params)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.content", hasSize(1)))
				.andExpect(jsonPath("$.content[0].productId", Matchers.is(35455)))
				.andExpect(jsonPath("$.content[0].brandId", Matchers.is(1)))
				.andExpect(jsonPath("$.content[0].startDate", Matchers.is("2020-06-14T15:00:00")))
				.andExpect(jsonPath("$.content[0].endDate", Matchers.is("2020-06-14T18:30:00")))
				.andExpect(jsonPath("$.content[0].price", Matchers.is(25.45)))
				.andExpect(jsonPath("$.content[0].currency", Matchers.is("EUR")))
				.andExpect(jsonPath("$.content[0].priceList", Matchers.is(2)));

	}

	/**
	 * Test 3
	 * 
	 * When: :
	 * 
	 * petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
	 *
	 * Then: result success.
	 */
	@Test
	public void getAllTest3() throws Exception {

		params.set("date", "2020-06-14T21:00:00");

		mockMvc.perform(get(baseUrl + "/prices").params(params)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.content", hasSize(1)))
				.andExpect(jsonPath("$.content[0].productId", Matchers.is(35455)))
				.andExpect(jsonPath("$.content[0].brandId", Matchers.is(1)))
				.andExpect(jsonPath("$.content[0].startDate", Matchers.is("2020-06-14T00:00:00")))
				.andExpect(jsonPath("$.content[0].endDate", Matchers.is("2020-12-31T23:59:59")))
				.andExpect(jsonPath("$.content[0].price", Matchers.is(35.50)))
				.andExpect(jsonPath("$.content[0].currency", Matchers.is("EUR")))
				.andExpect(jsonPath("$.content[0].priceList", Matchers.is(1)));

	}

	/**
	 * Test 4
	 * 
	 * When:
	 * 
	 * petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
	 * 
	 * Then: result success.
	 */
	@Test
	public void getAllTest4() throws Exception {

		params.set("date", "2020-06-15T10:00:00");

		mockMvc.perform(get(baseUrl + "/prices").params(params)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.content", hasSize(1)))
				.andExpect(jsonPath("$.content[0].productId", Matchers.is(35455)))
				.andExpect(jsonPath("$.content[0].brandId", Matchers.is(1)))
				.andExpect(jsonPath("$.content[0].startDate", Matchers.is("2020-06-15T00:00:00")))
				.andExpect(jsonPath("$.content[0].endDate", Matchers.is("2020-06-15T11:00:00")))
				.andExpect(jsonPath("$.content[0].price", Matchers.is(30.50)))
				.andExpect(jsonPath("$.content[0].currency", Matchers.is("EUR")))
				.andExpect(jsonPath("$.content[0].priceList", Matchers.is(3)));

	}

	/**
	 * Test 5
	 * 
	 * When:
	 * 
	 * petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
	 * 
	 * Then: result success.
	 */
	@Test
	public void getAllTest5() throws Exception {

		params.set("date", "2020-06-16T21:00:00");

		mockMvc.perform(get(baseUrl + "/prices").params(params)).andDo(MockMvcResultHandlers.print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.content", hasSize(1)))
				.andExpect(jsonPath("$.content[0].productId", Matchers.is(35455)))
				.andExpect(jsonPath("$.content[0].brandId", Matchers.is(1)))
				.andExpect(jsonPath("$.content[0].startDate", Matchers.is("2020-06-15T16:00:00")))
				.andExpect(jsonPath("$.content[0].endDate", Matchers.is("2020-12-31T23:59:59")))
				.andExpect(jsonPath("$.content[0].price", Matchers.is(38.95)))
				.andExpect(jsonPath("$.content[0].currency", Matchers.is("EUR")))
				.andExpect(jsonPath("$.content[0].priceList", Matchers.is(4)));

	}

}
