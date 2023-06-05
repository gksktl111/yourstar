package com.example.yourstar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    // 접속 주소 : http://localhost:8080/swagger-ui.html
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .consumes(getAllConsumeContentTypes())
                .produces(getAllProduceContentTypes())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Arrays.asList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()));
    }

    private Set<String> getAllConsumeContentTypes() {
        Set<String> consumes = new HashSet<>();
        consumes.add("application/json");
        consumes.add("multipart/form-data");
        consumes.add("application/octet-stream");
        return consumes;
    }

    private Set<String> getAllProduceContentTypes() {
        Set<String> produces = new HashSet<>();
        produces.add("application/json");
        return produces;
    }

    private ApiKey apiKey() {
        return new ApiKey("jwtToken", "Authorization", "header");
    }

    private springfox.documentation.spi.service.contexts.SecurityContext securityContext() {
        return springfox.documentation.spi.service.contexts.SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Arrays.asList(new SecurityReference("jwtToken", authorizationScopes));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Your API Title")
                .description("Your API Description")
                .version("1.0")
                .build();
    }
}
