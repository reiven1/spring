package com.bs.spring.controller.demo.view;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.View;

@Component
public class MyView implements View {
	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		for(String key : model.keySet()) {
			System.out.println(key);
		}
		
		for(Object value : model.values()) {
			System.out.println(value);
		}

		response.sendRedirect(request.getContextPath());
		
	}
}
