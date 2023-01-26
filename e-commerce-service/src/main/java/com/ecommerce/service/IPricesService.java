package com.ecommerce.service;

import org.springframework.data.domain.Sort.Direction;

import com.ecommerce.dto.response.PageResponseDto;
import com.ecommerce.dto.response.PricesResponseDto;

/**
 * IPricesService
 * 
 * @author Eva Magalí Capecci
 */
public interface IPricesService {

	/**
	 * Method to find all prices
	 * 
	 * @param sortBy
	 * @param sortDirection
	 * @param page
	 * @param size
	 * @return
	 */
	PageResponseDto<PricesResponseDto> findAll(String sortBy, Direction sortDirection, Integer page, Integer size);

}
