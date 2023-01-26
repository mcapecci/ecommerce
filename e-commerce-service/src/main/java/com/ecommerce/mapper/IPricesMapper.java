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
	 * @param <T>
	 * 
	 * @param entity - {@link PricesEntity}
	 * @return dto - {@link PricesResponseDto}
	 */
	PricesResponseDto toProductResponseDto(PricesEntity entity);

}
