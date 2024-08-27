package d_forward;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * http://localhost:8888/servlet/first 요청시 실행됨
 */
@WebServlet("/first")
public class FirstStep extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstStep() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("첫번째 목적지(경유지) 도착");
		
		// * 포워드 하는 구문
		//   request.getRequestDispatcher("이동할경로").forward(request, response);
		request.getRequestDispatcher("/second").forward(request, response);
		
		/*
		 * * 서블릿이 실행되는 경우
		 * 1. 클라이언트 요청에 의해서 (a, form submit 등)
		 * 2. 서블릿에서 또다른 서블릿을 "이동"할 경우 (forward)
		 * 3. 서블릿에서 또다른 서블릿을 "재호출"할 경우 (redirect)
		 * 
		 * * forward
		 * 1. 특정 처리를 대신할 Servlet 및 JSP로 이동시 사용하는 기술
		 * 	  주로 응답페이지 제작하는 과정을 JSP에게 위임할때 사용할 예정
		 * 2. 포워딩시 현재 서블릿에서 생성된 request, response를 전달해야됨
		 * 	  => 이된 곳에서 해당 request, response 그대로 사용 가능
		 * 3. 이동 경로 작성시 contextPath 제외하고 작성
		 * 4. URL에는 forward된(이동된) 경로가 노출되지 않음 ****
		 *    즉, 클라이언트에서는 이동된 경로를 확인할 수 없음	  
		 *        클라이언트에게는 최초 요청했던 URL만 보여짐
		 * 
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
