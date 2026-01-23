package com.bs.spring.model.dao.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bs.spring.common.aop.MyAnnotation;
import com.bs.spring.model.dto.Member;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@MyAnnotation
	@Override
	public Member selectMemberById(SqlSession session, String id) {
		// TODO Auto-generated method stub
		return session.selectOne("member.selectMemberById", id);
	}

	@Override
	public int insertMember(SqlSession session, Member m) {
		// TODO Auto-generated method stub
		return session.insert("member.insertMember", m);
	}

	@Override
	public int updateMember(SqlSession session, Member m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(SqlSession session, String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
