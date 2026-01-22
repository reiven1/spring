package com.bs.spring.model.dao.member;

import org.apache.ibatis.session.SqlSession;

import com.bs.spring.model.dto.Member;

public interface MemberDao {
	
	Member selectMemberById(SqlSession session, String id);
	int insertMember(SqlSession session, Member m);
	int updateMember(SqlSession session, Member m);
	int deleteMember(SqlSession session, String id);
	
}
