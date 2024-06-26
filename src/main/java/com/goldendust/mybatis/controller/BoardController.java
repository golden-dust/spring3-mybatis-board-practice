package com.goldendust.mybatis.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.goldendust.mybatis.dao.BoardDao;
import com.goldendust.mybatis.dao.CommentDao;
import com.goldendust.mybatis.dao.MemberDao;
import com.goldendust.mybatis.dto.BoardDto;
import com.goldendust.mybatis.dto.CommentDto;
import com.goldendust.mybatis.dto.MemberDto;

@Controller
public class BoardController {
	
	@Autowired
	SqlSession sqlSession;
	
	@RequestMapping(value="/boardlist")
	public String toBoard(Model model) {
		
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		ArrayList<BoardDto> boardList = bDao.getList();
		
		model.addAttribute("boardList", boardList);
		
		return "boardlist";
	}
	
	@RequestMapping(value="/write")
	public String toWrite(HttpSession session, Model model) {
		
		// 세션에서 session id 값 가져오기 (세션 매개변수 선언 필수)
		String sid = (String) session.getAttribute("sid");
		
		if (sid == null) {  // 로그인 안 된 상태
			model.addAttribute("loginFail", "글 작성은 로그인 후 가능합니다");
			return "login";
		} else {  // 로그인 된 상태
			
			MemberDao mDao = sqlSession.getMapper(MemberDao.class);
			MemberDto user = mDao.search(sid);

			model.addAttribute("mid", sid);
			model.addAttribute("mname", user.getMname());
			
			return "write_form";
		}
	}

	@RequestMapping(value="/writeOk")
	public String writeOk(HttpServletRequest request) {
		
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		bDao.write(request.getParameter("mid"), 
				request.getParameter("bname"), 
				request.getParameter("btitle"), 
				request.getParameter("bcontent"));
		
		return "redirect:boardlist";
	}
	
	@RequestMapping(value="/post")
	public String toPost(HttpServletRequest request, Model model) {
		
		// 세션 아이디 가져오기
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("sid");
		
		// 세션 아이디 확인하기 (로그인 여부 확인)
		if (sid != null) {  // 로그인 O
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			String bnum = request.getParameter("bnum");
			
			int idMatch = 0; // 아이디 일치여부 플래그
			
			// 게시물 번호에 해당하는 게시글 있는지 확인
			if (bDao.isPostAvailable(bnum) == 1) {  // 일치 게시글 있으면
				// 조회수 증가
				bDao.incrementCount(bnum);
				
				// 게시글 불러와 모델에 저장
				BoardDto post = bDao.findByBnum(bnum);
				model.addAttribute("post", post);
				
				CommentDao cDao = sqlSession.getMapper(CommentDao.class);
				model.addAttribute("comments", cDao.getComments(bnum));

				// 로그인 아이디와 게시글 아이디 일치여부 확인 -> 프런트에서 버튼 출력 조정
				if (post.getMid().equals(sid)) {  // 일치
					idMatch = 1;
				} 
				model.addAttribute("idMatch", idMatch);
			} else {  // 일치 게시글 없음
				model.addAttribute("viewFail", "조회하신 게시글이 존재하지 않습니다");
			}
			return "contentView";
		} else {  // 로그인 X -> 로그인 페이지로 이동
			return "redirect:login";
		}
		
		
	}
	
	@RequestMapping(value="/modify-post")
	public String toModifiyPost(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		String sid = (String) session.getAttribute("sid");
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		BoardDto post = bDao.findByBnum(request.getParameter("bnum"));
		model.addAttribute("post", post);
		if (sid != null) {
			String mid = request.getParameter("mid");
			if (sid.equals(mid)) {
				return "modify_form";
			} else {
				model.addAttribute("wrongId", "본인의 글만 수정하거나 삭제할 수 있습니다.");
				return "contentView";
			}
		} else {
			return "redirect:login";
		}
	}
	
	@RequestMapping(value="/modifyOk")
	public String modifyOk(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("sid");
		
		int idMatch = 0; // 아이디 일치여부 플래그
		
		BoardDao bDao = sqlSession.getMapper(BoardDao.class);
		
		if (request.getParameter("mid").equals(sid)) {
			idMatch = 1;
			model.addAttribute("idMatch", idMatch);
			bDao.modifyPost(request.getParameter("bnum"), 
					request.getParameter("btitle"), 
					request.getParameter("bcontent"));
		}
		
		BoardDto post = bDao.findByBnum(request.getParameter("bnum"));
		model.addAttribute("post", post);
		return "contentView";
	}
	
	@RequestMapping(value="/delete-post")
	public String deletePost(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		
		String sid = (String) session.getAttribute("sid");
		if (sid != null) {
			String mid = request.getParameter("mid");
			BoardDao bDao = sqlSession.getMapper(BoardDao.class);
			if (sid.equals(mid)) {
				bDao.delete(request.getParameter("bnum"));
				return "redirect:boardlist";
			} else { 
				BoardDto post = bDao.findByBnum(request.getParameter("bnum"));
				model.addAttribute("post", post);
				model.addAttribute("wrongId", "본인의 글만 수정하거나 삭제할 수 있습니다.");
				return "contentView"; 
			}
		} else {
			return "login";
		}
	}
	
	@RequestMapping(value="/commentOk")
	public String comment(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("sid");
		if (sid == null) {
			return "redirect:login";
		} 
		String bnum = request.getParameter("bnum");
		
		CommentDao cDao = sqlSession.getMapper(CommentDao.class);
		cDao.insertComment(bnum, sid, request.getParameter("ctext"));
		
		ArrayList<CommentDto> comments = cDao.getComments(bnum);
		
		model.addAttribute("bnum", bnum);
		model.addAttribute("comments", comments);
		
		return "redirect:post";
	}
	
}