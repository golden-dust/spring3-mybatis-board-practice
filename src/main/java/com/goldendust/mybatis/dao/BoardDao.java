package com.goldendust.mybatis.dao;

import java.util.ArrayList;

import com.goldendust.mybatis.dto.BoardDto;

public interface BoardDao {
	
	public void write(String mid, String bname, String btitle, String bcontent);
	public ArrayList<BoardDto> getList();
	public BoardDto findByBnum(String bnum);
	public int isPostAvailable(String bnum);
	public void delete(String bnum);
	public void modifyPost(String bnum, String btitle, String bcontent);
	public void incrementCount(String bnum);
}