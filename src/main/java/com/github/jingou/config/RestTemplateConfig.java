package com.github.jingou.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * RestTemplate配置类
 */
@Configuration
public class RestTemplateConfig{
    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
//        SSL factory = new SSL();    //跳过SSL认证
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(60*1000);//单位为ms
        factory.setConnectTimeout(60*1000);//单位为ms
        return factory;
    }

//    @Bean
//    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
//
//        factory.setReadTimeout(5000);
//        factory.setConnectTimeout(15000);
//        return factory;
//    }

}
