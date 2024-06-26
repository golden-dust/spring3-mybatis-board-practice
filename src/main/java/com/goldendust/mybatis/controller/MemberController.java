package com.goldendust.mybatis.controller;

import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldendust.mybatis.dao.MemberDao;
import com.goldendust.mybatis.dto.MemberDto;

@Controller
public class MemberController {
	
	@Autowired	// 의존 자동 주입 (DI)
	private SqlSession sqlSession;
	
	
	@RequestMapping(value="/join")
	public String join() {
		return "join";
	}
	
	@RequestMapping(value="/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		String mname = request.getParameter("mname");
		String memail = request.getParameter("memail");
		
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		mDao.joinMember(mid, mpw, mname, memail);
		
		model.addAttribute("mid", mid);
		model.addAttribute("mname", mname);
		
		return "joinOk";
	}
	
	
	@RequestMapping(value="/login")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping(value="/loginOk")
	public String login(HttpServletRequest request, HttpSession session, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		int loginFlag = mDao.checkIdAndPw(mid, mpw);	// flag: 1 or 0
		if (loginFlag == 1) {
			session.setAttribute("sid", mid);
			model.addAttribute("loginId", mid);
			return "loginOk";
		} else {
			model.addAttribute("loginFail", "아이디와 비밀번호를 확인해주세요");
			return "login";
		}
		
	}
	
	@RequestMapping(value="/search-member")
	public String toSearchMember() {
		return "searchMember";
	}
	
	@RequestMapping(value="/search-member-result")
	public String searchMember(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		Optional<MemberDto> memberFoundOptional = Optional.ofNullable(mDao.search(mid));
		
		if (memberFoundOptional.isPresent()) {
			model.addAttribute("mDto", memberFoundOptional.get());
		} else {
			model.addAttribute("failMsg", "회원 조회 실패: 존재하지 않는 아이디입니다");
		}
		return "searchOk";
	}
	
	@RequestMapping(value="/memberlist")
	public String memberlist(Model model) {
		
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		ArrayList<MemberDto> mDtos = mDao.getMemberList();
		int memberCount = mDao.countMembers();
		
		model.addAttribute("memberList", mDtos);
		model.addAttribute("memberCount", memberCount);
		
		return "memberList";
	}
	
	@RequestMapping(value="/delete-account")
	public String toDelete() {
		return "delete";
	}
	
	@RequestMapping(value="/deleteOk")
	public String delete(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("mid");
		String mpw = request.getParameter("mpw");
		
		int success = 0;
		
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		int idPwChecked = mDao.checkIdAndPw(mid, mpw);
		if (idPwChecked == 1) {
			success = mDao.deleteMember(mid);
		} else {
			model.addAttribute("deleteFail", "아이디와 비밀번호를 확인해주세요");
			return "delete";
		}
		
		System.out.println(success);
		
		return "redirect:memberlist";
	}
	
	@RequestMapping(value="/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:login";
	}
	
	@RequestMapping(value="/checkId")
	public String checkId(HttpServletRequest request, Model model) {
		
		MemberDao mDao = sqlSession.getMapper(MemberDao.class);
		int idFlag = mDao.isIdUsed(request.getParameter("checkId"));
		model.addAttribute("idFlag", idFlag);

		return "check_id";
	}

}