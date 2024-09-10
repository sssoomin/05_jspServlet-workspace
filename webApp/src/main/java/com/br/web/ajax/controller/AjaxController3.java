package com.br.web.ajax.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.web.board.model.service.BoardService;
import com.br.web.board.model.vo.Board;
import com.google.gson.Gson;

/**
 * Servlet implementation class AjaxController3
 */
@WebServlet("/test3.do")
public class AjaxController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("no"));
		
		Map<String, Object> map = new BoardService().selectBoardByNo(boardNo);
		Board b = (Board)map.get("b");
		
		/* 잘못된 방법 : 자바에서의 객체를 바로 응답하면 안됨
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(b); // 사실상 b.toString()의 문자열이 응답됨
		*/
		
		// JSON객체로 변환해서 응답
		// vo객체 => JSONObject
		// List객체 => JSONArray
		
		/* 직접 변환하기 : 직접 JSONObject객체에 옮겨담기
		JSONObject jobj = new JSONObject(); // {}
		if( b != null) {
			jobj.put("boardTitle", b.getBoardTitle()); // {"boardTitle" : "제목"}
			jobj.put("boardWriter", b.getBoardWriter()); // {"boardTitle" : "제목", "boardWriter" : }
			jobj.put("boardContent", b.getBoardContent());
		}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jobj);
		*/
		
		// 위의 과정을 대신해서 해주는 GSON 라이브러리 활용
		response.setContentType("application/json; charset=UTF-8");
		// Gson객체.toJson(응답할데이터(자바객체), 스트림객체);
		new Gson().toJson(b, response.getWriter());
		// vo객체 같은 경우
		// 내부적으로 자동으로 JSONObject{필드명:필드값, 필드명:필드값, ..} 형태로 만들어져서 응답됨
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
