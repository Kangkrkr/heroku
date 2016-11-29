package com.teamk.heroku.config;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class ServletConfig {
	@Bean
	public EmbeddedServletContainerCustomizer customizer() {
		return container -> {
			container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/forbidden"));
		};
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver bean = new CommonsMultipartResolver();
		bean.setMaxUploadSize(1024 * 1024);
		return bean;
	}

}