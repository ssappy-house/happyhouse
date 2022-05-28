package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.BoardDto;

public interface BoardService {
	
	public List<BoardDto> retrieveFreeboard();
	public List<BoardDto> retrieveLocalboard(String dongCode);
	public BoardDto detailFreeboard(int boardno);
	public boolean writeFreeboard(BoardDto board);
	public boolean updateFreeboard(BoardDto board);
	public boolean deleteFreeboard(int boardno);
	
}
