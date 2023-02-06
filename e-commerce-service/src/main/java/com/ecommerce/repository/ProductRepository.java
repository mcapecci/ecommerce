package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.ProductEntity;

/**
 * ProductRepository
 *
 * @author Eva Magalí Capecci
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
