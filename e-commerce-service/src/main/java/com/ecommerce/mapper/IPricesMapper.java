package com.ecommerce.mapper;

import com.ecommerce.dto.response.PricesResponseDto;
import com.ecommerce.entity.PricesEntity;

/**
 * IPricesMapper
 * 
 * @author Eva Magalí Capecci
 */
public interface IPricesMapper {

	/**
	 * Method to map PricesEntity to PricesResponseDto
	 * 
	 * @param entity - {@link PricesEntity}
	 * @return dto - {@link PricesResponseDto}
	 */
	PricesResponseDto toPricesResponseDto(PricesEntity entity);

}
