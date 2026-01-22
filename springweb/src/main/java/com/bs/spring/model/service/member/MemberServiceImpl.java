package com.bs.spring.model.service.member;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bs.spring.model.dao.member.MemberDao;
import com.bs.spring.model.dto.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

	private final MemberDao dao;
	private final SqlSession session;
	private final BCryptPasswordEncoder pwEncoder;

	@Override
	public Member selectMemberById(String id) {
		// TODO Auto-generated method stub
		return dao.selectMemberById(session, id);
	}

	@Override
	public int insertMember(Member m) {
		// TODO Auto-generated method stub
		return dao.insertMember(session, m);
	}

	@Override
	public int updateMember(Member m) {
		// TODO Auto-generated method stub
		return dao.updateMember(session, m);
	}

	@Override
	public int deleteMember(String id) {
		// TODO Auto-generated method stub
		return dao.deleteMember(session, id);
	}

	@Override
	public Member loginMember(String userId, String password) {
		// TODO Auto-generated method stub
		Member loginMember = dao.selectMemberById(session, userId);
		if (loginMember != null && pwEncoder.matches(password, loginMember.getPassword()))
			return loginMember;
		return null;
	}

}
