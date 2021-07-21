package com.hh18.tenorbackendservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
public class WebMvcConfigurer implements org.springframework.web.servlet.config.annotation.WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:/home/tbreadgcp/image/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOrigins("http://tenor-test1.s3-website.ap-northeast-2.amazonaws.com")
                .allowedMethods("GET","POST")
                .allowCredentials(true)
                .allowedHeaders("*");
    }
}
