package com.ecommerce.mapper.impl;

import java.util.Objects;

import org.springframework.stereotype.Component;

import com.ecommerce.dto.response.PricesResponseDto;
import com.ecommerce.entity.PricesEntity;
import com.ecommerce.mapper.IPricesMapper;

/**
 * PricesMapper interface implementation.
 * 
 * @author Eva Magalí Capecci
 */
@Component
public class PricesMapperImpl implements IPricesMapper {

	/**
	 * {@inheritDoc}
	 *
	 */
	@Override
	public PricesResponseDto toProductResponseDto(PricesEntity entity) {
		if (Objects.isNull(entity)) {
			return null;
		}

		return PricesResponseDto.builder().brandId(entity.getBrand().getId()).productId(entity.getProduct().getId())
				.startDate(entity.getStartDate()).endDate(entity.getEndDate()).price(entity.getPrice())
				.currency(entity.getCurrency().getCurrencyCode()).priceList(entity.getPriceList()).build();

	}

}
