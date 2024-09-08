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
import com.br.web.board.model.vo.Category;
import com.br.web.common.model.vo.PageInfo;


public class BoardDao {
      
   private Properties prop = new Properties();
   
   public BoardDao() {
      try {
         prop.loadFromXML(new FileInputStream(BoardDao.class.getResource("/db/mappers/board-mapper.xml").getPath()));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }//BoardDao
   
   public int selectBoardListCount(Connection conn) {
      //select->ResultSet(게시글 개수, 숫자 한 개) =>int
      int listCount = 0;
      PreparedStatement pstmt = null;
      ResultSet rset = null;
      String sql = prop.getProperty("selectBoardListCount");
      
      try {
         pstmt = conn.prepareStatement(sql);
         rset = pstmt.executeQuery();
         
         if(rset.next()) {
            listCount = rset.getInt("COUNT");
         }
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
      
      return listCount;
   } //selectBoardListCount

   public List<Board> selectBoardList(Connection conn, PageInfo pi) {
      //select -> ResultSet(여러행) ->List<Board>
      List<Board> list = new ArrayList<>();
      ResultSet rset = null;
      PreparedStatement pstmt = null;
      String sql = prop.getProperty("selectBoardList");
      
      try {
         pstmt = conn.prepareStatement(sql);
         /*
          * boardLimit이 10이라는 가정 하
          * currentPage 1 일경우 =>시작값 : 1 / 끝 값 : 10
          * currentPage 2 일경우 =>시작값 : 11/ 끝 값 : 20
          * 시작값 : (currentPage -1) * boardLimit +1
          * 끝  값 : 시작값 + boardLimit -1
          * 
          */
         int startRow = (pi.getCurrentPage()-1)* pi.getBoardLimit() + 1;
         int endRow = startRow + pi.getBoardLimit();
         pstmt.setInt(1, startRow);
         pstmt.setInt(2, endRow);
         
         rset = pstmt.executeQuery();
         
         while(rset.next()) {
            list.add(new Board(rset.getInt("BOARD_NO")
                           , rset.getString("CATEGORY_NAME")
                           , rset.getString("BOARD_TITLE")
                           , rset.getString("USER_ID")
                           , rset.getString("BOARD_COUNT")
                           , rset.getDate("REGIST_DATE")
                  ));
         }
         
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close(rset);
         close(pstmt);
      }
         return list;
      }
   
   public List<Category> selectCategoryList(Connection conn ){
	   // 
	   List<Category> list = new ArrayList<>();
	   PreparedStatement pstmt = null;
	   ResultSet rset = null;
	   String sql = prop.getProperty("selectCategoryList");
	   
	   try {
		pstmt = conn.prepareStatement(sql);
		rset = pstmt.executeQuery();
		
		while(rset.next()) {
			list.add(new Category(rset.getInt("category_no")
								, rset.getString("category_name")));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		close(rset);
		close(pstmt);
	}
	   return list;
	   
   }
   
}//end