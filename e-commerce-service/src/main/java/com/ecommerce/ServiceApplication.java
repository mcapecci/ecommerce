package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.core.env.Environment;

import com.ecommerce.application.ApplicationUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * ServiceApplication
 * 
 * @author Eva Magalí Capecci
 */
@SpringBootApplication
@EntityScan(basePackages = { "com.ecommerce.entity" })
@Slf4j
public class ServiceApplication {
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(ServiceApplication.class);
		Environment env = application.run(args).getEnvironment();
		log.info(ApplicationUtils.logApplicationStartup(env));
	}
}