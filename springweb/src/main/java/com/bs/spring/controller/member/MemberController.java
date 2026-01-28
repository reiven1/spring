package com.bs.spring.controller.member;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring.model.dto.Member;
import com.bs.spring.model.service.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
@SessionAttributes({ "loginMember" })
@Slf4j
public class MemberController {

	private final MemberService service;
	private final BCryptPasswordEncoder pwEncoder;

	@RequestMapping("/insertmember.do")
	public String insertMember() {
		return "member/member";
	}

	@RequestMapping("/insertend.do")
	public String insertEnd(Member m, Model model) {
//		System.out.println(m.getPassword());
		log.debug("원본 비밀번호 : " + m.getPassword());
		String encodePw = pwEncoder.encode(m.getPassword());
//		System.out.println(encodePw);
		log.debug("인코딩 비밀번호 : " + encodePw);
		m.setPassword(encodePw);

		int result = service.insertMember(m);
		String msg = "회원저장 실패", loc = "/member/insertmember.do";
		if (result > 0) {
			msg = "회원저장 성공";
			loc = "/";
		}
		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);

		return "common/msg";
	}

	@RequestMapping("/login")
	public String login(String userId, String password, Model model/* , HttpSession session */) {
		log.debug(userId);
		log.debug(password);
		Member loginMember = service.loginMember(userId, password);
		log.debug("{}", loginMember);
		if (loginMember != null) {
//			session.setAttribute("loginMember", loginMember);
			model.addAttribute("loginMember", loginMember);
			return "redirect:/";
		}
		model.addAttribute("msg", "로그인 실패");
		model.addAttribute("loc", "/");
		return "common/msg";
//		String msg = "로그인 실패", loc = "/";
//		Member result = service.selectMemberById(userId);
//		if(result==null) {
//			model.addAttribute("msg",msg);
//			model.addAttribute("loc",loc);
//			return "common/msg";
//		}
//		if (result.getUserId().equals(userId)) {
//			if (result.getPassword().equals(password))
//				msg = "로그인 성공";
//			else
//				msg = "비밀번호가 틀렸습니다";
//		}
//		model.addAttribute("msg", msg);
//		model.addAttribute("loc", loc);
//		return "common/msg";
	}

	@RequestMapping("/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
	public String logout(SessionStatus sessionStatus) {
		if (!sessionStatus.isComplete()) {
			sessionStatus.setComplete();
		}
		return "redirect:/";
	}

	@RequestMapping("/memberview.do")
	public String memberView(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Member loginMember = (Member) auth.getPrincipal();
//		System.out.println(loginMember);
		
		Member m = service.selectMemberById(loginMember.getUserId());
//		System.out.println(m);
		model.addAttribute("member", m);
		return "member/memberview";
	}

	@RequestMapping("/idduplicate.do")
	@ResponseBody
	public boolean membersearch(String userId) {
		Member searchMember = service.selectMemberById(userId);
		return searchMember == null;
	}
}
