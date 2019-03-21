package com.bugrasessevmez.issuemanagement.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bugrasessevmez.issuemanagement"))
                .paths(regex("/rest.*"))
                .build()
                .apiInfo(apiInfo())
                .useDefaultResponseMessages(false)
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class);
    }

	ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Issue Management API Reference")
                .version("1.0.0")
                .build();
    }
}
