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
import com.br.web.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 메뉴바에 있는 메뉴 클릭시    /list.bo						=> 1번 페이지 요청
		// 목록페이지의 페이징바 클릭시 /list.bo?page=클릭한페이지번호  => 클릭한 페이지 요청
		
		// ------------- 페이징 처리 -------------
		// * listCount 	 : 현재 게시글 총 갯수 (db로부터 조회)
		int listCount = new BoardService().selectBoardListCount();
		// * currentPage : 사용자가 요청한 페이지 번호 (요청시 전달됨|전달된게 없으면 1로 간주)
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		// * pageLimit   : 페이징바의 목록 수 (몇개 단위씩 보여지게 할건지)
		int pageLimit = 10;
		// * boardLimit  : 한 페이지에 보여질 게시글 수 (몇개 단위씩 보여지게 할건지)
		int boardLimit = 10;
		
		// 위의 4개를 가지고 사용자가 요청한 페이지 하단에 보여질 
		// 페이징바의 시작수, 끝수, 가장마지막페이지수를 구해야됨
		
		/*
		 * * maxPage : 가장 마지막 페이지 수 (즉, 총 페이지 수)
		 *   listCount, boardLimit 가지고 구하기 
		 *   
		 *   listCount	boardLimit	maxPage
		 *      100			10		  10
		 *      101			10		  11
		 *      105			10		  11
		 *      110			10		  11
		 *      
		 *      즉,
		 *      101~110	 /  10	=> 10.1~11.0  => 올림 =>  11
		 *      111~120	 /	10	=> 11.1~12.0  => 올림 =>  12
		 */
		int maxPage = (int)Math.ceil( (double)listCount / boardLimit );
		
		/*
		 * * startPage : 사용자가 요청한 페이지 하단에 보여질 페이징바의 시작수 
		 *   pageLimit, currentPage 가지고 구함
		 *   
		 *   ex) pageLimit이 10이라는 가정하에 
		 *       startPage는 1, 11, 21, 31이 나올 수 있음
		 *       			=> n * pageLimit + 1 (즉, pageLimit 배수 + 1)
		 *       
		 *       currentPage	pageLimit	startPage
		 *       	 1				10			1	(0 * pageLimit + 1)
		 *       	 5				10			1	(0 * pageLimit + 1)
		 *       	10				10			1	(0 * pageLimit + 1)
		 *       	11				10		   11	(1 * pageLimit + 1)
		 *       	15				10		   11	(1 * pageLimit + 1)
		 *       	20				10		   11	(1 * pageLimit + 1)
		 *       
		 *          즉,
		 *          1~10	=> n 자리가 0
		 *         11~20	=> n 자리가 1
		 *         21~30	=> n 자리가 2
		 *         ...
		 *         
		 *         n 자리는 (currentPage - 1) / pageLimit 
		 *   
		 */
		int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		/*
		 * * endPage : 사용자가 요청한 페이지 하단에 보여질 페이징바의 끝 수
		 *   startPage, pageLimit으로 구하기
		 *   
		 *   ex) pageLimit이 10이라는 가정하에
		 *   
		 *       startPage	endPage
		 *       	1		  10
		 *         11		  20
		 *         21		  30	
		 *         
		 *         즉, startPage + pageLimit - 1
		 */
		int endPage = startPage + pageLimit - 1;
		// 단, 위의 과정으로 구해진 endPage가 maxPage보다 클 경우 수정
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// * 페이징바를 제작하기 위한 데이터 => PageInfo vo 객체
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
		
		// * 사용자가 요청한 페이지상에 필요한 게시글 데이터 조회
		List<Board> list = new BoardService().selectBoardList(pi);
	
		// 응답페이지 : 일반게시글 목록페이지 (/views/board/boardList.jsp)
		// 응답데이터 : 페이징바 제작할 데이터, 게시글 데이터 
		request.setAttribute("pi", pi);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/views/board/boardList.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
