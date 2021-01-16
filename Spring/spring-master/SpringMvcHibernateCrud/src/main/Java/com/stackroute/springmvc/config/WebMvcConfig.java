package com.stackroute.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


//dispatcher-servlet.xml
//@Configuration
//@ComponentScan("com.stackroute.springmvc")
//@EnableWebMvc
/*
Scan starts from base package and  registers all
 controllers, repositories, service, beans, etc
 */
//Defines callback methods to customize the Java-based
// configuration for Spring MVC enabled via @EnableWebMvc.
//@EnableWebMvc-annotated configuration classes may implement this interface to be
// called back and given a chance to customize the default configuration.
/*public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();

        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}*/
