package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.BoardDto;

@Mapper
public interface BoardMapper {
	
	public List<BoardDto> selectFreeboard();
	public List<BoardDto> selectLocalboard(String dongCode);
	public BoardDto selectFreeboardByNo(int boardno);
	public int insertFreeboard(BoardDto board);
	public int updateFreeboard(BoardDto board);
	public int deleteFreeboard(int boardno);
	
}