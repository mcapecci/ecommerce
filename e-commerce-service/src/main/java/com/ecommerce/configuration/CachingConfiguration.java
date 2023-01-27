package com.ecommerce.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.service.handler.ManagerCacheErrorHandler;
import com.ecommerce.util.CustomKeyGenerator;

/**
 * CachingConfiguration
 * 
 * @author Eva Magalí Capecci
 */
@Configuration
public class CachingConfiguration extends CachingConfigurerSupport {

	@Bean("customKeyGenerator")
	public KeyGenerator keyGenerator() {
		return new CustomKeyGenerator();
	}
	
	@Override
	public CacheErrorHandler errorHandler() {
		return new ManagerCacheErrorHandler();
	}

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager();
	}
}