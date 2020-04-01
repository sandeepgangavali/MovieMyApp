package com.stackroute.moviecruiserapp;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableDiscoveryClient
//@ComponentScan(basePackages = { "com.stackroute.moviecruiserapp.*" })
public class MovieCruiserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCruiserServiceApplication.class, args);
	}

	@Bean
	public Docket swaggerCongiguration() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				 .paths(PathSelectors.ant("/movie/**"))
				.apis(RequestHandlerSelectors.basePackage("com.stackroute.moviecruiserapp"))
				.build()
				.apiInfo(apiDeatils());
	}

	private ApiInfo apiDeatils() {
		return new ApiInfo("Movie_Cruiser_API", "Api is used for bookmarking the favorite movies", "1.0", "Free to use",
				new springfox.documentation.service.Contact("Sandeep Gangavali", "http://itcinfotech.com",
						"sandeep.gangavali@itcinfotech.com"),
				"Api License", "http://itcinfotech.com", Collections.emptyList());

	}

}
