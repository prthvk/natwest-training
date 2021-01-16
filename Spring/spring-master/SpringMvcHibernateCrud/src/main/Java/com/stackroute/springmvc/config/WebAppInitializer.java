package com.stackroute.springmvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
//This class replaces web.xml file
//This class basically registers DispatcherServlet and uses java-based Spring Configuration
//This is abstract class
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //for "root" application context configuration.
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { AppConfig.class };
    }

    //for DispatcherServlet application context (Spring MVC infrastructure) configuration.
     @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { };
    }


    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
