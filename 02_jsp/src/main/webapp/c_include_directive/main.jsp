<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h2>include 지시어: 다른페이지를 포함시킬때 사용</h2>
	
	<div class="wrap" style="border: 1px solid black; padding: 20px">
		
		<%@ include file="header.jsp" %>
		
		<section>여기는 content부</section>
		
		<%@ include file="footer.jsp" %>
		
		
	
	</div>

</body>
</html>