package com.bs.spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring.model.dto.Board;
import com.bs.spring.model.dto.Member;
import com.bs.spring.model.service.board.BoardService;
import com.bs.spring.model.service.member.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/ajax")
public class AjaxController {

	private final BoardService service;
	private final MemberService memberService;

	@RequestMapping("/board")
	@ResponseBody
	public List<Board> boards(@RequestParam(defaultValue = "1") int cpage,
			@RequestParam(defaultValue = "5") int numPerPage) {
		return service.searchBoardAll(Map.of("cPage", cpage, "numPerpage", numPerPage));
	}
	
	@RequestMapping("/member")
	@ResponseBody
	public Member memberById(String userId) {
		return memberService.selectMemberById(userId);
	}
}
