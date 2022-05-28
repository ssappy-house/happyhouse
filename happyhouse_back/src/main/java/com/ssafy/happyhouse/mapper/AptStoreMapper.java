package com.ssafy.happyhouse.mapper;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.dto.AptStoreDto;

public interface AptStoreMapper {

	List<AptStoreDto> getStoreAroundApt(String lng, String lat, String category) throws SQLException;

	//	List<SidoGugunCodeDto> getSido() throws SQLException;
//	List<SidoGugunCodeDto> getGugunInSido(String sido) throws SQLException;
//	List<HouseInfoDto> getDongInGugun(String gugun) throws SQLException;
//	List<HouseInfoDto> getAptInDong(String dong) throws SQLException;
	
}
