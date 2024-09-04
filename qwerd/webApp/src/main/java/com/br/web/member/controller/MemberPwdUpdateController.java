package com.br.web.member.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.br.web.member.model.service.MemberService;
import com.br.web.member.model.vo.Member;

/**
 * Servlet implementation class MemberPwdUpdateController
 */
@WebServlet("/updatePwd.me")
public class MemberPwdUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberPwdUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 1. 요청
		/*
		String userId = request.getParameter("userId"); // 몰래 숨겨서 넘긴 데이터
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		Map<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userPwd", userPwd);
		map.put("updatePwd", updatePwd);
		*/
		Map<String, String> map = new HashMap<>();
		map.put("userId", request.getParameter("userId"));
		map.put("userPwd", request.getParameter("userPwd"));
		map.put("updatePwd", request.getParameter("updatePwd"));
		
		Member updateMem = new MemberService().updateMemberPwd(map);
		
		// 2. 응답
		HttpSession session = request.getSession();
		
		if(updateMem == null) { // 실패
			// 응답페이지 : 마이페이지 
			// 응답데이터 : "비밀번호 변경 실패" alert 메세지
			session.setAttribute("alertMsg", "비밀번호 변경 실패");
		}else { // 성공
			// session에 담겨있는 회원 객체 갱신 
			session.setAttribute("loginUser", updateMem);
			// 응답페이지 : 마이페이지 
			// 응답데이터 : "성공적으로 비밀번호가 변경되었습니다." alert 메세지
			session.setAttribute("alertMsg", "성공적으로 비밀번호가 변경되었습니다.");
		}
		
		// 성공이든 실패든 /web/myinfo.me   url 재요청 => 마이페이지로 포워딩
		response.sendRedirect(request.getContextPath() + "/myinfo.me");
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}