package com.bs.spring.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String index(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		session.setAttribute("saveId", "admin");
		
		Cookie c = new Cookie("mycookie", "두쫀쿠");
		c.setMaxAge(60*60*4);
		response.addCookie(c);
		
		return "index";
	}
}
