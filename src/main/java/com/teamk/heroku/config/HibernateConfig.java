package com.teamk.heroku.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	@Autowired
	private Environment env;
	
	@Bean
	@Autowired
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource){
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setHibernateProperties(hibernateProperties());
		bean.setPackagesToScan("com.teamk.hibernate.domain");
		
		return bean;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager bean = new HibernateTransactionManager(sessionFactory);
		
		return bean;
	}
	
	@Bean
	public Properties hibernateProperties(){
		Properties properties = new Properties();
		String[] propKeys = {"hibernate.dialect", "hibernate.hbm2ddl.auto", 
							 "hibernate.show_sql", "hibernate.enable_lazy_load_no_trans"};

		for(String key : propKeys) {
			properties.put(key, env.getProperty(key));
		}
		
		return properties;
	}
}