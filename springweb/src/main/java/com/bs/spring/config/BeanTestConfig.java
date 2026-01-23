package com.bs.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import com.bs.spring.model.dto.Animal;
import com.bs.spring.model.dto.Person;
import com.bs.spring.model.dto.Snack;

@Configuration
public class BeanTestConfig {

	/*
	 * @Bean public HandlerExceptionResolver exceptionResolver() { Properties prop =
	 * new Properties(); // prop.setProperty(NullPointerException.class.getName(),
	 * "common/error/error1"); //
	 * prop.setProperty(IllegalArgumentException.class.getName(),
	 * "common/error/error1"); prop.setProperty(Exception.class.getName(),
	 * "common/error/error1");
	 * 
	 * SimpleMappingExceptionResolver smr = new SimpleMappingExceptionResolver();
	 * smr.setExceptionMappings(prop);
	 * 
	 * // smr.setDefaultErrorView("common/error/error1"); return smr; }
	 */
	@Bean
	public Snack snack() {
		return new Snack();
	}

	@Bean
	public Snack snack2(Person jm, @Qualifier("animal3") Animal ani) {
		return Snack.builder().name("재민스넥").p(jm).build();
	}

	// 국제화 처리 메세지 빈 등록하기
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
}
