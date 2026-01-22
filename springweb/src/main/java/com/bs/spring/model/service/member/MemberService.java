package com.bs.spring.model.service.member;

import com.bs.spring.model.dto.Member;

public interface MemberService {
	
	Member selectMemberById(String id);
	int insertMember(Member m);
	int updateMember(Member m);
	int deleteMember(String id);
	Member loginMember(String userId, String password);
	
}
