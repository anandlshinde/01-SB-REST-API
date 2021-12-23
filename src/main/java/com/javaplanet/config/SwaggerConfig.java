package com.javaplanet.config;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.javaplanet.controller"))
				.paths(PathSelectors.regex("/.*")).build().apiInfo(apiEndPoint());
	}

	private ApiInfo apiEndPoint() {
		return new ApiInfoBuilder().title("Spring Boot Rest API")
									.description("API")
									.contact(new Contact("Anand","","alshinde7@gmail.com"))
									.license("Apache 2.0")
									.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
									.version("1.0").build();
	}

}
