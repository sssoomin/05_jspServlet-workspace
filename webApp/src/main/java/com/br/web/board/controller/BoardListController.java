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
      // 메뉴바에 있는 메뉴 클릭시     /list.bo                  =>1번 페이지 요청
      // 목록 페이지의 페이징바 클릭시 /list.bo?page=클릭페이지번호   =>클릭한 페이지 요청
      
      //-------페이징 처리---------
      //*listCount : 현재 게시글 총 개수(DB에서 조회)
      int listCount = new BoardService().selectBoardListCount();
      
      //*currentPage : 사용자가 요청한 페이지 번호 (요청시전달 | 전달x면1페이지)
      int currentPage = 1;
      if(request.getParameter("page") != null) {
         currentPage = Integer.parseInt(request.getParameter("page"));
      }
      
      //*pageLimit : 페이징바의 목록수< 1 2 3 4 5 > 이부분
      int pageLimit = 10;
      
      //*boardLimit : 한 페이지에 보일 게시글 수(몇개단위씩 보여지게 할 건지)
      int boardLimit = 10;
      
      //위 4개 갖고 사용자가 요청한 페이지 하단에 보여질 
      //페이징바의 시작수, 끝수, 가장 마지막 페이지수를 구해야함
      /*
       * maxPage : 가장 마지막 페이지 수(즉, 총 페이지 수)
       * listCount, boardLimit 갖고 구할 수 있음
       * 
       * listCount  boardLimit  maxPage
       *   100          10        10
       *   101          10        11
       *   105          10        11
       * 
       * 즉, 101~110    10        11
       *     111~120    11        12
       *     
       */
      int maxPage = (int) Math.ceil((double)listCount / boardLimit);
      
      /*
       * startPage : 사용자가 요청한 페이지 하단에 보여질 페이징바의 시작수
       * pageLimit, currentPate갖고 구함 
       * ex) pageLimit 10이라 가정 하
       * startPage 1, 11, 21, 31
       *          => n * pageLimit+1(즉, pageLimit 배수 + 1)
       * 
       *   currentPage   pageLimit  startPage
       *       1             10         1    ( 0 * pageLimit + 1 ) 0~10 -> n=0
       *       5             10         1
       *      11             20         11   ( 1 * pageLimit + 1 ) 11~20-> n=1
       *      15             20         11
       *   n 자리는(currentPage -1 ) / pageLimit
       */
       int startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
       
       /* endPage : 사용자가 요청한 페이지 하단에 보여질 페이징바의 끝 수
        * startPage, pageLimit으로 구하기
        * 
        * ex) pageLimit이 10이라는 가정 하에
        *   startPage  endPage
        *       1        10
        *      11        20
        *      21        30
        * 즉, startPage + pageLimit - 1
        * 
        */
       int endPage = startPage + pageLimit - 1;
       //단, 위 과정으로 구해진 endPage가 maxPage보다 클 경우 수정해 줘야함
       if(endPage > maxPage) {
          endPage = maxPage;
       }
       
       // 페이징 바를 제작하기 위한 데이터 -> PageInfo vo 생성해서 객체에 넣자
       PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
       
       //사용자가 요청한 페이지 상에 필요한 게시글 데이터 조회
       List<Board> list = new BoardService().selectBoardList(pi);
       
       // 응답 페이지 : 일반 게시글 목록 페이지("/views/board/boardList.jsp")
       // 응답 데이터 : 페이징바 제작 할 데이터, 게시글 데이터
       request.setAttribute("pi", pi);
       request.setAttribute("list", list);
       
       request.getRequestDispatcher("/views/board/boardList.jsp").forward(request, response);
   }//doGet

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}
