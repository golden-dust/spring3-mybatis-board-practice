package com.goldendust.mybatis.dao;

import java.util.ArrayList;

import com.goldendust.mybatis.dto.MemberDto;

public interface MemberDao {
	
	public void joinMember(String mid, String mpw, String mname, String memail);//회원 가입 메소드
	public int checkIdAndPw(String mid, String mpw);//로그인 체크 메소드
	public MemberDto search(String mid);//회원 아이디로 정보 조회 메소드
	public ArrayList<MemberDto> getMemberList();//회원 리스트 조회 메소드
	public int countMembers();//총 회원수 조회 메소드
	public int deleteMember(String mid); // 회원 탈퇴(삭제) 메소드
	public int isIdUsed(String mid); // 아이디 존재 여부 
}