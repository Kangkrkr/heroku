package com.teamk.heroku.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.teamk.heroku.security.LoginSuccessHandler;
import com.teamk.heroku.security.LoginUserDetaisService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private LoginUserDetaisService loginUserDetailsService;
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
        	// 해당 url은 전체공개한다.
            .antMatchers("/login", "/join", "/rest/member/join", "/resources/**").anonymous()
            // 해당 url은 반드시 권한이 있어야 접근가능하도록 한다.
        	.antMatchers("/home", "/rest/**").authenticated()
        .and()
        	.formLogin()
        	.loginPage("/login")
        	// 프론트단에서 submit시 아래 url로 action을 명시하면 자동으로 아래의 파라미터를 받아와 처리한다.
        	// 아래 url이 호출될때 등록한 userDetailsService 빈의 loadUserByUsername() 메소드가 호출됨.
        	.loginProcessingUrl("/login-process")
        		.usernameParameter("email")
        		.passwordParameter("password")
        	// 성공시 아래 url로 이동.
        	.defaultSuccessUrl("/home", true)
        	// 로그인 성공시의 핸들러 등록. 추가 인증작업이 필요하다.
        	.successHandler(loginSuccessHandler)
        	.failureUrl("/login?error=falied")
        .and()
        	.logout()
        		// 해당 클라이언트의 세션을 무효화하고 SecutiryContext의 인증 내역을 clear 한다.
        		// SecurityContextHolder.clearContext() 과 동일하다.
	        	.invalidateHttpSession(true)
	        	.clearAuthentication(true)
	        	.logoutSuccessUrl("/login")
        .and()
        	// csrf 방지.
        	.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// DB의 사용자정보와 로그인시의 입력정보를 비교하므로 로그인시 입력한 비밀번호는 암호화되어 비교되어져야 한다.
		// userDetailsService 메소드에 빈이 등록되면 해당 빈의 loadUserByUsername(String email) 콜백 메소드가 호출된다.
		// 아래 userDetailsService 메소드는 등록한 빈을 통해 UserDetails 구현체를 반환한다.
		auth.userDetailsService(loginUserDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
