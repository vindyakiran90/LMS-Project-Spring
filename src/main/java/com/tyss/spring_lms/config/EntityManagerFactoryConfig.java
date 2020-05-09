package com.tyss.spring_lms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
public class EntityManagerFactoryConfig {
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManager() {
		
		LocalContainerEntityManagerFactoryBean factoryBean = 
				new LocalContainerEntityManagerFactoryBean();
		
		factoryBean.setPersistenceUnitName("TestPersistence");
		
		return factoryBean;
	}
}
