package com.bs.spring.config;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice // 모든 컨트롤러에 적용
public class ExceptionCommand {
	
	@ExceptionHandler(value = NullPointerException.class)
	@ResponseBody
	public Map<String, Object> nullException(Exception e, HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");
		Map<String, Object> resultData = Map.of("msg", e.getMessage(), "date", new Date());
		
		return resultData;
	}
}
