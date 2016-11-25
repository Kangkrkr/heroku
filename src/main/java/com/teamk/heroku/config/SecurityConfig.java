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
            .antMatchers("/", "/home").hasAnyAuthority("USER")
            .antMatchers("/login", "/join", "/public").anonymous()
            .anyRequest().permitAll()
        .and()
        	.formLogin()
        	.loginPage("/login")
        	.defaultSuccessUrl("/home")
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
