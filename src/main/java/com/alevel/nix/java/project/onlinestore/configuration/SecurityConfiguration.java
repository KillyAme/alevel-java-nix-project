package com.alevel.nix.java.project.onlinestore.configuration;

import com.alevel.nix.java.project.onlinestore.service.JPAUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.actuate.health.HealthEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.SecureRandom;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JPAUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfiguration(JPAUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET,"/categories/**").permitAll()
                .antMatchers(HttpMethod.GET,"/products/**").permitAll()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers(HttpMethod.POST,"/products").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/products/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/categories").hasRole("ADMIN")
                .requestMatchers(EndpointRequest.to(HealthEndpoint.class)).hasAnyRole("USER","ADMIN")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .userDetailsService(userDetailsService)
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}

