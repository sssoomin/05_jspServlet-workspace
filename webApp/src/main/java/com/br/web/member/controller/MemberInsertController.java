package com.br.web.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.br.web.member.model.service.MemberService;
import com.br.web.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청
		// 요청시 전달값 뽑기
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId"); // "user03"
		String userPwd = request.getParameter("userPwd"); // "pass03"
		String userName = request.getParameter("userName"); // "강개똥"
		String phone = request.getParameter("phone"); // "010-1111-2222" | ""
		String email = request.getParameter("email"); // "ssss@sss.com" | ""
		String address = request.getParameter("address"); // "서울시" | ""
		String[] interestArr = request.getParameterValues("interest"); // ["운동", "등산"] | null
		String interest = "";
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
		
		Member m = new Member(userId, userPwd, userName, phone, email, address, interest);
		
		
		// 서비스 호출 (쿼리실행)
		int result = new MemberService().insertMember(m);
		
		
		// 응답
		if(result > 0) {
			/*
			 * * 회원가입 성공
			 *   ㄴ 응답페이지 : 메인페이지
			 *   ㄴ 응답데이터 : "성공적으로 회원가입 되었습니다." alert 메세지
			 */
			
			request.getSession().setAttribute("alertMsg", "성공적으로 회원가입 되었습니다");
			response.sendRedirect(request.getContextPath());
			
		}else {
			/*
			 * * 회원가입 실패
			 *   ㄴ 응답페이지 : 에러페이지 
			 *   ㄴ 응답데이터 : "회원가입 실패" 메세지 (해당 응답페이지에서만 필요)
			 */
			request.setAttribute("msg", "회원가입 실패");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
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
