package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.CommentDto;

@Mapper
public interface CommentMapper {
	
	public List<CommentDto> selectCommentByBoardno(int boardno);
	public int insertComment(CommentDto comment);
	public int updateComment(CommentDto comment);
	public int deleteComment(int commentno);
	public int deleteAllCommentByBoardno(int boardno);
	
}