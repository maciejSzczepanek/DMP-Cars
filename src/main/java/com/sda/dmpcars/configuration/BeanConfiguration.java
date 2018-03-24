package com.sda.dmpcars.configuration;

import com.sda.dmpcars.dao.AccountDao;
import com.sda.dmpcars.validator.AccountDtoValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
public class BeanConfiguration {

    @Bean
    public AccountDtoValidator accountDtoValidator(){
        return new AccountDtoValidator();
    }
}
