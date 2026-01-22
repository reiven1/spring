package com.bs.spring.controller.demo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.bs.spring.controller.demo.view.MyView;
import com.bs.spring.model.dto.Address;
import com.bs.spring.model.dto.Demo;
import com.bs.spring.model.service.demo.DemoService;
import static com.bs.spring.common.PageBar.*;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class DemoController {

	private DemoService service;

	// 의존성 주입
//	public DemoController(DemoService service) {
//		this.service = service;
//	} => 대신 lombok @AllargsConstructor 사용

	@RequestMapping("/demo/insertdemo.do")
	public String insertDemo(Demo demo) {
		int result = service.insertDemo(demo);
		if (result > 0)
			return "redirect:/demo/listdemo.do";
		return "redirect:/demo/demo.do";
	}

	@RequestMapping("/demo/demolist.do")
	public String searchDemoAll(Model model, HttpServletRequest request) {
		int cPage = 1;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		int numPerPage = 10;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (NumberFormatException e) {
			// TODO: handle exception
		}
		List<Demo> demos = service.searchDemoAll(cPage, numPerPage);
		int total = service.countDemo();
		String uri = request.getRequestURI();
		String pagebar = makePageBar(cPage, numPerPage, total, uri);
		model.addAttribute("demos", demos);
		model.addAttribute("pagebar", pagebar);
		return "demo/demoList";
	}
	// demo 요청을 처리하는 클래스

	// 요청을 처리하는 메소드를 등록
	@RequestMapping("/demo/demo.do")
	public String demo() {
		// 페이지 전환
		return "demo/demo";
	}

	// 서블릿처럼 이용하기
	@RequestMapping("/demo/demo1.do")
	public void demo1(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws IOException, ServletException {
//		System.out.println(request);
//		System.out.println(response);

		String name = request.getParameter("devName");
		Integer age = Integer.parseInt(request.getParameter("devAge"));
		String email = request.getParameter("devEmail");
		String gender = request.getParameter("devGender");
		String[] lang = request.getParameterValues("devLang");

		Demo d = Demo.builder().devName(name).devAge(age).devEmail(email).devGender(gender).devLang(lang).build();
		request.setAttribute("demo", d);

		session.setAttribute("teacher", "bslove");

		request.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(request, response);
	}

	// 전달된 파라미터 데이터를 매개변수와 매핑해서 처리하기
	// 파라미터의 key 값과 매개변수의 변수명이 일치
	// 데이터를 저장하기 위해 Model 객체를 이용
	@RequestMapping("/demo/demo2.do")
	public String demo2(String devName, int devAge, String devEmail, String devGender, String[] devLang, Model model) {
//		System.out.println(devName + " " + devAge + " " + devEmail + " " + devGender + " " + Arrays.toString(devLang));
		Demo d = Demo.builder().devName(devName).devAge(devAge).devEmail(devEmail).devGender(devGender).devLang(devLang)
				.build();

		// model 에 데이터 저장하기
		// addAttribute()
		model.addAttribute("demo", d); // = request.setAttribute()

		return "demo/demoResult";
	}

	// 매개변수 설정하기
	// @RequestParam 을 사용하기
	@RequestMapping("/demo/demo3.do")
	public String demo3(@RequestParam(value = "devName") String name,
			@RequestParam(value = "devAge", required = true, defaultValue = "0") int age,
			@RequestParam(value = "devEmail") String email,
			@RequestParam(value = "devGender", required = false) String gender,
			@RequestParam(value = "devLang", required = false) String[] lang, Model model) {
		Demo d = Demo.builder().devName(name).devAge(age).devEmail(email).devGender(gender).devLang(lang).build();

		model.addAttribute("demo", d); // = request.setAttribute()

		return "demo/demoResult";
	}

	// command 로 처리하기
	// 전달된 파라미터를 DTO 로 받기
	// 파라미터 key 와 DTO 필드명이 동일해야함
	@RequestMapping("/demo/demo4.do")
//	public String demo4(Demo d, Address address, Model m) {
	public String demo4(@ModelAttribute("demo") Demo d, Address address) {
		d.setAddress(address);
		System.out.println(d);
//		m.addAttribute("demo", d);
		return "demo/demoResult";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(sdf, false));
	}

	// 파라미터 값을 Map 으로 저장하기
	@RequestMapping("/demo/demo5.do")
	public String demo5(@RequestParam Map param, String[] devLang) {
		param.put("devLang", devLang);
		System.out.println(param);
		return "demo/demoResult";
	}

	// 반환형
	// ModelAndView 객체 이용하기
	@RequestMapping("/demo/demo6.do")
	public ModelAndView demo6(Demo d, ModelAndView mv) {
		mv.addObject("demo", d); // = model.addAttribute()
		mv.setViewName("demo/demoResult"); // view 설정

		ModelAndView mv2 = new ModelAndView("viewname", "key", "value");

		Map<String, Object> data = mv.getModel();
		String viewName = mv.getViewName();
		System.out.println(data);
		System.out.println(viewName);
		return mv;
	}

	// 컨트롤러에서 데이터를 응답
	// 특정 페이지를 지정하지 않고 클라이언트에 필요한 데이터를 응답
	// REST-API 를 이용할 때 사용 Restful 한 방식 설계
	// @ResponseBody 어노테이션 선언
	// MessageConverter 를 이용해서 변환 후 데이터를 ResponseBody 에 저장해서 응답
	// Gson, jackson ... -> 클래스패스에 있으면 자동으로 이용

	@RequestMapping("/demo/demo7.do")
	@ResponseBody
	public Demo demo7(@RequestBody Demo demo) {
		System.out.println(demo);
		return demo;
//		return Demo.builder().devName("원준 아파").devAge(25).devEmail("won@won.com")
//				.devLang(new String[] { "java", "html/css", "javascript", "servlet" }).build();
	}

	// 커스텀 view 를 만들어서 반환
	@Autowired
	private View myview;

	@RequestMapping("/demo/demo8.do")
	public View demo8(Model m) {
		m.addAttribute("teacher", "bslove");
		m.addAttribute("names", List.of(1, 2, 3));
		return myview;
	}

	// 추가적인 정보들 가져오기
	// Session 에 저장된 값 -> @SessionAttribute("key")
	// Cookie 에 저장된 값 -> @CookieValue("key")
	// Header 에 저장된 값 -> @RequestHeader("key")
	@RequestMapping("/demo/demo9.do")
	public String demo9(@SessionAttribute("saveId") String saveId, @CookieValue("JSESSIONID") String jId,
			@RequestHeader("Accept") String accept,
			@CookieValue(value = "mycookie1", required = false, defaultValue = "붕어빵") String cookieVal) {
		System.out.println(saveId);
		System.out.println(jId);
		System.out.println(accept);
		System.out.println(cookieVal);
		return "redirect:/";
	}

}
