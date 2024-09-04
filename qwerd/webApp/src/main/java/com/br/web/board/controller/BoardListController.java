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
		//메뉴바에 있는 메뉴 클릭시 /list.bo 							=> 1번 페이지 요청
		//목록페이지의 페이징바 클릭시 /list.bo?page=클릭한페이지번호 	=> 클릭한 페이지 요청
		
		// ---------------페이징 처리 ------------------
		// listCount	: 현재 게시글 총 갯수
		int listCount = new BoardService().selectBoardListCount();
		// currentPage 	: 사용자가 요청한 페이지 번호 (요청시 전달됨 | 전달된게 없으면 1로 간주)
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		// pageLimit : 페이징바의 목록 수 (몇개 단위씩 보여지게 할건지)
		int pageLimit = 10;
		// boardLimit : 한 페이지에 보여질 게시글 수 (몇개 단위씩 보여지게 할건지)
		int boardLimit = 10;
		
		// 위의 4개를 가지고 사용자가 요청한 페이지 하단에 보여질 
		//페이징바의 시작수, 끝수, 가장마지막페이지 수
	
		/*
		 * 	maxPage : 가장 마지막 페이지 수 (즉, 총 페이지 수)
		 * 	listCount,boardLimit 가지고 구하기
		 * 
		 */
		int maxPage = (int)Math.ceil((double) listCount / boardLimit );
		
		/*
		 * StartPage : 사용자가 요청한 페이지 하단에 보여질 페이징바의 시작수
		 * 	pageLimit, currentPage 가지고 구함
		 * 	
		 * 	ex) pageLimit이 10이라는 가정하에
		 * 		startPage는 1,11,21,31이 나올 수 있음
		 * 						=> n * pageLimit + 1 (즉, pageLimit 배수 + 1 )
		 */
		
		int startPage = (currentPage -1 ) / pageLimit * pageLimit + 1;
		
		/*
		 *  endPage : 사용자가 요청한 페이지 하단에 보여질 페이징바의 끝 ㅅ 
		 *  startPage, pageLimit으로 구하기
		 *  
		 *  ex) pageLimit이 10이라는 가정하에
		 *  	
		 *  	즉, startPage + pageLimit -1;
		 */
		
		int endPage = startPage + pageLimit -1 ;
		//단, 위의 과정으로 구해진 endPage가 maxPage보다 클 경우 수정
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 페이징처리 변수 7개 시발 새끼
		
		//페이징바를 제작하기 위한 데이터 => pageInfo vo 객체
		PageInfo pi = new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
		// 사용자가 요청한 페이지상에 필요한 게시글 데이터 필요
		List<Board> list = new BoardService().selectBoardList(pi);
		
		// 응답페이지 : 일반게시글 목록페이지 (/views/board/boardList.jsp)
		// 응답데이터 : 페이징바 제작할 데이터, 게시글 데이터
		request.setAttribute("pi", pi);
		request.setAttribute("list", List);
		
		RequestDispatcher("/views/board/boardList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
