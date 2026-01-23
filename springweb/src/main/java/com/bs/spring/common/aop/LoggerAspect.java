package com.bs.spring.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

// aspect class
// 공용으로 사용할 기능을 정의한 클래스
@Slf4j
public class LoggerAspect {

	// 공용 메소드가 실행할 지점
	// joinpoint -> pointcut 을 이용해서 설정 -> 메소드명, 클래스, 어노테이션
	// advice : 실행시점(전, 후, 전후, 리턴 전, 리턴 후, Exception 발생 시(트랜젝션 처리))
	public void before(JoinPoint joinPoint) {
		log.debug("==== AOP BEFORE ADVICE ====");
		Signature sig = joinPoint.getSignature();
		log.debug(sig.getName()); // 실행할 메소드
		log.debug(sig.getDeclaringTypeName()); // 클래스 명
		log.debug("===========================");
	}

	public void after(JoinPoint joinPoint) {
		log.debug("==== AOP AFTER ADVICE ====");
//		Signature sig = joinPoint.getSignature();
//		log.debug(sig.getName()); // 실행할 메소드
//		log.debug(sig.getDeclaringTypeName()); // 클래스 명
		log.debug("==========================");
	}
}
