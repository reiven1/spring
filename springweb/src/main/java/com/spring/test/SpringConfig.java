package com.spring.test;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SpringConfig {
	// 메소드 선언해서 등록
	@Bean
	@Scope("prototype")
	TestClass test4() {
		return new TestClass("오수환",25);
	}
	@Bean
	LocalDate date() {
		return LocalDate.of(2002, 6, 26);
	}
}
