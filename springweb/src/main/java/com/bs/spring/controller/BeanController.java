package com.bs.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.model.dto.Animal;
import com.bs.spring.model.dto.Car;
import com.bs.spring.model.dto.Person;
import com.bs.spring.model.dto.Snack;

@Controller
public class BeanController {

	// springbean 등록 / 등록된 bean 이용하기
	// xml 등록, @Configuration 클래스에서 @Bean 메소드 선언, 
	// 클래스 선언부에 (@Component, @Controller, @Service, @Repository) 어노테이션 선언
	
	// 등록된 springbean 가져와 사용하기(의존성 주입 - DI)
	// @Autowired, 생성자, @Bean 메소드를 이용
	// 타입 기준 매칭, 같은 타입의 Bean 많을 땐 필드명과 Bean 의 id 값과 매칭
	@Autowired
	@Qualifier("animal3")
	private Animal animal;
	@Autowired
	private Animal animal2;
	
	@Autowired
	private Person person;
	
	@Autowired
	private Snack snack;
	@Autowired
	private Snack snack2;
	
	@Autowired
	private Car car;
	
	@Autowired
	private List<Animal> animals;
	
	@RequestMapping("/bean/beantest")
	public String beantest() {
		System.out.println(animal);
		System.out.println(animal2);
		System.out.println(person);
		System.out.println(snack);
		System.out.println(snack2);
		System.out.println(car);
		System.out.println(animals);
		return "index";
	}
}
