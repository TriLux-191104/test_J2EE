package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Bất kỳ request nào bắt đầu bằng /uploads/ sẽ được lấy file từ thư mục uploads/ trong project
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
    }
}