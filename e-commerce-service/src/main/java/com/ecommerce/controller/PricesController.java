package com.ecommerce.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.response.PricesResponseDto;
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
	@GetMapping(value = "/price")
	@ApiOperation(value = "Get price.", nickname = "getPrice", httpMethod = "GET", notes = "Given Price by product, brand and date", response = PricesResponseDto.class)
	public ResponseEntity<PricesResponseDto> getPriceByProductBrandAndDate(
			@ApiParam(name = "productId", value = "The Product id") @RequestParam(name = "productId", required = true) Long productId,
			@ApiParam(name = "brandId", value = "The Brand id") @RequestParam(name = "brandId", required = true) Long brandId,
			@ApiParam(name = "date", value = "Date of application") @RequestParam(name = "date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date
			) {

		log.debug("Entering PricesController getPriceByProductBrandAndDate [productId]: {} [brandId]: {} [date]{}", productId,
				brandId, date);

		PricesResponseDto response = service.findByProductBrandAndDate(productId, brandId, date);

		log.debug("Leaving PricesController getPriceByProductBrandAndDate");

		return ResponseEntity.ok(response);
	}

}
