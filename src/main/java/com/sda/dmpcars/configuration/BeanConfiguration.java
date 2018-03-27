package com.sda.dmpcars.configuration;

import com.sda.dmpcars.security.SimplePasswordEncoder;
import com.sda.dmpcars.security.UserDetails;
import com.sda.dmpcars.validator.AccountDtoValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public AccountDtoValidator accountDtoValidator(){
        return new AccountDtoValidator();
    }
    @Primary
    @Bean(name = "dataSource")
    public DriverManagerDataSource dataSource(){
        DriverManagerDataSource managerDataSource = new DriverManagerDataSource();
        managerDataSource.setDriverClassName("org.postgresql.Driver");
        managerDataSource.setUrl(this.url);
        managerDataSource.setUsername(this.username);
        managerDataSource.setPassword(this.password);
        return managerDataSource;
    }

    @Bean
    public UserDetails userDetails(){
        return new UserDetails();
    }

    @Bean
    public SimplePasswordEncoder simplePasswordEncoder(){
        return new SimplePasswordEncoder();
    }
}
