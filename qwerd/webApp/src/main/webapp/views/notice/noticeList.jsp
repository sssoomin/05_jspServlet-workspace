<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.List" %>
 <%@ page import="com.br.web.notice.model.vo.Notice" %>
    <%
    	List<Notice> list = (List<Notice>)request.getAttribute("list");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


 <div class="container p-3">

   <!-- Header, Nav start -->
    <%@ include file="/views/common/header.jsp" %>
   <!-- Header, Nav end -->

   <!-- Section start -->
   <section class="row m-3" style="min-height: 500px">

    <div class="container border p-5 m-4 rounded">

      <h2 class="m-4">공지사항 목록</h2>

   <% if(loginUser != null && loginUser.getStatus().equals("A")) { %>
      <div align="right">
        <a href="<%= contextPath %>/write.no" class="btn btn-secondary btn-sm">등록하기</a>
      </div>
	<% } %>
      <br>
      <table id="notice-list" class="table">
        <thead>
          <tr>
            <th width="100px">번호</th>
            <th width="600px">글제목</th>
            <th width="120px">작성자</th>
            <th>작성일</th>
          </tr>
        </thead>
        <tbody>
        <% if(list.isEmpty()) { %>
          <!--   case1. 조회된 공지글이 없을 경우 -->
          <tr>
            <td colspan="4" style="text-align:center">존재하는 공지사항글이 없습니다.</td>
          </tr>
         <% }else{ %>
				<% for(Notice n : list) {%>
          <!-- case2. 조회된 공지글이 있을 경우 -->
          <tr class="board-title" data-toggle="collapse" data-target="#notice<%=n.getNoticeNo()%>">
            <td><%= n.getNoticeNo() %></td>
            <td><%= n.getNoticeTitle() %></td>
            <td><%= n.getNoticeWriter() %></td>
            <td><%= n.getRegistDt() %></td>
          </tr>
          <tr class="board-content collapse" id="notice<%=n.getNoticeNo()%>">
            <td colspan="4">
              <p class="border rounded p-3 w-75 mx-auto" style="min-height: 150px; white-space:pre;x"><%= n.getNoticeContent() %></p>
              
             <% if(loginUser != null && loginUser.getUserId().equals(n.getNoticeWriter())) { %>
              <div align="center">
                <a href="<%= contextPath %>/modify.no?no=<%= n.getNoticeNo() %>" class="btn btn-secondary btn-sm">수정하기</a>
                <a href="<%= contextPath %>/delete.no?no=<%= n.getNoticeNo() %>" class="btn btn-danger btn-sm">삭제하기</a>
              </div>
              <% } %>
            </td>
          </tr>
					<% } %>
	<% } %>

        </tbody>
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