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

import com.ssafy.happyhouse.dto.CommentDto;
import com.ssafy.happyhouse.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api("CommentController V1")
@RestController
@RequestMapping("/comment")
//@CrossOrigin(origins = { "*" }, maxAge = 6000)
public class CommentController {

	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private CommentService commentService;

    @ApiOperation(value = "게시글 댓글목록", notes = "게시글의 모든 댓글 정보를 반환한다.", response = List.class)
	@GetMapping("/{boardno}")
	public ResponseEntity<List<CommentDto>> retrieveCommentByBoardno(@PathVariable int boardno) throws Exception {
		logger.debug("retrieveCommentByBoardno - 호출");
		return new ResponseEntity<List<CommentDto>>(commentService.retrieveCommentByBoardno(boardno), HttpStatus.OK);
	}

    @ApiOperation(value = "게시글 댓글 등록", notes = "게시글에 새로운 댓글을 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeComment(@RequestBody CommentDto comment) {
		logger.debug("writeComment - 호출");
		if (commentService.writeComment(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "게시글 댓글 수정", notes = "댓글번호에 해당하는 댓글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("/{commentno}")
	public ResponseEntity<String> updateComment(@RequestBody CommentDto comment) {
		logger.debug("updateComment - 호출");
		if (commentService.updateComment(comment)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "게시글 댓글 삭제", notes = "댓글번호에 해당하는 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{commentno}")
	public ResponseEntity<String> deleteComment(@PathVariable int commentno) {
		logger.debug("deleteComment - 호출");
		if (commentService.deleteComment(commentno)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "게시글 모든 댓글 삭제", notes = "게시글 번호에 해당하는 모든 댓글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("/{boardno}/all")
	public ResponseEntity<String> deleteAllCommentByBoardno(@PathVariable int boardno) {
		logger.debug("deleteAllCommentByBoardno - 호출");
		commentService.deleteAllCommentByBoardno(boardno);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
}