package com.ecommerce.service;

import java.time.LocalDateTime;

import com.ecommerce.dto.response.PricesResponseDto;

/**
 * IPricesService
 * 
 * @author Eva Magalí Capecci
 */
public interface IPricesService {

	/**
	 * Method to find price by productId, brandId and date
	 * 
	 * @param productId
	 * @param brandId
	 * @param date
	 * @return
	 */
	PricesResponseDto findByProductBrandAndDate(Long productId, Long brandId, LocalDateTime date);

}
