package com.br.web.board.model.dao;

import static com.br.web.common.template.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.br.web.board.model.vo.Board;
import com.br.web.common.model.vo.PageInfo;

public class BoardDao {
	private Properties prop = new Properties();
	
	public BoardDao() {
		try {
			prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("db/mappers/board-mapper.xml").getPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int selectBoardListCount(Connection conn) {
		// select => ResultSet(게시글 갯수, 숫자한개)  => int
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("count");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rset);
			close(pstmt);
		}
		return listCount;
	}
	
	public List<Board> selectBoardList(Connection conn,PageInfo pi){
		// select => ResultSet(여러행) => List<Board>
		List<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectBoardList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			/*
			 * 	ex) boardLimit이 10이라는 가정하
			 * 		currentPage 1일 경우 => 시작값 : 1 / 끝값 : 10
			 * 		currentPage 2일 경우 => 시작값 : 11/ 끝값 : 20
			 * 
			 * 	시작값 : (currentPage -1 ) * boardLimit + 1
			 * 
			 */
			
	         *    시작값 : (currentPage -1 ) * boardLimit + 1
	          *    끝 값    : 시작값 + boardLimit - 1
	          * 
	          */
	         
	         int startRow = (pi.getCurrentPage() -1 ) * pi.getBoardLimit() + 1;
	         int endRow = startRow + pi.getBoardLimit() - 1;
	         
	         pstmt.setInt(1, startRow);
	         pstmt.setInt(2, endRow);
	         
	         rset = pstmt.executeQuery();
	         
	         while(rset.next()) {
	            list.add(new Board())
	         }
			int startRow;
			int endRow;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
			close(rset);
		}
		
		return list;
	}
	
	
	
}
