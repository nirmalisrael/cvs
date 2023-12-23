package com.algoriant.cvs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket getApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.algoriant.cvs"))
                .paths(PathSelectors.ant("/cvs/*"))
                .build()
                .apiInfo(new ApiInfo("CVS API", "College Voting Management System",
                        "1.0", "www.algoriant.com",
                        new Contact("Algoriant", "http://www.algoriant.com", "info@algoriant.com"),
                        "opensource", "http://www.algoriant.com",
                        Collections.emptyList()));
    }
}
