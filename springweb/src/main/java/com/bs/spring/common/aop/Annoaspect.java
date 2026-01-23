package com.bs.spring.common.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class Annoaspect {

	@Pointcut("execution(* com.bs.spring.controller..*(..))")
	public void allMethod() {
	}

	// @Before("execution(* com.bs.spring.model.dao..select*(..))")
	public void before(JoinPoint joinPoint) {
		log.debug("===== BEFORE ADVICE =====");
		Signature sig = joinPoint.getSignature();
		log.debug("{}", sig.getDeclaringTypeName() + sig.getName());
		Object[] args = joinPoint.getArgs();
		if (args.length > 0) {
			log.debug("====== 매개변수 확인 ======");
			Arrays.stream(args).forEach(a -> log.debug("{}", a));
		}
		log.debug("=========================");
	}

	// @After("allMethod()")
	public void after(JoinPoint joinPoint) {
		log.debug("===== AFTER ADVICE =====");
	}

	// 특정 클래스의 전체 메소드를 지정하는 방법
	// within(클래스 패턴)
	@Before("within(com.bs.spring.model.service.board.*)")
	public void withinTest(JoinPoint joinpoint) {
		log.debug("===== within 으로 적용 =====");
		Signature sig = joinpoint.getSignature();
		log.debug(sig.getDeclaringTypeName() + "." + sig.getName());
		log.debug("==========================");
	}

	// 특정 인터페이스를 구현한 클래스를 대상으로 지정
	@After("within(com.bs.spring.model.dao.demo.DemoDao+)")
	public void withinTest2(JoinPoint joinPoint) {
		log.debug("===== within 으로 적용 -> 구현 인터페이스 =====");
		Signature sig = joinPoint.getSignature();
		log.debug(sig.getDeclaringTypeName() + "." + sig.getName());
		log.debug("=========================");
	}

	// 특정 어노테이션을 설정한 메소드
	@Around("@annotation(com.bs.spring.common.aop.MyAnnotation)")
	public Object aroundAnnotation(ProceedingJoinPoint pj) throws Throwable {
		log.debug("==== 어노테이션으로 적용한 aop ====");
		// 전
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		Object o = pj.proceed();
		// 후
		log.debug("==== 메소드 실행 후 ====");
		stopwatch.stop();
		log.info("실행 시간 : " + stopwatch.getTotalTimeMillis() + "ms");

		return o;
	}

	@AfterThrowing(value = "@annotation(com.bs.spring.common.aop.MyAnnotation)", throwing = "e")
	public void afterThrowingTest(JoinPoint joinPoint, Throwable e) {
		log.error("==== 에러 발생 ====");
		log.error("에러 내용 : {}", e.getMessage());
	}

}
