package a_lifecycle;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// http://loaclhost:8888/servlet/life URL 요청시 해당 Servlet 실행
@WebServlet("/life") // 어노테이션
/*
 *  * URL Mapping 등록방법
 *  1. Servlet 클래스 상단에 @WebServlet Annotation 활용
 *    @WebServlet("URLMapping")
 *  2. WEB-INF/web.xml 파일에 Servlet 클래스 등록시 매핑
 *    <servlet>
 *    		<servlet-name></servlet-name>
 *    		<servlet-class>a_lifecycle.LifeCycle</servlet-class>
 *    </servlet>
 *   <servlet-mapping>
 *      	<servlet-name>LifeCycle</servlet-name>
 *      	<url-pattern>/life</url-pattern>
 *      </servlet-mapping>

 */
public class LifeCycle extends HttpServlet {
	
	/*
	 *  * Servlet
	 *  1. 사용자의 요청을 받아 처리한 후 응답해주는 Java 클래스
	 *  2. javax.servlet.http.HttpServlet 클래스를 상속 받아 구현
	 *  	(javax 패키지는 Tomcat이 제공하므로 Tomcat이 없으면 구현할 수 없음)
	 *  3. 동일한 애플리케이션 내에 동일한 URL Mapping값의 Servlet이 존재해서는 안됨
	 *  4. 사용자의 요청시 Servlet컨테이너가 작동됨녀서 해당 Servlet클래스가 실행되
	 *  	이때 미리 약속된 메소드가 호출되도록 되어있음 (LifeCycle)
	 *  
	 *  * Servlet LifeCycle
	 *  1) 최초 요청일 경우 Servlet 객체 생성(생성자) => init() 메소드 호출
	 *  2_1) service() 메소드 호출
	 *  2_2) doGet() 또는 doPost()호출 (service()메소드가 정의되어있지 않을 경우)
	 *  3) 더이상 Servlet이 서비스되지 않을 경우 destroy() 호출
	 *  	(주로 서버종료 / 재컴파일시)
	 */
	private static final long serialVersionUID = 1L;
       
    /**
     * * 생성자
     * 1. Servlet 생성시 호출됨
     * 2. 생성자 호출 이후에 자동으로 init() 메소드 호출됨
     */
    public LifeCycle() { 
        super();
        System.out.println("LifeCycle Servlet 생성자 실행");
    }

	/**
	 * * init()
	 * 1. Servlet 환결설정을 담당하는 메소드
	 * 2. init() 이후에 자동으로 service() 메소드 호출됨
	 */
	public void init(ServletConfig config) throws ServletException { 
		System.out.println("init()메소드 실행");
	}
	

	/**
	 * * service()
	 * 1. 클라이언트로부터 요청을 받을 수 있음 (HttpServletRequest 매개변수)
	 * 	  클라이언트로 응답할 수 있음 (HttpServletResponse 매개변수)
	 * 2. service() 메소드가 있을 경우 여기서 요청과 응답을 처리함
	 *    service() 메소드가 없을 경우 doGet() 또는 doPost() 메소드가 요청방식에 따라 호출됨
	 *    
	 */
	/*
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() 메소드 실행");
		
		// request.getMethod() : 요청된 요청방식 반환 ("GET" | "POST")
		switch(request.getMethod()) {
		case "GET" : System.out.println("Get방식으로 요청됨"); break;
		case "POST" : System.out.println("Post방식으로 요청됨"); break;
		}
		
		System.out.println("============================");
		
	}
	*/

	/**
	 * * doGet()
	 * 1. service() 메소드가 없을 경우 GET방식 요청시 자동으로 호출됨
	 * 2. GET 방식 요청 케이스
	 * 	ㄴ <a href="/servlet/life">
	 * 	ㄴ <form action="/servlet/life" method = "get">
	 *  ㄴ location.href = "/servlet/life";
	 *  ㄴ window.open("/servlet/life");
	 *  ㄴ $.ajax({
	 *  		url: "/servlet/life",
	 *  		type: "get",
	 *  		...
	 *  	});
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet() 메소드 실행 (Get방식으로 요청됨)");
	}

	/**
	 *  * doPost()
	 *  1. service() 메소드가 없을 경우 POST 방식 요청시 자동으로 호출됨
	 *  2. POST 방식 요청 케이스
	 *     ㄴ <form action="/servlet/life" method="post">
	 *     ㄴ $.ajax({
	 *     		 url: "/servlet/life",
	 *     		 type: "post",
	 *           ...
	 *           });
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost() 메소드 실행 (Post방식으로 요청됨)");
	}
	
	/**
	 * * destroy()
	 * 1. 더이상 해당 Servlet이 서비스되지 않을 경우 실행됨
	 * 	ex) 서버종료 / 소스코드 재컴파일
	 * 
	 */
	public void destroy() {
		System.out.println("destroy() 메소드 실행");				
	}

}
