package com.ecommerce.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.criteria.PricesCriteria;
import com.ecommerce.dto.response.PageResponseDto;
import com.ecommerce.dto.response.PricesResponseDto;
import com.ecommerce.dto.response.example.ExamplePagePricesResponseDto;
import com.ecommerce.service.IPricesService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * PricesController
 *
 * @author Eva Magalí Capecci
 */
@Slf4j
@RestController
@Api(value = "PricesController", tags = "Prices")
@RequestMapping("${ecommerce.base-url}")
public class PricesController {

	/** IPricesService */
	@Autowired
	private IPricesService service;

	/**
	 * Get all prices. It is possible to filter the search by several fields
	 * 
	 * @param productId
	 * @param brandId
	 * @param date
	 * @param sortBy
	 * @param sortDirection
	 * @param page
	 * @param size
	 * @return
	 */
	@GetMapping(value = "/prices")
	@ApiOperation(value = "Get all prices.", nickname = "getAllPrices", httpMethod = "GET", notes = "Given all Prices", response = ExamplePagePricesResponseDto.class)
	public ResponseEntity<PageResponseDto<PricesResponseDto>> getAll(
			@ApiParam(name = "productId", value = "The Product id") @RequestParam(name = "productId", required = false) Optional<Long> productId,
			@ApiParam(name = "brandId", value = "The Brand id") @RequestParam(name = "brandId", required = false) Optional<Long> brandId,
			@ApiParam(name = "date", value = "Date of application") @RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> date,
			@ApiParam(name = "sortBy", value = "Sort items") @RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
			@ApiParam(name = "sortDirection", value = "Enumeration for sort order:\r\n"
					+ "             * `ASC` - Ascending, from A to Z\r\n"
					+ "             * `DESC` - Descending, from Z to A") @RequestParam(name = "sortDirection", required = false, defaultValue = "ASC") Direction sortDirection,
			@ApiParam(name = "page", value = "The page to be returned") @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@ApiParam(name = "size", value = "The number of items of that page") @RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

		log.debug("Entering PricesController getAll [sortBy]: {} [sortDirection]: {} [page]: {} [size]: {}", sortBy,
				sortDirection, page, size);

		PricesCriteria criteria = PricesCriteria.builder().brandId(brandId).productId(productId).date(date).build();

		PageResponseDto<PricesResponseDto> response = service.findAll(criteria, sortBy, sortDirection, page, size);

		log.debug("Leaving PricesController getAll");

		return ResponseEntity.ok(response);
	}

}
