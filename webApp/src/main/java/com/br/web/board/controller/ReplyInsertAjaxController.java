package com.br.web.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.web.board.model.service.BoardService;
import com.br.web.board.model.vo.Reply;
import com.br.web.member.model.vo.Member;

/**
 * Servlet implementation class ReplyInsertAjaxController
 */
@WebServlet("/insert.re")
public class ReplyInsertAjaxController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertAjaxController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("no"));
		String replyContent = request.getParameter("content");
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		Reply r = new Reply();
		r.setRefBoardNo(boardNo);
		r.setReplyContent(replyContent);
		r.setReplyWriter(String.valueOf(userNo));
		
		int result = new BoardService().insertReply(r);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(result);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
