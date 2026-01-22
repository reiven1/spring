package com.bs.spring.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
	private String name;
	private int age;
	private double height;
	private Animal animal;
	
	public void initTest() {
		System.out.println("생성완료");
		System.out.println(this);
	}
	
	public void destroyTest() {
		System.out.println("객체소멸");
		System.out.println(this);
	}
}
