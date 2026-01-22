package com.bs.spring.common.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BasicInterceptor implements HandlerInterceptor {

	// 필요한 메소드만 재정의
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		// 리턴값이 true 면 요청한 컨트롤러 메소드 실행
		// 리턴값이 false 면 요청한 컨트롤러 메소드 실행 x
		log.debug("==== prehandle 실행 ====");
		log.debug("요청주소 : " + request.getRequestURI());

		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String name = paramNames.nextElement();
			log.debug("key : " + name);
		}

		if (handler instanceof HandlerMethod) {
			HandlerMethod method = (HandlerMethod) handler;
			log.debug("실행 컨트롤러 : " + method.getBean());
			log.debug("매핑 메소드 : " + method.getMethod().getName());
		}

//		response.setContentType("text/html;charset=utf-8");
//		response.getWriter().print("<h2>넌 안돼!</h2>");

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		// 컨트롤러 메소드가 반환처리 후 실행
		log.debug("==== 메소드 실행 완료 ====");
		log.debug("응답화면 이름 : " + modelAndView.getViewName());
		log.debug("모델 저장 데이터 : " + modelAndView.getModelMap());
		
//		modelAndView.setViewName("redirect:/member/insertmember.do");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		// 응답이 완료된 후
		log.debug("==== afterCompletion 실행 ====");
		if(ex!=null)
			log.error("에러 : {}", ex.getMessage());
	}

}
