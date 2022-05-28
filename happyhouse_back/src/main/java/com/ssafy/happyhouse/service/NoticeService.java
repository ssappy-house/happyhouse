package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.NoticeDto;

public interface NoticeService {
	public List<NoticeDto> retrieveNotice();
	public NoticeDto detailNotice(int articleno);
	public void updateHit(int articleno);
	public boolean writeNotice(NoticeDto notice);
	public boolean updateNotice(NoticeDto notice);
	public boolean deleteNotice(int articleno);
}
