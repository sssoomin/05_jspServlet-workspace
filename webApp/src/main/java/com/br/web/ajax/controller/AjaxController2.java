package com.br.web.ajax.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Servlet implementation class AjaxController2
 */
@WebServlet("/test2.do")
public class AjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String flag = request.getParameter("flag");
		
		// 요청처리 다 됐다는 가정하에
		// 응답데이터 다수 돌려주기
		
		/* 잘못된 방법 : 출력되는 데이터가 하나의 문자열로 연이어져있음 (따로 추출하기 어려움)
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(name);
		response.getWriter().print(age);
		*/
		
		/*
		 * * JSON
		 * 1. JavaScript Object Notation
		 * 2. 자바스크립트 객체 표기법
		 * 3. ajax 통신시 데이터 전송에 자주 사용되는 포맷형식 중 하나
		 * 4. json 라이브러리 필요
		 * 5. 종류
		 * 		1) JSONArray : 자바스크립트에서의 배열 형태로 제작가능
		 * 			ㄴ [value, value, value, ..]
		 * 		2) JSONObject : 자바스크립트에서의 일반 객체 형태로 제작 가능
		 * 			ㄴ {key: value, key: value, ...}
		 * 
		 */
		
		if(flag.equals("array")) {
			// JSONArray 활용해서 응답 연습
			JSONArray jArr = new JSONArray(); // []
			jArr.add(name); // ["정루피"]
			jArr.add(age); // ["정루피", 20]
			
			response.setContentType("application/json; charset=UTF-8");// JSON 데이터를 응답할때
			response.getWriter().print(jArr);
		}else {
			// JSONObject 활용해서 응답 연습
			JSONObject jobj = new JSONObject(); // {}
			jobj.put("name", name); // {"name": "루피"}
			jobj.put("age", age); // {"name": "루피", "age": 20}
			
			response.setContentType("application/json; charset=UTF-8");
			response.getWriter().print(jobj);
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
