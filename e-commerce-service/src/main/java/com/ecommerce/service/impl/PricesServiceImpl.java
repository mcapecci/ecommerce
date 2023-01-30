package com.ecommerce.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.criteria.PricesCriteria;
import com.ecommerce.dto.response.PageResponseDto;
import com.ecommerce.dto.response.PricesResponseDto;
import com.ecommerce.entity.PricesEntity;
import com.ecommerce.mapper.IPricesMapper;
import com.ecommerce.repository.PricesRepository;
import com.ecommerce.service.IPricesService;
import com.ecommerce.specification.PricesSpecification;

import lombok.extern.slf4j.Slf4j;

/**
 * PricesService interface implementation.
 * 
 * @author Eva Magalí Capecci
 */
@Slf4j
@Service
public class PricesServiceImpl implements IPricesService {

	private static final String CACHE_NAME = "prices";

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
	@Cacheable(cacheNames = CACHE_NAME, keyGenerator = "customKeyGenerator")
	public PageResponseDto<PricesResponseDto> findAll(PricesCriteria criteria, String sortBy, Direction sortDirection,
			Integer page, Integer size) {
		log.debug("Entering PricesService findAll");

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

		List<PricesEntity> entityList = repository.findAll(PricesSpecification.createSpeficication(criteria));

		Map<Pair<Long, Long>, Optional<PricesEntity>> priorityList = entityList.stream()
				.collect(Collectors.groupingBy(p -> Pair.of(p.getBrand().getId(), p.getProduct().getId()),
						Collectors.maxBy(Comparator.comparingInt(PricesEntity::getPriority))));

		List<PricesResponseDto> contentList = priorityList.values().stream()
				.map(entityOpt -> mapper.toPricesResponseDto(entityOpt.get())).filter(Objects::nonNull)
				.collect(Collectors.toList());

		Page<PricesResponseDto> pageResponse = getPage(pageable, contentList);
		log.debug("Leaving PricesService findAll");

		return new PageResponseDto<>(pageResponse.getContent(), pageResponse.getTotalElements(),
				pageResponse.getNumber(), pageable.getPageSize(), pageResponse.getTotalPages());

	}

	/**
	 * getPage
	 * 
	 * @param pageable
	 * @param contentList
	 * @return
	 */
	private Page<PricesResponseDto> getPage(Pageable pageable, List<PricesResponseDto> contentList) {
		int start = (int) pageable.getOffset();
		int end = ((start + pageable.getPageSize()) > contentList.size() ? contentList.size()
				: (start + pageable.getPageSize()));

		return new PageImpl<>(contentList.subList(start, end), pageable, contentList.size());
	}

}
