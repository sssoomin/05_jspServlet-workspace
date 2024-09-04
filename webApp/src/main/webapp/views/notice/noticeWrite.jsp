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
    <section class="row m-3" style="min-height: 500px">

      <div class="container border p-5 m-4 rounded">
        <h2 class="m-4">공지사항 등록</h2>
        
        <form action="<%= contextPath %>/insert.no" method="post" class="m-4">
          <table class="table">
            <tr>
              <th>제목</th>
              <td><input type="text" class="form-control" name="title" required></td>
            </tr>
            <tr>
              <th>내용</th>
              <td><textarea rows="10" class="form-control" name="content" style="resize:none" required></textarea></td>
            </tr>
          </table>

          <br>

          <div align="center">
            <button type="submit" class="btn btn-primary btn-sm">등록하기</button>
            <button type="reset" class="btn btn-danger btn-sm">초기화</button>
          </div>

        </form>

      
      </div>

    </section>
    <!-- Section end -->

    <!-- Footer start -->
    <%@ include file="/views/common/footer.jsp" %>
    <!-- Footer end -->

  </div>

</body>
</html>