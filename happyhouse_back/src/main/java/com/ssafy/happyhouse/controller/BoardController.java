package com.ssafy.happyhouse.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.dto.BoardDto;
import com.ssafy.happyhouse.dto.NoticeDto;
import com.ssafy.happyhouse.service.BoardService;
import com.ssafy.happyhouse.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("FreeboardController V1")
@RestController
@RequestMapping("/board")
//@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService boardService;

    @ApiOperation(value = "자유게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping("/free")
	public ResponseEntity<List<BoardDto>> retrieveFreeboard() throws Exception {
		logger.debug("retrieveFreeboard - 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.retrieveFreeboard(), HttpStatus.OK);
	}
    
    @ApiOperation(value = "지역게시판 글목록", notes = "해당하는 동코드의 모든 게시글 정보를 반환한다.", response = List.class)
	@GetMapping("/local/{dongCode}")
	public ResponseEntity<List<BoardDto>> retrieveLocalboard(@PathVariable String dongCode) throws Exception {
		logger.debug("retrieveLocalboard - 호출");
		return new ResponseEntity<List<BoardDto>>(boardService.retrieveLocalboard(dongCode), HttpStatus.OK);
	}

    @ApiOperation(value = "자유게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)    
	@GetMapping("/{boardno}")
	public ResponseEntity<BoardDto> detailFreeboard(@PathVariable int boardno) {
		logger.debug("detailFreeboard - 호출");
		return new ResponseEntity<BoardDto>(boardService.detailFreeboard(boardno), HttpStatus.OK);
	}

    @ApiOperation(value = "자유게시판 글등록", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeFreeboard(@RequestBody BoardDto board) {
		logger.debug("writeFreeboard - 호출");
		if (boardService.writeFreeboard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "자유게시판 글정보 수정", notes = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{boardno}")
	public ResponseEntity<String> updateFreeboard(@RequestBody BoardDto board) {
		logger.debug("updateFreeboard - 호출");
		if (boardService.updateFreeboard(board)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "자유게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{boardno}")
	public ResponseEntity<String> deleteFreeboard(@PathVariable int boardno) {
		logger.debug("deleteFreeboard - 호출");
		if (boardService.deleteFreeboard(boardno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}