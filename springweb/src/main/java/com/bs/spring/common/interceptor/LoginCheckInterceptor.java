package com.bs.spring.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bs.spring.model.dto.Member;
import com.bs.spring.model.service.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@Component(value = "loginCheckInter")
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

	@Autowired
	private MemberService service;
	@Autowired
	private WebApplicationContext context;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub

		log.debug("service : {}", service);
		int count = context.getBeanDefinitionCount();
		log.debug("bean 갯수 : {}", count);
		
		HttpSession session = request.getSession();
		Member loginMember = (Member) session.getAttribute("loginMember");
		if (loginMember == null) {
			request.setAttribute("msg", "로그인 후 이용할 수 있습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/WEB-INF/views/common/msg.jsp").forward(request, response);
			log.debug("msg : {}", (String) request.getAttribute("msg"));
			return false;
		}
		return true;
	}

}
