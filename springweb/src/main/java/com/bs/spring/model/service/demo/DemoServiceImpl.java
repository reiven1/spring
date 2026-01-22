package com.bs.spring.model.service.demo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.bs.spring.model.dao.demo.DemoDao;
import com.bs.spring.model.dto.Demo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final 로 선언된 필드로만 생성자를 생성
@Service
public class DemoServiceImpl implements DemoService {
	private final DemoDao dao;
	private final SqlSession session;
	
	@Override
	public List<Demo> searchDemoAll(int cPage, int numPerPage) {
		// TODO Auto-generated method stub
		int start = (cPage - 1) * numPerPage + 1;
		int end = cPage * numPerPage;
		Map<String, Integer> param = Map.of("start", start, "end", end);
		return dao.selectDemoAll(session, param);
	}

	@Override
	public int insertDemo(Demo d) {
		// TODO Auto-generated method stub
		return dao.insertDemo(session, d);
	}
	
	@Override
	public int countDemo() {
		// TODO Auto-generated method stub
		return dao.countDemo(session);
	}

}
