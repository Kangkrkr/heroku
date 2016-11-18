//package com.teamk.heroku.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//        .authorizeRequests()
//            .antMatchers("/login", "/join", "/rest/**").permitAll()
//            .antMatchers("/", "/home").hasAuthority("USER")
//            .anyRequest().authenticated()
//        .and()
//        	.formLogin()
//        	.loginPage("/login")
//        	.defaultSuccessUrl("/")
//        	.failureUrl("/login?error=falied")
//        .and()
//        	.logout()
//        	.logoutSuccessUrl("/login")
//        .and()
//        	.csrf().disable();
//	}
//	
//	@Override
//	protected UserDetailsService userDetailsService() {
//		return userDetailsService;
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder(){
//		return new BCryptPasswordEncoder();
//	}
//	
//}
