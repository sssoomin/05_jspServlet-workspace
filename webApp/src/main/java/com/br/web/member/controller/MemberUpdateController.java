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
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 요청
		request.setCharacterEncoding("utf-8");
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestArr = request.getParameterValues("interest");
		String interest = interestArr == null ? "" : String.join(",", interestArr);
		
		Member m = new Member(userId, userName, phone, email, address, interest);
		
		Member updateMem = new MemberService().updateMember(m);
		
		// 2. 응답
		if(updateMem == null) { // 정보변경 실패
			// 응답페이지: 에러페이지
			// 응답데이터: "회원 정보 변경 실패" 메세지
			request.setAttribute("msg", "회원 정보 변경 실패");
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
			
		}else { // 정보변경 성공
			// session에 담겨있는 loginUser를 갱신된 회원객체로 변경
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", updateMem);
			
			// 응답페이지: 다시 마이페이지
			// 응답데이터 : "성공적으로 회원정보를 수정했습니다." alert 메세지
			session.setAttribute("alertMsg", "성공적으로 회원정보를 수정했습니다");
			
			// /web/myinfo.me  url재요청 => 마이페이지 포웓;ㅇ
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
