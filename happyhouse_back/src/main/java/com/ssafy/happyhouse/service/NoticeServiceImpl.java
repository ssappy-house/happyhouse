package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.mapper.NoticeMapper;

@Service
public class NoticeServiceImpl implements NoticeService {
	
    @Autowired
	private NoticeMapper boardMapper;

    @Override
	public List<NoticeDto> retrieveNotice() {
		return boardMapper.selectNotice();
	}
    
  	@Override
	public boolean writeNotice(NoticeDto notice) {
		return boardMapper.insertNotice(notice) == 1;
	}

	@Override
	public NoticeDto detailNotice(int articleno) {
		return boardMapper.selectNoticeByNo(articleno);
	}
	
	@Override
	public void updateHit(int articleno) {
		boardMapper.updateHit(articleno);
	}

	@Override
	@Transactional
	public boolean updateNotice(NoticeDto notice) {
		return boardMapper.updateNotice(notice) == 1;
	}

	@Override
	@Transactional
	public boolean deleteNotice(int articleno) {
		return boardMapper.deleteNotice(articleno) == 1;
	}
}