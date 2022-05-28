package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.CommentDto;

public interface CommentService {
	
	public List<CommentDto> retrieveCommentByBoardno(int boardno);
	public boolean writeComment(CommentDto comment);
	public boolean updateComment(CommentDto comment);
	public boolean deleteComment(int commentno);
	public void deleteAllCommentByBoardno(int boardno);

}
