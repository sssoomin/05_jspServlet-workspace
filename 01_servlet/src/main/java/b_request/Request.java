package b_request;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Request
 */
@WebServlet("/request")
public class Request extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Request() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * * HttpServletRequest
	 * 1. 클라이언트의 요청관련 정보가 담겨있는 객체
	 * 2. doGet() 또는 doPost() 메소드가 실행시 해당객체가 생성돼서 매개변수(request)에 전달됨
	 * 3. 요청 관련 주요 메소드
	 * 	1) getContextPath() : 애플리케이션의 contextPath 반환
	 *  2) getRequestURI() : 요청 URL의 contextPath+URLMapping 반환
	 *  3) getRequestURL() : 요청 URL의 전체 경로 반환
	 *  4) getParameterMap() : 요청에 포함된 parameter들을 Map<String, String[]>으로 반환
	 *  5) getParameter(String) : 요청에 포함된 parameter 특정 데이터를 문자열로 반환
	 *  6) getParameterValues(String) : 요청에 포함된 parameter 다수 데이터를 문자열 배열로 반환
	 *  7) setCharacterEncoding(String) : 요청에 포함된 parameter들의 인코딩 설정
	 *  
	 * 4. parameter(파라미터)
	 *  1) 요청시 전달값 (사용자가 입력한 값, 직접 전달되는 값)
	 *   http://host.port/contextPath/URLMapping?param=values&param=value
	 *  2) 모든 파라미터는 String 타입
	 *  3) 파라미터가 존재하지 않을 경우 null 반환
	 *  4) 파라미터값이 존재하지 않을 경우 "" 반환
	 *     
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doGet() 메소드 실행");
		
		System.out.println("contextPath: " + request.getContextPath());
		System.out.println("URI: " + request.getRequestURI());
		System.out.println("url: " + request.getRequestURL());
		
		// name=홍갈동&age=20&hobby=sports&hobby=reading
		// name=&age=
		String name = request.getParameter("name"); // "홍길동" | ""
		System.out.println("param(name): " + name);
		
		int age = Integer.parseInt(request.getParameter("age")); // "20" => 20 | ""=>NumberForamtException
		System.out.println("param(age): " + age);
		
		String[] hobbyList = request.getParameterValues("hobby"); // ["sports", "reading"] | null 
		System.out.println("param(hobby): " + Arrays.toString(hobbyList));
		
		System.out.println("===================================");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
