package com.ecommerce.specification;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.ecommerce.criteria.PricesCriteria;
import com.ecommerce.entity.PricesEntity;

/**
 * PricesSpecification
 * 
 * @author Eva Magalí Capecci
 *
 */
public class PricesSpecification {

	private static final String BRANDID = "brand";
	private static final String PRODUCTID = "product";
	private static final String STARTDATE = "startDate";
	private static final String ENDDATE = "endDate";

	private PricesSpecification() {
	}

	public static Specification<PricesEntity> createSpeficication(PricesCriteria criteria) {
		return productIdSearch(criteria.getProductId()).and(brandIdSearch(criteria.getBrandId()))
				.and(searchLocalDateTime(criteria.getDate()));
	}

	public static Specification<PricesEntity> productIdSearch(Optional<Long> productId) {
		return (Root<PricesEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> productId
				.map(id -> criteriaBuilder.equal(root.get(PRODUCTID).get("id"), id)).orElse(null);
	}

	public static Specification<PricesEntity> brandIdSearch(Optional<Long> brandId) {
		return (Root<PricesEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> brandId
				.map(id -> criteriaBuilder.equal(root.get(BRANDID).get("id"), id)).orElse(null);
	}

	private static Specification<PricesEntity> searchLocalDateTime(Optional<LocalDateTime> searchLocalDateTime) {
		return (root, query,
				builder) -> searchLocalDateTime.map(date -> builder.between(builder.literal(date),
						root.get(STARTDATE).as(LocalDateTime.class), root.get(ENDDATE).as(LocalDateTime.class)))
						.orElse(null);
	}

}
