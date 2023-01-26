package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.PricesEntity;

/**
 * PricesRepository
 *
 */
@Repository
public interface PricesRepository extends JpaRepository<PricesEntity, Long>, JpaSpecificationExecutor<PricesEntity> {

}
