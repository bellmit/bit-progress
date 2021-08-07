package com.wpx.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.wpx.constant.StringConstants;
import com.wpx.property.SwaggerProperties;
import com.wpx.util.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spi.service.contexts.SecurityContextBuilder;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.wpx.constant.VerifyConstant.*;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

/**
 * @Author: 不会飞的小鹏
 * @Deprecated: swagger配置
 */
@EnableOpenApi
@EnableKnife4j
@Configuration
public class SwaggerConfig {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerProperties.getEnable())
                .groupName(swaggerProperties.getGroup())
                .apiInfo(apiInfo())
                .select()
                .apis(withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(swaggerProperties.getApplicationName())
                .description(swaggerProperties.getDescription())
                .version(swaggerProperties.getVersion())
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        ApiKey authorization = new ApiKey(AUTHORIZATION, AUTHORIZATION, HEADER);
        ApiKey routeApiToken = new ApiKey(ROUTE_API_TOKEN, ROUTE_API_TOKEN, HEADER);
        ApiKey routeRestToken = new ApiKey(ROUTE_REST_TOKEN, ROUTE_REST_TOKEN, HEADER);
        ApiKey userId = new ApiKey(USER_ID, USER_ID, HEADER);
        List<SecurityScheme> apiKeys = new ArrayList<>();
        apiKeys.add(authorization);
        apiKeys.add(routeApiToken);
        apiKeys.add(routeRestToken);
        apiKeys.add(userId);
        return apiKeys;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> contexts = new ArrayList<>();
        SecurityContextBuilder contextBuilder = SecurityContext.builder();
        AuthorizationScope[] scopes = new AuthorizationScope[1];
        AuthorizationScope scope = new AuthorizationScope(GLOBAL, ACCESS_EVERY_THING);
        scopes[0] = scope;
        SecurityReference authorization = new SecurityReference(AUTHORIZATION, scopes);
        SecurityReference routeApiToken = new SecurityReference(ROUTE_API_TOKEN, scopes);
        SecurityReference routeRestToken = new SecurityReference(ROUTE_REST_TOKEN, scopes);
        SecurityReference userId = new SecurityReference(USER_ID, scopes);
        List<SecurityReference> references = new ArrayList<>();
        references.add(authorization);
        references.add(routeApiToken);
        references.add(routeRestToken);
        references.add(userId);
        SecurityContext context = contextBuilder.securityReferences(references).build();
        contexts.add(context);
        return contexts;
    }

}
