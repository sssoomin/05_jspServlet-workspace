package c_response;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Response
 */
@WebServlet("/Response")
public class Response extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Response() {
        super();
        // TODO Auto-generated constructor stub
    }

	/** 주석문 따오기
	 * * HttpServletResponse
	 * 1. 요청을 보낸 클라이언트에게 응답할 수 있는 객체
	 * 2. doGet() 또는 do post() 메소드 실행시 해당 객체가 생성되어 매개변수(response)에 전달
	 * 3. 응답 주요 메소드
	 * 	1) getWriter()  : 클라이언트에게 응답문자를 전송할 수 있는 출력 스트림(PrintWriter) 반환
	 * 	2) setContentType(String) : 응답문자의 데이터형식(MIME-TYPE)을 설정
	 * 	3) sendRed : 리다이렉트 할때 URL 전송
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		
		// 요청시 전달값 => service => dao => select 쿼리 실행 후 결과 받음 (JDBC과정)
		// 성공적으로 조회되었다는 가정하로 응답페이지 제작 후 출력
		
		/*
		 * 1. 응답 Content-type 설정 + UTF-8 인코딩
		 * 	1) HTML : text / html
		 * 	2) XML : application / xml
		 * 	3) JSON : application / json
		 * 
		 */
		
		response.setContentType("text/html; charset=utf-8");
		
		/*
		 * 2. 응답할 출력 스트림 알아내기
		 */
		PrintWriter out = response.getWriter();
		
		/*
		 * 3. 응답문자 전송
		 */
		out.println("<!DOCTYPE html");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset='utf-8'>");
		out.println("<title>로그인성공페이지</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<script>alert('짝짝짝!');</script>");
		out.println("<h1>반갑습니다 회원님!" + userId + "계정으로 로그인 성공입니다.</h1>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response); // doGet으로 결국 토스하고 있음
								  // get방식 요청이든 post방식 요청이든
								  // 그냥 편하게 doGet메소드에서 작업하면 됨
	}

}
