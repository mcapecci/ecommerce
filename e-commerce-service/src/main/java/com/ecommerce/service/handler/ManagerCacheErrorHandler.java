package com.ecommerce.service.handler;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * ManagerCacheErrorHandler
 * 
 * @author Eva Magalí Capecci
 *
 */
@Slf4j
public class ManagerCacheErrorHandler implements CacheErrorHandler {
	@Override
	public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
		log.error(e.getMessage(), e);
	}

	@Override
	public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
		log.error(e.getMessage(), e);
	}

	@Override
	public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
		log.error(e.getMessage(), e);
	}

	@Override
	public void handleCacheClearError(RuntimeException e, Cache cache) {
		log.error(e.getMessage(), e);
	}
}