package com.been.beenbackend;

import com.been.beenbackend.Interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class BeenBackendApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(BeenBackendApplication.class, args);
    }

    @Autowired
    private JwtInterceptor jwtInterceptor;

    /**
     * JWTInterceptor를 설치한다.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor).addPathPatterns("/**") // 기본 적용 경로
                .excludePathPatterns(Arrays.asList("/**"));// 적용 제외 경로
    }

    /**
     * Interceptor를 이용해서 처리하므로 전역의 Corss Origin 처리를 해준다.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("jwt-auth-token");
    }

}