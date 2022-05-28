package com.ssafy.happyhouse.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.dto.NoticeDto;
@Mapper
public interface NoticeMapper {
	public List<NoticeDto> selectNotice();
	public NoticeDto selectNoticeByNo(int articleno);
	public void updateHit(int articleno);
	public int insertNotice(NoticeDto notice);
	public int updateNotice(NoticeDto notice);
	public int deleteNotice(int articleno);
}