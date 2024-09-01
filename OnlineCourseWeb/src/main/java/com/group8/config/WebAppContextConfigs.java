/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.group8.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author thang
 */
@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.group8.controller",
    "com.group8.repository",
    "com.group8.service",
    
}
)
public class WebAppContextConfigs implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(
            ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/css/**")
//                .addResourceLocations("/WEB-INF/resources/css/");
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("/WEB-INF/uploads/");
        registry.addResourceHandler("/plugins/**")
                .addResourceLocations("/WEB-INF/resources/plugins/");
        registry.addResourceHandler("/js/**")
                .addResourceLocations("/WEB-INF/resources/js/");
        registry.addResourceHandler("/dist/**")
                .addResourceLocations("/WEB-INF/resources/dist/");
        registry.addResourceHandler("/pages/**")
                .addResourceLocations("/WEB-INF/pages/");
    }

}
