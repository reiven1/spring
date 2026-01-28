package com.bs.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring.common.PropertyData;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor

@Controller
@Slf4j
@RequestMapping("/property")
public class PropertyController {

	private final PropertyData propData;

	@GetMapping
	public String propertyCheck() {
		log.debug(propData.getAppName());
		log.debug(propData.getDbUrl());
		log.debug(propData.getDbUserName());
		log.debug(propData.getDbPassword());
		return "redirect:/";
	}
}
