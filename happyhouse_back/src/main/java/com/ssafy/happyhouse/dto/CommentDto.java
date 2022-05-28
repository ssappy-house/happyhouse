package com.ssafy.happyhouse.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Comment (댓글 정보)", description = "댓글번호, 작성자아이디, 게시글번호, 내용, 작성시간를 가진 Domain Class")
public class CommentDto {
	
	@ApiModelProperty(value = "댓글번호")
	private int commentno;
	
	@ApiModelProperty(value = "작성자아이디")
	private String userid;
	
	@ApiModelProperty(value = "게시글번호")
	private int boardno;
	
	@ApiModelProperty(value = "내용")
	private String content;
	
	@ApiModelProperty(value = "작성시간")
	private String regtime;
	

	public CommentDto() {
		
	}

	public CommentDto(int commentno, String userid, int boardno, String content, String regtime) {
		this.commentno = commentno;
		this.userid = userid;
		this.boardno = boardno;
		this.content = content;
		this.regtime = regtime;
	}

	public int getCommentno() {
		return commentno;
	}

	public void setCommentno(int commentno) {
		this.commentno = commentno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public int getBoardno() {
		return boardno;
	}

	public void setBoardno(int boardno) {
		this.boardno = boardno;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	

}