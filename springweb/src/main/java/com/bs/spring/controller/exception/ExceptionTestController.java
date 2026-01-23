package com.bs.spring.controller.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ExceptionTestController {

	@ExceptionHandler(value = IllegalArgumentException.class)
	public String exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
		log.error("에러발생 처리하기");
		log.error(e.getMessage());
		log.error("{}", request);
		log.error("{}", response);
		return "common/error/error1";
	}

	@RequestMapping("/error/test1.do")
	public String exceptionTest() {
		String name = null;
//		name.length();
		if (name == null) {
			throw new IllegalArgumentException("매개변수 잘못 !");
		}
		return "redirect:/";
	}

	@RequestMapping("/error/test2.do")
	public String exceptionTest2() {
		String name = null;
		name.length();
		return "redirect:/";
	}
}
