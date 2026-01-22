package com.bs.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bs.spring.model.dto.Animal;
import com.bs.spring.model.dto.Person;
import com.bs.spring.model.dto.Snack;

@Configuration
public class BeanTestConfig {
	@Bean
	public Snack snack() {
		return new Snack();
	}
	@Bean
	public Snack snack2(Person jm, @Qualifier("animal3") Animal ani) {
		return Snack.builder().name("재민스넥").p(jm).build();
	}
}
