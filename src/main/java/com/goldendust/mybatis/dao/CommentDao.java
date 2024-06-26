package com.goldendust.mybatis.dao;

import java.util.ArrayList;

import com.goldendust.mybatis.dto.CommentDto;

public interface CommentDao {
	
	public void insertComment(String bnum, String mid, String ctext);
	public ArrayList<CommentDto> getComments(String bnum);

}