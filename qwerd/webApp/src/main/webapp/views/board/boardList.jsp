<%@ page import="java.util.List" %>
<%@ page import="com.br.web.common.model.vo.PageInfo" %>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <section class="row m-3" style="min-height: 500px;">
      <div class="container border m-4 p-5 rounded">

        <h2 class="m-4">일반게시글 목록</h2>

        <!-- 현재 로그인되어있는 회원일 경우 보여지는 요소 -->
        <div class="d-flex justify-content-end">
          <button type="button" class="btn btn-secondary btn-sm">등록하기</button>
        </div>

        <br>
        <table class="table">
          <thead>
            <tr>
              <th width="100px">번호</th>
              <th width="100px">카테고리</th>
              <th width="400px">글제목</th>
              <th width="120px">작성자</th>
              <th>조회수</th>
              <th>작성일</th>
            </tr>
          </thead>
          <tbody>
          	<!-- 사용자가 요청한 페이지에 뿌려줄 게시글 데이터 조회해와야됨 -->
          	
          	<% if  %>
            <!-- case1. 조회된 게시글이 없을 경우 -->
            <!--
            <tr>
              <td colspan="6" style="text-align: center;">존재하는 게시글이 없습니다.</td>
            </tr>
            -->

            <!-- case2. 조회된 게시글이 있을 경우 -->
            <tr>
              <td>3</td>
              <td>게임</td>
              <td>글제목입니다</td>
              <td>user01</td>
              <td>200</td>
              <td>2024-01-12</td>
            </tr>
            <tr>
                <td>3</td>
                <td>게임</td>
                <td>글제목입니다</td>
                <td>user02</td>
                <td>200</td>
                <td>2024-01-12</td>
            </tr>
            <tr>
                <td>3</td>
                <td>게임</td>
                <td>글제목입니다</td>
                <td>user02</td>
                <td>200</td>
                <td>2024-01-12</td>
            </tr>
            <tr>
                <td>3</td>
                <td>게임</td>
                <td>글제목입니다</td>
                <td>user01</td>
                <td>200</td>
                <td>2024-01-12</td>
            </tr>
            <tr>
                <td>3</td>
                <td>게임</td>
                <td>글제목입니다</td>
                <td>user03</td>
                <td>200</td>
                <td>2024-01-12</td>
            </tr>
          </tbody>
        </table>
				<!-- 사용자가 현재보고있는 페이지가 뭐냐에 따라서 다르게 보여질 페이징바 -->
        <ul class="pagination d-flex justify-content-center text-dark">
          <li class="page-item disabled"><a class="page-link" href="">Previous</a></li>
          <li class="page-item active"><a class="page-link" href="">1</a></li>
          <li class="page-item"><a class="page-link" href="">2</a></li>
          <li class="page-item"><a class="page-link" href="">3</a></li>
          <li class="page-item"><a class="page-link" href="">4</a></li>
          <li class="page-item"><a class="page-link" href="">5</a></li>
          <li class="page-item"><a class="page-link" href="">Next</a></li>
        </ul>
      </div>
    </section>
    <!-- Section end -->

    <!-- Footer start -->
   <%@ include file="/views/common/footer.jsp" %>
    <!-- Footer end -->

  </div>


    

</body>
</html>