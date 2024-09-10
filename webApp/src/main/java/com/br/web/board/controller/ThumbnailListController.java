package com.br.web.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.web.board.model.service.BoardService;
import com.br.web.board.model.vo.Board;

/**
 * Servlet implementation class ThumbnailListController
 */
@WebServlet("/list.th")
public class ThumbnailListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 필요한 데이터 조회하기 (나중에 진행)
		List<Board> list = new BoardService().selectThumbnailList();
		
		// 응답페이지 : 사진게시판 목록페이지
		// 응답데이터 : 사진게시글 데이터(게시글 번호, 대표이미지, 제목) 
		request.setAttribute("list", list);
		request.getRequestDispatcher("/views/board/thumbnailList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
