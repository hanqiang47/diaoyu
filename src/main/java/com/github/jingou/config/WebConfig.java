package com.github.jingou.config;

import com.github.jingou.filter.AuthFilter;
import com.github.jingou.filter.WebContextFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


    @Bean
    public WebContextFilter webContextFilter() {
        return new WebContextFilter();
    }

    @Bean
    public AuthFilter jwtAuthenticationTokenFilter() {
        return new AuthFilter();
    }
}
