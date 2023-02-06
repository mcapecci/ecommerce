package com.ecommerce.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.PricesEntity;

/**
 * PricesRepository
 *
 * @author Eva Magalí Capecci
 */
@Repository
public interface PricesRepository extends JpaRepository<PricesEntity, Long>, JpaSpecificationExecutor<PricesEntity> {

	Optional<PricesEntity> findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(Long productId, Long brandId,
			LocalDateTime date, LocalDateTime date2);

}
