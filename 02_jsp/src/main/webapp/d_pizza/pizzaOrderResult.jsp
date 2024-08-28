<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String userName = (String)request.getAttribute("userName");
	String orderNo = (String)request.getAttribute("orderNo");
	String pizza = (String)request.getAttribute("pizza");
	String[] topping = (String[])request.getAttribute("toppings");
	String[] side = (String[])request.getAttribute("sides");
	String payment = (String)request.getAttribute("payment");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>주문 완료</h2>
	
	<h3>주문번호 : <%= orderNo %></h3>
	<h3>주문자명 : <%= userName %></h3>

	<h3>주문내역</h3>
	<ul>
		<li>피자 : <%= pizza %></li>
		<li>토핑 : 
			<% if(topping == null){  %>
				<b>선택안함</b>
			<% } else {  %>
				<%= String.join(",", topping)  %>	
			<% } %>
		</li>
		<li>사이드 : 
			<%
				if(side == null) {  
					out.println("<b>선택안함</b>");
				 } else {
					out.println(String.join(",", side)); 
			} %>
		</li>
		<li>결제수단 : <%= payment.equals("card") ? "카드" : "현금" %></li>
	</ul>
	

</body>
</html>