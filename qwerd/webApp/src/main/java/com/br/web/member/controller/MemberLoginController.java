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
 * Servlet implementation class MemberLoginController
 */
@WebServlet("/login.me")
public class MemberLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 요청 ([요청시 전달값 뽑기] => [JDBC과정을 통해 쿼리실행 후 결과받기])
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		Member loginUser = new MemberService().loginMember(userId, userPwd); // null|생성된객체		
		
		// 2. 응답
		/*
		 * * 요청 처리 후 특정 데이터를 담을 수 있는 객체
		 *   1. 해당 객체들은 "JSP 내장객체"로 JSP에서 바로 사용 가능함
		 *   2. 주로 응답페이지에서 사용할 데이터를 담음
		 *   3. 어떤 객체에 담냐에 따라 사용할 수 있는 범위가 다름
		 *   4. 종류
		 *      1) ServletContext application
		 *         ㄴ 컨텍스트 - 애플리케이션 하나당 한 개 존재 (즉, 애플리케이션에 유지할 데이터 담기)
		 *         ㄴ 애플리케이션 종료 전까지 데이터 사용 가능함 (범위가 가장 넓음)
		 *      2) HttpSession session *
		 *         ㄴ 세션 - 브라우저 하나당 한 개 존재 (즉, 브라우저에 유지할 데이터 담기)
		 *         ㄴ 브라우저 종료 및 서버 종료 전까지 데이터 사용 가능함
		 *      3) HttpServletRequest request *
		 *         ㄴ 요청 - 하나의 요청당 한 개 존재 (즉, 해당 요청에 대한 응답페이지에 유지할 데이터 담기)
		 *         ㄴ forward에 의해 해당 request가 전달된 Servlet 및 JSP에서만 사용 가능함
		 *      4) PageContext page
		 *         ㄴ 페이지 - 한 페이지당 한 개 존재 (즉, 해당 페이지에 유지시킬 데이터 담기)
		 *         ㄴ jsp에서 담고 해당 jsp에서만 사용 가능함
		 *   5. 공통 메소드
		 *      1) 데이터 담기 	   : .setAttribute(String, Object)
		 *      2) 데이터 꺼내기   : .getAttribute(String)   => Object타입으로 반환 (캐스팅해야될수있음)
		 *      3) 데이터 제거하기 : .removeAttribute(String)
		 */
		
		if(loginUser == null) {
			/*
			 * * 로그인 실패
			 *   ㄴ 응답페이지 : 에러페이지 (/web/views/common/errorPage.jsp)
			 *   ㄴ 응답데이터 : "로그인 실패" 메세지 (해당 응답페이지 에서만 필요)
			 */
			// request에 응답데이터 담기
			request.setAttribute("msg", "로그인 실패");
			// request를 전달하면서 응답페이지로 forward(이동)
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			
		}else {
			/*
			 * * 로그인 성공
			 *   ㄴ 응답페이지 : 메인페이지 (/web/index.jsp)
			 *   ㄴ 응답데이터 : 조회된 데이터들이 담겨있는 Member객체 (브라우저 종료 전까지 계속 필요함)
			 */
			// session에 응답데이터 담기
			HttpSession session = request.getSession(); // Servlet에서는 HttpSession 객체를 얻어와서 사용
			session.setAttribute("loginUser", loginUser);
			
			// 이미 메인페이지를 응답하는 URL로 redirect (url재요청)
			response.sendRedirect(request.getContextPath()); // "/web"
			
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