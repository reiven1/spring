package com.bs.spring.model.dao.demo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.model.dto.Demo;

public interface DemoDao {
	public List<Demo> selectDemoAll(SqlSession session, Map<String, Integer> param);
	public int insertDemo(SqlSession session, Demo d);
	public int countDemo(SqlSession session);
}
