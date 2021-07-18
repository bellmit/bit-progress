package com.wpx.config;

import com.fasterxml.classmate.TypeResolver;
import com.wpx.common.model.ResultVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

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
                .build().additionalModels(typeResolver.resolve(ResultVO.class));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("spring-cloud")
                .description("接口")
                .version("1.0")
                .build();
    }

}