package com.bs.spring.model.service.demo;

import java.util.List;

import com.bs.spring.model.dto.Demo;

public interface DemoService {
	List<Demo> searchDemoAll(int cPage, int numPerPage);
	int insertDemo(Demo d);
	int countDemo();
}
