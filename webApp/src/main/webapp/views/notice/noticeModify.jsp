<%@ page import="com.br.web.notice.model.vo.Notice" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Notice n = (Notice)request.getAttribute("n"); // 글번호, 글제목, 글내용 
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>



   <!-- Header, Nav start -->
    <%@ include file="/views/common/header.jsp" %>
     <!-- Header, Nav end -->
     
   <div class="container p-3">
  

   <!-- Section start -->
   <section class="row m-3" style="min-height: 500px">

     <div class="container border p-5 m-4 rounded">
       <h2 class="m-4">공지사항 수정</h2>
       
       <form action="<%=contextPath %>/update.no" method="post" class="m-4">
         <table class="table">
           <tr>
             <th>제목</th>
             <td><input type="text" class="form-control" required value="<%= n.getNoticeTitle() %>"></td>
           </tr>
           <tr>
             <th>내용</th>
             <td><textarea rows="10" class="form-control" style="resize:none" required><%= n.getNoticeContent() %>기존의 공지사항 내용</textarea></td>
           </tr>
         </table>

         <br>

         <div align="center">
           <button type="submit" class="btn btn-primary btn-sm">수정하기</button>
           <button type="reset" class="btn btn-danger btn-sm">초기화</button>
         </div>

       </form>

     
     </div>

   </section>
   <!-- Section end -->

   <!-- Footer start -->
     <!-- footer.jsp include 할 예정 -->
   <!-- Footer end -->

 </div>
</body>
</html>