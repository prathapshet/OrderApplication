package com.zkteco.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.zkteco.order")).paths(regex("/api.*")).build()
				.apiInfo(metaInfo());

	}

	private ApiInfo metaInfo() {

		ApiInfo apiInfo = new ApiInfo("Order Application", "Spring Boot Project", "1.0", "Terms of Service",
				new Contact("Prathap Shet", "https://www.zkteco.in", "prathapshet@zkteco.in"),
				"Apache Licence Version 2.0", "http://www.apache.org/licence.html");
		return apiInfo;
	}

}
