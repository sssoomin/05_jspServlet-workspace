package e_redirect;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Redirect
 */
@WebServlet("/list.bo")
public class BoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("게시글 전체 조회용 Servlet 실행 (JDBC과정을 통애 필요 데이터 select 진행)");
		// => service => dao => 게시글 전체 데이터 select 쿼리 실행 (JDBC)
		// 위와 같이 조회된 데이터를 가지고 응답페이지 제작
		
		// 위의 과정이 성공적으로 수행됐다는 가정하에 응답페이지 : /servlet/e_redirect/boardList.html
		request.getRequestDispatcher("/e_redirect/boardList.html").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
