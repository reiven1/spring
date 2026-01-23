package com.bs.spring.controller.national;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.WebApplicationContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class NationalController {

	@Autowired
	private WebApplicationContext context;

	@RequestMapping("/national")
	public String test(Model model, String locale, Locale locale2) {
		log.debug("{}", locale2);
		String info = "";
		switch (locale) {
		case "ko_KR":
			info = context.getMessage("info", null, new Locale("ko", "KR"));
			break;
		case "en_US":
			info = context.getMessage("info", null, new Locale("en", "US"));
			break;
		case "ja_JP":
			info = context.getMessage("info", null, new Locale("ja", "JP"));
			break;
		case "zh_CN":
			info = context.getMessage("info", null, new Locale("zh", "CN"));
			break;
		}

		model.addAttribute("info", info);

		return "national/test";
	}
}
