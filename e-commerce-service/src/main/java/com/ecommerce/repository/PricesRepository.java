package com.ecommerce.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.PricesEntity;

/**
 * PricesRepository
 *
 */
@Repository
public interface PricesRepository extends JpaRepository<PricesEntity, Long> {

	Page<PricesEntity> findAll(Pageable pageable);

}
