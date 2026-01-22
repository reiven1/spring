package com.bs.spring.model.dao.demo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.model.dto.Demo;

@Repository
public class DemoDaoImpl implements DemoDao {

	@Override
	public List<Demo> selectDemoAll(SqlSession session, Map<String,Integer> param) {
		// TODO Auto-generated method stub
		return session.selectList("demo.selectAll",param);
	}

	@Override
	public int insertDemo(SqlSession session, Demo d) {
		// TODO Auto-generated method stub
		return session.insert("demo.insertDemo",d);
	}
	
	@Override
	public int countDemo(SqlSession session) {
		// TODO Auto-generated method stub
		return session.selectOne("demo.countDemo");
	}
}
