package com.ecommerce.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.OperationsSorter;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 * <p>
 * The class <code>com.ecommerce.configuration.SwaggerConfiguration</code> is an
 * spring component for management swagger beans.
 *
 * @author Eva Magalí Capecci
 * @see Configuration
 * @see EnableSwagger2
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport {

	@Value("${info.app.version}")
	private String appVersion;

	@Value("${info.app.name}")
	private String appName;

	@Value("${info.app.description}")
	private String appDescription;

	@Value("${info.app.package_controller}")
	private String packageController;

	/**
	 *
	 * <p>
	 * Unique constructor without arguments.
	 */
	public SwaggerConfiguration() {
		super();
	}

	/**
	 * Method that create a the api info.
	 *
	 * @return New api info.
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title(appName).description(appDescription).version(appVersion).build();
	}

	/**
	 * Method that create a new Docket api.
	 *
	 * @return New Docket api.
	 * @see Bean
	 */
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(this.packageController)).build().apiInfo(apiInfo());
	}

	@Bean
	public UiConfiguration uiConfig() {
		return UiConfigurationBuilder.builder().operationsSorter(OperationsSorter.METHOD).build();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui.html**")
				.addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}