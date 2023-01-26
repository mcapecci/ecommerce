package com.ecommerce.criteria;

import java.time.LocalDateTime;
import java.util.Optional;

import lombok.Builder;
import lombok.Data;

/**
 * Filtering parameters for {@link PricesCriteria} search.
 * 
 * @author Eva Magalí Capecci
 * 
 */
@Data
@Builder
public class PricesCriteria {

	private Optional<Long> productId;
	private Optional<Long> brandId;
	private Optional<LocalDateTime> date;

}