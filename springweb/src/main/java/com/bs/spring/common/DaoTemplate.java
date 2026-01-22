package com.bs.spring.common;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

public interface DaoTemplate<T> {
	List<T> selectAll(SqlSession session, Map<String,Object> param);
	T selectByNo(SqlSession session,int no);
	int insert(SqlSession session, T data);
}
