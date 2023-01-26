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
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/prices")
	@ApiOperation(value = "Get all prices.", nickname = "getAllPrices", httpMethod = "GET", notes = "Given all Prices", response = ExamplePagePricesResponseDto.class)
	public ResponseEntity<PageResponseDto<PricesResponseDto>> getAll(
			@RequestParam(name = "productId", required = false) Optional<Long> productId,
			@RequestParam(name = "brandId", required = false) Optional<Long> brandId,
			@RequestParam(name = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> date,
			@RequestParam(name = "sortBy", required = false, defaultValue = "id") String sortBy,
			@RequestParam(name = "sortDirection", required = false, defaultValue = "ASC") Direction sortDirection,
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "10") Integer size) {

		log.debug("Entering PricesController getAll [sortBy]: {} [sortDirection]: {} [page]: {} [size]: {}", sortBy,
				sortDirection, page, size);

		PricesCriteria criteria = PricesCriteria.builder().brandId(brandId).productId(productId).date(date).build();

		PageResponseDto<PricesResponseDto> response = service.findAll(criteria, sortBy, sortDirection, page, size);

		log.debug("Leaving PricesController getAll");

		return ResponseEntity.ok(response);
	}

}
