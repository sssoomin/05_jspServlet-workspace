package com.br.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.br.web.member.model.service.MemberService;
import com.br.web.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 요청
		String userPwd = request.getParameter("userPwd");
		HttpSession session = request.getSession();
		String userId= ((Member)session.getAttribute("loginUser")).getUserId();
		
		int result = new MemberService().deleteMember(userId, userPwd);
		
		// 2. 응답
		if(result > 0) { // 성공
			// session에 담겨있는 회원데이터 지우기
			session.removeAttribute("loginUser");
			// 응답페이지 : 메인페이지
			// 응답데이터 : "성공적으로 탈퇴" alert 메세지
			session.setAttribute("alertMsg", "성공적으로 탈퇴되었습니다. 그동안 이용해주셔서 감사합니다.");
			response.sendRedirect(request.getContextPath());
			
		}else { // 실패
			// 응답페이지 : 마이페이지
			// 응답데이터 : "회원탈퇴 실패" alert 메세지
			session.setAttribute("alertMsg", "회원 탈퇴 실패");
			response.sendRedirect(request.getContextPath() + "/myinfo.me");
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
