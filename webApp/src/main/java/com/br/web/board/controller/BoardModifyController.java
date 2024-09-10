package com.br.web.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.web.board.model.service.BoardService;
import com.br.web.board.model.vo.Category;

/**
 * Servlet implementation class BoardModifyController
 */
@WebServlet("/modify.bo")
public class BoardModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardModifyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int boardNo = Integer.parseInt(request.getParameter("no"));
		
		BoardService bService = new BoardService();
		List<Category> list = bService.selectCategoryList();
		Map<String, Object> map = bService.selectBoardByNo(boardNo);
		
		// 응답페이지 : 수정페이지 (/views/board/boardModify.jsp)
		// 응답데이터 : 카테고리목록, 게시글데이터, 첨부파일데이터 
		
		if(map.get("b") == null) {
			request.setAttribute("msg", "존재하지 않는 게시글이거나 삭제된 게시글입니다.");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
		}else {
			request.setAttribute("list", list);
			request.setAttribute("map", map);
			request.getRequestDispatcher("/views/board/boardModify.jsp").forward(request, response);
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
