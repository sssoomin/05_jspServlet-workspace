package com.br.web.board.model.service;

import java.sql.Connection;
import java.util.List;

import com.br.web.board.model.dao.BoardDao;
import com.br.web.board.model.vo.Board;
import com.br.web.common.model.vo.PageInfo;

import static com.br.web.common.template.JDBCTemplate.getConnection;
import static com.br.web.common.template.JDBCTemplate.close;

public class BoardService {
	private BoardDao bDao = new BoardDao();

	public int selectBoardListCount() {
		Connection conn = getConnection();
		int listCount = bDao.selectBoardListCount(conn);
		close(conn);
		return listCount;
	}

	public List<Board> selectBoardList(PageInfo pi) {
		Connection conn = getConnection();
		List<Board> list = bDao.selectBoardList(conn, pi);
		
		close(conn);
		
		return list;
	}
}
