package com.ecommerce.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
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
	public PageResponseDto<PricesResponseDto> findAll(PricesCriteria criteria, String sortBy, Direction sortDirection,
			Integer page, Integer size) {
		log.debug("Entering PricesService findAll");

		Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));

		Page<PricesEntity> pageEntity = repository.findAll(PricesSpecification.createSpeficication(criteria), pageable);

		Map<Pair<Long, Long>, Optional<PricesEntity>> priorityList = pageEntity.stream()
				.collect(Collectors.groupingBy(p -> Pair.of(p.getBrand().getId(), p.getProduct().getId()),
						Collectors.maxBy(Comparator.comparingInt(PricesEntity::getPriority))));

		List<PricesResponseDto> contentList = priorityList.values().stream()
				.map(entityOpt -> mapper.toProductResponseDto(entityOpt.get())).filter(Objects::nonNull)
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
