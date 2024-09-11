package com.br.web.ajax.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.web.notice.model.service.NoticeService;
import com.br.web.notice.model.vo.Notice;
import com.google.gson.Gson;

/**
 * Servlet implementation class AjaxController4
 */
@WebServlet("/test4.do")
public class AjaxController4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxController4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// [Notice 객체, ...]
		List<Notice> list  = new NoticeService().selectNoticeList();
		
		/*
		// => JSONArray[JSONObject, JSONObject, ...]
		JSONArray jArr = new JSONArray();
		for(Notice n : list) {
			JSONObject jObj = new JSONObject();
			jObj.put("noticeNo", n.getNoticeNo());
			jObj.put("noticeTitle", n.getNoticeTitle());
			jObj.put("noticeContent", n.getNoticeContent());
			jObj.put("noticeWriter", n.getNoticeWriter());
			jArr.add(jObj);		
		}
		*/
		
		response.setContentType("apllication/json; charset=UTF-8");
		new Gson().toJson(list, response.getWriter());
		
		/*
		 * 
		 * *******************주석문 쌤 깃 따오기
		 * Gson 사용해서 자바 객체 응답시 변환되는 형태
		 * 
		 * case1: Map {"name"="정루피", age=10}
		 * {
		 * 	"name" : "정루피",
		 * 	"age" : 10	
		 * 
		 * }
		 * 
		 * case2: Map {"b"=Board객체, "at"=Attachment객체}
		 * {
		 * 
		 * }
		 */
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
