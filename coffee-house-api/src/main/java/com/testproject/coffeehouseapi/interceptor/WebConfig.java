package com.testproject.coffeehouseapi.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final CheckAuthInterceptor checkAuthInterceptor;
    private final CheckManagerRoleInterceptor checkManagerRoleInterceptor;

    @Autowired
    public WebConfig(CheckAuthInterceptor checkAuthInterceptor, CheckManagerRoleInterceptor checkManagerRoleInterceptor) {
        this.checkAuthInterceptor = checkAuthInterceptor;
        this.checkManagerRoleInterceptor = checkManagerRoleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkAuthInterceptor).addPathPatterns("/api/**/users/**");
        registry.addInterceptor(checkManagerRoleInterceptor).addPathPatterns("/api/**/manager/**");
    }
}
