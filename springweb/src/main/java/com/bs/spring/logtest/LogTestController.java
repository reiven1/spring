package com.bs.spring.logtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogTestController {
	
	// log4j 를 이용해서 로그 출력하기
//	private final Logger logger = LogManager.getLogger(LogTestController.class);
	private final Logger log = LoggerFactory.getLogger(LogTestController.class);
	
	@RequestMapping("/logtest.do")
	public String logTest() {
//		System.out.println("로그");
		log.debug("개발할 때 사용하는 로그"); // 매개변수에 문자열은 로그 xml 파일 중 패턴의 m에 해당
		log.info("프로그램 런타임 중 알려줄 사항을 남길 때");
		log.warn("프로그램 이용에 대한 경고를 출력");
		log.error("에러 결과 출력 -> 프로그램이 중단!");
		
		return "redirect:/";
	}
}
