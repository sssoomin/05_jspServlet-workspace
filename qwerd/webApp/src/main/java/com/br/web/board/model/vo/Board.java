package com.br.web.board.model.vo;

import java.sql.Date;

public class Board {
	
private int boardNo;
private int boardType;
private String category;		//작성하기 시 카테고리 번호 | 조회시 카테고리명
private String boardTitle;
private String boardContent;
private String boardWriter;		// 작성하기 시 회원번호 | 조회시 회원아이디
private int boardCount;
private Date registDt;
private String status;
	
public Board() {}

public int getBoardNo() {
	return boardNo;
}

public void setBoardNo(int boardNo) {
	this.boardNo = boardNo;
}

public int getBoardType() {
	return boardType;
}

public void setBoardType(int boardType) {
	this.boardType = boardType;
}

public String getCategory() {
	return category;
}

public void setCategory(String category) {
	this.category = category;
}

public String getBoardTitle() {
	return boardTitle;
}

public void setBoardTitle(String boardTitle) {
	this.boardTitle = boardTitle;
}

public String getBoardContent() {
	return boardContent;
}

public void setBoardContent(String boardContent) {
	this.boardContent = boardContent;
}

public String getBoardWriter() {
	return boardWriter;
}

public void setBoardWriter(String boardWriter) {
	this.boardWriter = boardWriter;
}

public int getBoardCount() {
	return boardCount;
}

public void setBoardCount(int boardCount) {
	this.boardCount = boardCount;
}

public Date getRegistDt() {
	return registDt;
}

public void setRegistDt(Date registDt) {
	this.registDt = registDt;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

@Override
public String toString() {
	return "Board [boardNo=" + boardNo + ", boardType=" + boardType + ", category=" + category + ", boardTitle="
			+ boardTitle + ", boardContent=" + boardContent + ", boardWriter=" + boardWriter + ", boardCount="
			+ boardCount + ", registDt=" + registDt + ", status=" + status + "]";
}

public Board(int boardNo, int boardType, String category, String boardTitle, String boardContent, String boardWriter,
		int boardCount, Date registDt, String status) {
	super();
	this.boardNo = boardNo;
	this.boardType = boardType;
	this.category = category;
	this.boardTitle = boardTitle;
	this.boardContent = boardContent;
	this.boardWriter = boardWriter;
	this.boardCount = boardCount;
	this.registDt = registDt;
	this.status = status;
}



}
