package com.ssafy.happyhouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.happyhouse.dto.MemberDto;
import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper memberMapper;
	
	@Override
	public MemberDto login(MemberDto memberDto) throws Exception {
		if(memberDto.getUserid() == null || memberDto.getUserpwd() == null)
			return null;
		return memberMapper.login(memberDto);
	}

	@Override
	public MemberDto userInfo(String userid) throws Exception {
		return memberMapper.userInfo(userid);
	}

	@Override
	public boolean register(MemberDto member) throws Exception {
		return memberMapper.register(member) == 1;
	}

	@Override
	@Transactional
	public boolean updateMember(MemberDto member) throws Exception {
		return memberMapper.updateMember(member) == 1;
	}

	@Override
	@Transactional
	public boolean deleteMember(String userid) throws Exception {
		return memberMapper.deleteMember(userid) == 1;
	}

	
}
