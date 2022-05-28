package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {
	
    @Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardDto> retrieveFreeboard() {
		return boardMapper.selectFreeboard();
	}

	@Override
	public List<BoardDto> retrieveLocalboard(String dongCode) {
		return boardMapper.selectLocalboard(dongCode);
	}

	@Override
	public BoardDto detailFreeboard(int boardno) {
		return boardMapper.selectFreeboardByNo(boardno);
	}

	@Override
	public boolean writeFreeboard(BoardDto board) {
		return boardMapper.insertFreeboard(board) == 1;
	}

	@Override
	public boolean updateFreeboard(BoardDto board) {
		return boardMapper.updateFreeboard(board) == 1;
	}

	@Override
	public boolean deleteFreeboard(int boardno) {
		return boardMapper.deleteFreeboard(boardno) == 1;
	}


}