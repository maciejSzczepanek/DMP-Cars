package com.sda.dmpcars.configuration;

import com.sda.dmpcars.security.SimplePasswordEncoder;
import com.sda.dmpcars.security.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;


import javax.sql.DataSource;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private UserDetails userDetails;

    @Autowired
    public SecurityConfiguration(DataSource dataSource, UserDetails userDetails) {
        this.dataSource = dataSource;
        this.userDetails = userDetails;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();//need to check how to add csrf token to json
        http.authorizeRequests()
                .antMatchers("/script/**", "/style/**", "/img/**", "/about", "/cars",
                        "/register", "/", "/index", "/cars/**")//, "/cars/car") maybe not needed when posting json to db
                .permitAll()
                .antMatchers("/userpanel")
                .hasRole("USER")
                .antMatchers("/adminpanel", "/userpanel")
                .hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/index").permitAll()
                .and()
                .logout().logoutSuccessUrl("/index").permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetails);
        authProvider.setPasswordEncoder(new SimplePasswordEncoder());
        auth.authenticationProvider(authProvider);
    }
}
