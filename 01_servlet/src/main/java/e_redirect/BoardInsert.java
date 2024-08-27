package e_redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BoardInsert
 */
@WebServlet("/insert.bo")
public class BoardInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("게시물 등록 요청용 Servlet(JDBC 과정을 통해 전달 된 데이터 insert진행)");
		
		// 요청시 전달된 값 뽑아서 각 변수에 기록
		request.setCharacterEncoding("UTF-8");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// => service => dao => 게시글 데이터 insert 쿼리 실행
		// 성공적으로 추가되었다는 가정하에
		// 응답페이지 : 다시 게시글 전체 조회 페이지 (단, 새로 추가된 데이터도 조회돼서 보여져야됨)
		
		//request.getRequestDispatcher("/e_redirect//boardList.html").forward(request, response);
		/*
		 * 잘못된 응답방법 : forward
		 * 문제점1. 포워딩을 통해 해당 페이지가 보여지긴 하나 여전히 /servlet/insert.bo 가 노출되어있음
		 * 문제점2. 새로 추가된 데이터를 다시 조회해서 뿌려야만 하는데 새로이 조회가 진행되지 않음
		 * 
		 * 해결방법
		 * Insert 후에 다시 select하고 게시글전체조회페이지로 이동해야됨
		 * 근데 DB로부터 데이터를 조회해서 해당 페이지로 이동하는 Servlet이 이미 존재함 (BoardList)
		 * => 해당 Servlet을 재 호출하면 됨 (URL 재요청, redirect)
		 */
		response.sendRedirect("/servlet/list.bo"); // => /list.bo의 Servlet을 재실행
		
		/*
		 * * redirect
		 * 1. 기존에 정의해둔 Servlet을 재실행 시키는 개념 (URL 요청과 동일)
		 * 2. 등록 및 수정/삭제 후에 다시 조회페이지를 요청할때 주로 사용함
		 * 3. redirect 시 현재 서블릿에 존재하는 request, response 전달하지 않음
		 * 4. 요청할 URL 작성시 contextPath부터 작성
		 * 5. 클라이언트 측에서 redirect 된 경로 확인할 수 있음
		 * 	(URL에 현재 redirect한 URL이 보여짐)
		 * 6. 주로 redirect 하는 경우
		 * 	1) insert 쿼리 이후 => 전체 조회 페이지 redirect
		 * 	2) update 쿼리 이후 => 상세 조회 페이지 redirect
		 * 	3) delete 쿼리 이후 => 전체 조회 페이지 redirect
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
