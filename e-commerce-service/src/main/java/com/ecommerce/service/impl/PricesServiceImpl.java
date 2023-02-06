package com.ecommerce.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dto.response.PricesResponseDto;
import com.ecommerce.entity.BrandEntity;
import com.ecommerce.entity.PricesEntity;
import com.ecommerce.entity.ProductEntity;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.mapper.IPricesMapper;
import com.ecommerce.repository.BrandRepository;
import com.ecommerce.repository.PricesRepository;
import com.ecommerce.repository.ProductRepository;
import com.ecommerce.service.IPricesService;

import lombok.extern.slf4j.Slf4j;

/**
 * PricesService interface implementation.
 * 
 * @author Eva Magalí Capecci
 */
@Slf4j
@Service
public class PricesServiceImpl implements IPricesService {

	/** PricesRepository */
	@Autowired
	private PricesRepository repository;

	/** BrandRepository */
	@Autowired
	private BrandRepository brandRepository;

	/** ProductRepository */
	@Autowired
	private ProductRepository productRepository;

	/** IPricesMapper */
	@Autowired
	private IPricesMapper mapper;

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Transactional(readOnly = true)
	@Override
	public PricesResponseDto findByProductBrandAndDate(Long productId, Long brandId, LocalDateTime date) {
		log.debug("Entering PricesService findByProductBrandAndDate");

		BrandEntity brand = brandRepository.findById(brandId)
				.orElseThrow(() -> new ResourceNotFoundException("Brand with id:" + brandId + " not found"));
		ProductEntity product = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product with id:" + productId + " not found"));

		Optional<PricesEntity> entityOpt = repository
				.findFirstByProductIdAndBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualOrderByPriorityDesc(
						productId, brandId, date, date);
		log.debug("Leaving PricesService findByProductBrandAndDate");
		return mapper.toPricesResponseDto(entityOpt.isPresent() ? entityOpt.get() : null);
	}

}
