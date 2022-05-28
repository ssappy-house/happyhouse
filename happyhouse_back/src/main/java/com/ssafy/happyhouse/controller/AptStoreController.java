package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.AptStoreDto;
import com.ssafy.happyhouse.service.AptStoreService;

import io.swagger.annotations.Api;

@Api("ApiStoreController V1")
@RestController
@RequestMapping("/aptstore")
@CrossOrigin("*")
public class AptStoreController {
	
	private final Logger logger = LoggerFactory.getLogger(AptStoreController.class);

	@Autowired
	private AptStoreService aptStoreService;
	
	
	@GetMapping("/category")
	public ResponseEntity<List<AptStoreDto>> store(@RequestParam("lng") String lng, @RequestParam("lat") String lat, @RequestParam("category") String category) throws Exception {
		return new ResponseEntity<List<AptStoreDto>>(aptStoreService.getStoreAroundApt(lng, lat, category), HttpStatus.OK);
	}
	
//	@GetMapping("/sido")
//	public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
////		logger.debug("sido : {}", haHouseMapService.getSido());
//		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getSido(), HttpStatus.OK);
//	}
//	
//	@GetMapping("/gugun")
//	public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam("sido") String sido) throws Exception {
////		logger.debug("gugun : {}", haHouseMapService.getGugunInSido(sido));
//		return new ResponseEntity<List<SidoGugunCodeDto>>(haHouseMapService.getGugunInSido(sido), HttpStatus.OK);
//	}
//	
//	@GetMapping("/dong")
//	public ResponseEntity<List<HouseInfoDto>> dong(@RequestParam("gugun") String gugun) throws Exception {
//		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getDongInGugun(gugun), HttpStatus.OK);
//	}
//	
//	@GetMapping("/apt")
//	public ResponseEntity<List<HouseInfoDto>> apt(@RequestParam("dong") String dong) throws Exception {
//		return new ResponseEntity<List<HouseInfoDto>>(haHouseMapService.getAptInDong(dong), HttpStatus.OK);
//	}
	
}
