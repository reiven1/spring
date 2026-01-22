package com.spring.test;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CoreTestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// spring bean 으로 등록하고 등록된 bean 가져와 사용하기
		// TestClass test = new TestClass(); <- 이런식으로 사용 안해도됨

		ApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
		// context 에 등록된 bean 을 가져오기
		// getBean() 메소드를 이용해서 가져올 수 있음
		Object o = context.getBean("test");
		((TestClass) o).setName("하승우");
		System.out.println(o);

		TestClass t = context.getBean("test2", TestClass.class);
		System.out.println(t);

		String[] beans = context.getBeanDefinitionNames();
		for (String bean : beans) {
			System.out.println(bean);
		}

		// 어노테이션으로 설정한 bean 가져오기
		ApplicationContext context2 = new AnnotationConfigApplicationContext("com.spring.test");
		Arrays.stream(context2.getBeanDefinitionNames()).forEach(System.out::println);

		String name = context2.getBean("test4", TestClass.class).getName();
		System.out.println(name);

		int age = context2.getBean("test4", TestClass.class).getCount();
		System.out.println(age);

		LocalDate date = context2.getBean("date", LocalDate.class);
		System.out.println(date);

		TestClass tt = context.getBean("test", TestClass.class);
		tt.setName("변경해");
		System.out.println(o);
		System.out.println(tt);
	}

}
