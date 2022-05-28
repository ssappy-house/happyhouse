package com.ssafy.happyhouse.service;

import com.ssafy.happyhouse.dto.MemberDto;

public interface MemberService {

	public MemberDto login(MemberDto memberDto) throws Exception;
	public MemberDto userInfo(String userid) throws Exception;
	public boolean register(MemberDto member) throws Exception;
	public boolean updateMember(MemberDto member) throws Exception;
	public boolean deleteMember(String userid) throws Exception;
	
	
}
