package com.testproject.coffeehouseapi.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CheckAuthInterceptor checkAuthInterceptor;

    @Autowired
    public WebConfig(CheckAuthInterceptor checkAuthInterceptor) {
        this.checkAuthInterceptor = checkAuthInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkAuthInterceptor).addPathPatterns("/api/**/users/**");
    }
}
