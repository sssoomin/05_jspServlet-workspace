<%@ page import="java.util.*" %>
<%@ page import="com.br.web.board.model.vo.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Map<String, Object> map = (Map<String, Object>)request.getAttribute("map");
	Board b = (Board)map.get("b");
	List<Attachment> list = (List<Attachment>)map.get("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#img-content {
		width: 500px;
		height:300px;
		margin:auto;
	}
	#img-content img{
		width: 500px;
		height: 300px;
	}
</style>
</head>
<body>

	<div class="container p-3">

    <!-- Header, Nav start -->
    <%@ include file="/views/common/header.jsp" %>
    <!-- Header, Nav end -->

    <!-- Section start -->
    <section class="row m-3" style="min-height: 500px;">
      <div class="container border m-4 p-5 rounded">

        <h2 class="m-4">사진게시글 상세조회</h2>

				<!-- 해당 게시글에 딸려있는 첨부파일 목록 필요 -->
        <div id="img-content" class="carousel slide" data-ride="carousel">

				  <!-- Indicators -->
				  <ul class="carousel-indicators">
				  	<% for(int i=0; i<list.size(); i++) { %>
				    <li data-target="#img-content" data-slide-to="<%= i %>" class='<%= i==0 ? "active" : "" %>'></li>
				    <% } %>
				  </ul>
				
				  <!-- The slideshow -->
				  <div class="carousel-inner">
				  
				  	<% for(int i=0; i<list.size(); i++) { %>
				    <div class="carousel-item <%= i==0 ? "active" : "" %>">
				      <img src="<%= contextPath + list.get(i).getFilePath() + list.get(i).getChangeName() %>">
				    </div>
				    <% } %>
				    
				  </div>
				
				  <!-- Left and right controls -->
				  <a class="carousel-control-prev" href="#img-content" data-slide="prev">
				    <span class="carousel-control-prev-icon"></span>
				  </a>
				  <a class="carousel-control-next" href="#img-content" data-slide="next">
				    <span class="carousel-control-next-icon"></span>
				  </a>
				
				</div>
				
				<!-- 해당 게시글의 게시글 정보 -->
				<table class="table m-4">
					<tr>
						<th>제목</th>
						<td><%= b.getBoardTitle() %></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><%= b.getBoardWriter() %></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><p style="min-height:200px; white-space:pre;"><%= b.getBoardContent() %></p></td>
					</tr>
				</table>

      </div>
    </section>
    <!-- Section end -->

    <!-- Footer start -->
    <%@ include file="/views/common/footer.jsp" %>
    <!-- Footer end -->

  </div>

</body>
</html>