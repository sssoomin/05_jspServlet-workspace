
<%@ page import="java.util.List, java.util.ArrayList" %>
<%@ page import="java.util.Date" %> <!-- 여러개 기술은 가능하되, 각자 기술하는 거 권장  -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %> <%-- errorPage="/b_page_directive/error500.jsp" --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%--
		* page지시어
		1. 현재 jsp페이지 내에 처리하는데 필요한 각종 속성들을 기술하는 구문
		2. jsp 제일 상단에 기술
		3. 여러개 작성 가능
		4. <%@ page 속성="값" 속성="값" ... %> 
		5. 주요 속성
			1) language			: 사용할 언어 유형 지정
			2) contentType  : 해당 페이지의 형식, 문자셋 지정
			3) pageEncoding : 자바 코드의 인코딩 방식
			4) import  			: 자바에서의 import와 같은 의미
			5) errorPage		: 해당 jsp 로드시 오류 발생할 경우 보여줄 에러페이지 경로
	--%>

	<h2>page 지시어 : 현재 jsp페이지에 대한 각종 속성들을 기술</h2>
	
	<% 
		String name = "홍길동"; // java.lang 패키지에 있는 애들은 기본적으로 import가 되어있음
		
		List<String> list = new ArrayList<>(); // import 해줘야 함
		list.add("Servlet"); // 0번 인덱스
		list.add("JSP"); // 1번 인덱스
		
		Date today = new Date();
	%>
	
	현재 날짜 및 시간 : <%= today %> <br>
	
	이름 : <%= name %> <br>
	
	리스트의 사이즈 : <%= list.size() %> <br>
	0번 인덱스 : <%= list.get(0) %> <br>
	1번 인덱스 : <%= list.get(1) %> <br>
	
	10번 인덱스 : <%= list.get(10) %> <br>
	

</body>
</html>