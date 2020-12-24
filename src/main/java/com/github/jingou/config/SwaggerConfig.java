package com.github.jingou.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caedmon
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("基于 Swagger 构建的 Rest API 文档")
                .description("更多请咨询服务开发者")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket docket() {
        List<RequestParameter> list = new ArrayList<>();
        list.add(new RequestParameterBuilder()
                .in(ParameterType.HEADER)
                .name("accessToken")
                .description("token统一认证参数")
                .required(false)
                .build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.github.jingou.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(list)
                ;
    }

}
