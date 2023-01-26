package com.ecommerce.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.dto.response.PageResponseDto;
import com.ecommerce.dto.response.PricesResponseDto;
import com.ecommerce.entity.PricesEntity;
import com.ecommerce.mapper.IPricesMapper;
import com.ecommerce.repository.PricesRepository;
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

	/** IPricesMapper */
	@Autowired
	private IPricesMapper mapper;

	/**
	 * {@inheritDoc}
	 * 
	 */
	@Transactional(readOnly = true)
	@Override
	public PageResponseDto<PricesResponseDto> findAll(String sortBy, Direction sortDirection, Integer page,
			Integer size) {
		log.debug("Entering PricesService findAll");
		
		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

		Page<PricesEntity> pageEntity = repository.findAll(pageable);

		List<PricesResponseDto> listDto = pageEntity.stream().map(entity -> mapper.toProductResponseDto(entity))
				.filter(Objects::nonNull).collect(Collectors.toList());

		log.debug("Leaving PricesService findAll");
		
		return new PageResponseDto<>(listDto, pageEntity.getTotalElements(), pageEntity.getNumber(),
				pageEntity.getSize(), pageEntity.getTotalPages());
		
		
	}

}
