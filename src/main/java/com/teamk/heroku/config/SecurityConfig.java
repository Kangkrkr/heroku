package com.teamk.heroku.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
            .antMatchers("/login", "/join", "/rest/**").permitAll()
            .antMatchers("/", "/home").hasAnyAuthority("USER")
            .anyRequest().authenticated()
        .and()
        	.formLogin()
        	.loginPage("/login")
        	.defaultSuccessUrl("/")
        	.failureUrl("/login?error=falied")
        .and()
        	.logout()
        	.logoutSuccessUrl("/login")
        .and()
        	.csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	
}
