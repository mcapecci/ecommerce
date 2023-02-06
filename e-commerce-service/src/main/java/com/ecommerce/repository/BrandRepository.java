package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.BrandEntity;

/**
 * BrandRepository
 *
 * @author Eva Magalí Capecci
 */
@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

}
