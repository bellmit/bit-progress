package com.wpx.config;

import com.fasterxml.classmate.TypeResolver;
import com.wpx.model.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

/**
 * <p>
 *
 * @author luwei
 **/
@Configuration
public class CmsBaseSwagger {

    @Value("${swagger.show}")
    private Boolean enable;

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        Parameter parameter = new ParameterBuilder()
                .name("Authorization")
                .description("token")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(false)
                .defaultValue("token ")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(enable)
                .groupName("spring-cloud")
                .apiInfo(apiInfo())
                .globalOperationParameters(Collections.singletonList(parameter))
                .select()
                .apis(withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .additionalModels(typeResolver.resolve(ResultVO.class));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring-cloud")
                .description("接口")
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes() {
        List<VendorExtension> list = new ArrayList<>();
        StringVendorExtension extension = new StringVendorExtension("token", "token ");
        list.add(extension);
        ApiKey authorization = new ApiKey("Authorization", "Authorization", "header", list);
        ApiKey routeApiToken = new ApiKey("route_api_token", "route_api_token", "header");
        ApiKey routeRestToken = new ApiKey("route_rest_token", "route_rest_token", "header");
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(authorization);
        apiKeys.add(routeApiToken);
        apiKeys.add(routeRestToken);
        return apiKeys;
    }

}
