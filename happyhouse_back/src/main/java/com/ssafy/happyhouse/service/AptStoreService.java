package com.ssafy.happyhouse.service;

import java.util.List;

import com.ssafy.happyhouse.dto.AptStoreDto;
import com.ssafy.happyhouse.dto.HouseInfoDto;
import com.ssafy.happyhouse.dto.SidoGugunCodeDto;

public interface AptStoreService {
	
	List<AptStoreDto> getStoreAroundApt(String lng, String lat, String category) throws Exception;


//	List<SidoGugunCodeDto> getSido() throws Exception;
//	List<SidoGugunCodeDto> getGugunInSido(String sido) throws Exception;
//	List<HouseInfoDto> getDongInGugun(String gugun) throws Exception;
//	List<HouseInfoDto> getAptInDong(String dong) throws Exception;
	
}
