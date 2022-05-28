package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	
    @Autowired
	private CommentMapper commentMapper;

	@Override
	public List<CommentDto> retrieveCommentByBoardno(int boardno) {
		return commentMapper.selectCommentByBoardno(boardno);
	}

	@Override
	public boolean writeComment(CommentDto comment) {
		return commentMapper.insertComment(comment) == 1;
	}

	@Override
	public boolean updateComment(CommentDto comment) {
		return commentMapper.updateComment(comment) == 1;
	}

	@Override
	public boolean deleteComment(int commentno) {
		return commentMapper.deleteComment(commentno) == 1;
	}

	@Override
	public void deleteAllCommentByBoardno(int boardno) {
		commentMapper.deleteAllCommentByBoardno(boardno);
	}


}