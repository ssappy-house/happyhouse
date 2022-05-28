package com.ssafy.happyhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.dto.AptStoreDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;
import com.ssafy.happyhouse.mapper.AptStoreMapper;
import com.ssafy.happyhouse.mapper.HouseMapMapper;

@Service
public class AptStoreServiceImpl implements AptStoreService {
	
	@Autowired
	private AptStoreMapper aptStoreMapper;

	public List<AptStoreDto> getStoreAroundApt(String lng, String lat, String category) throws Exception{
		return aptStoreMapper.getStoreAroundApt(lng, lat, category);
	}
	
//	@Override
//	public List<SidoGugunCodeDto> getSido() throws Exception {
//		return houseMapMapper.getSido();
//	}
//
//	@Override
//	public List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception {
//		return houseMapMapper.getGugunInSido(sido);
//	}
//
//	@Override
//	public List<HouseInfoDto> getDongInGugun(String gugun) throws Exception {
//		return houseMapMapper.getDongInGugun(gugun);
//	}
//
//	@Override
//	public List<HouseInfoDto> getAptInDong(String dong) throws Exception {
//		return houseMapMapper.getAptInDong(dong);
//	}

}
