package com.yao.test.configuration;

import com.yao.test.interceptor.MyInterceptor;
import com.yao.test.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final JwtProperties jwtProperties;

    // 注册拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("自定义拦截器开始生效...");
        registry.addInterceptor(new MyInterceptor(jwtProperties))
                .addPathPatterns("/**")// 拦截所有请求
                .excludePathPatterns("/user/login");// 排除登录页面
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
