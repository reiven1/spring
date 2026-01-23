package com.bs.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.common.aop.MyAnnotation;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ErrorTestController {

	@RequestMapping("/error/test")
	@MyAnnotation
	public String errorTest() {
		log.debug("컨트롤러 실행");
		if (1 == 1) {
			throw new RuntimeException("런타임 에러 발생");
		}
		return "redirect:/";
	}

}
