package com.sda.dmpcars.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@EnableWebMvc
@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware{
    private ApplicationContext applicationContext;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/about").setViewName("about");
        registry.addViewController("/cars").setViewName("cars");
        registry.addViewController("/username").setViewName("login");
        registry.addViewController("/register").setViewName("register");
        registry.addViewController("/userpanel").setViewName("userpanel");
        registry.addViewController("/adminpanel").setViewName("adminpanel");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


}