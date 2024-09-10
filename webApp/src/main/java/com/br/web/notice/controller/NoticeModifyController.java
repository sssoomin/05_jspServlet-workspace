package com.br.web.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.web.notice.model.service.NoticeService;
import com.br.web.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeModifyController
 */
@WebServlet("/modify.no")
public class NoticeModifyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeModifyController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1. 요청
		int noticeNo = Integer.parseInt(request.getParameter("no")); // 수정할 글 번호
		
		Notice n = new NoticeService().selectNoticeByNo(noticeNo);
		// 글번호, 글제목, 글내용
		
		// 2. 응답
		//	  ㄴ 응답페이지 : 수정페이지 (/views/notice/noticeModify.jsp)
		//	  ㄴ 응답데이터 : 수정할 해당 게시글의 제목, 내용 (db로 부터 조회)
		request.setAttribute("n", n);
		request.getRequestDispatcher("/views/notice/noticeModify.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
