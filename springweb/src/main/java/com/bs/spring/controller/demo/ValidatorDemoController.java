package com.bs.spring.controller.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.model.dto.Demo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ValidatorDemoController {

	@RequestMapping("/validator/page.do")
	public String validatorPage(Model m, Demo demo) {
		m.addAttribute("demo", demo);
		return "demo/validatorDemo";
	}

	@RequestMapping("/validator/demo.do")
	public String validatorTest(@Validated Demo demo, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			bindingResult.getAllErrors().forEach(System.out::println);
			return "demo/validatorDemo";
		}
		model.addAttribute("demo", demo);
		return "redirect:/";
	}
}
