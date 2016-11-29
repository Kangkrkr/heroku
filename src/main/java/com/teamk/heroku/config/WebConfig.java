package com.teamk.heroku.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("main");
		registry.addViewController("/home").setViewName("main");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/join").setViewName("join");
		registry.addViewController("/forbidden").setViewName("forbidden");
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		MappingJackson2HttpMessageConverter jackson = new MappingJackson2HttpMessageConverter();
		jackson.setDefaultCharset(Charset.forName("UTF-8"));
		
		converters.add(jackson);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/my/**").addResourceLocations("/public/");
	}

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(viewResolver());
	}

	@Bean
	public ViewResolver viewResolver() {
		return new InternalResourceViewResolver("/public/view/", ".html");
	}
	
	@Bean
	public RestTemplate restTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		
		MappingJackson2HttpMessageConverter jackson = new MappingJackson2HttpMessageConverter();
		jackson.setDefaultCharset(Charset.forName("UTF-8"));
		
		List<HttpMessageConverter<?>> converters = new ArrayList<>();
		converters.add(jackson);
		
		restTemplate.setMessageConverters(converters);
		return restTemplate;
	}
	
	
}
