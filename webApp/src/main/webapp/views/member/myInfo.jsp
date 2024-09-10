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
        <h2 class="m-4">마이페이지</h2>
        
        <form action="<%= contextPath%>/update.me" method="post" class="m-4">
          <table class="table">
            <tr>
              <th>* 아이디</th>
              <td><input type="text" class="form-control" placeholder="Enter Your ID" name="userId" value="<%= loginUser.getUserId() %>" readonly></td>
              <td></td>
            </tr>
            <tr>
              <th>* 이름</th>
              <td><input type="text" class="form-control" placeholder="Enter Your Name" name="userName" value="<%= loginUser.getUserName() %>" required></td>
              <td></td>
            </tr>
            <tr>
              <th>&nbsp;&nbsp;전화번호</th>
              <td><input type="text" class="form-control" placeholder="Enter Your Phone (- include)" name="phone" value='<%= loginUser.getPhone() == null ? "" : loginUser.getPhone() %>'></td>
              <td></td>
            </tr>
            <tr>
              <th>&nbsp;&nbsp;이메일</th>
              <td><input type="text" class="form-control" placeholder="Enter Your Email (@ include)" name="email" value='<%= loginUser.getEmail() == null ? "" : loginUser.getEmail() %>'></td>
              <td></td>
            </tr>
            <tr>
              <th>&nbsp;&nbsp;주소</th>
              <td><input type="text" class="form-control" placeholder="Enter Your Address" name="address" value='<%= loginUser.getAddress() == null ? "" : loginUser.getAddress() %>'></td>
              <td></td>
            </tr>
            <tr>
              <th>&nbsp;&nbsp;관심분야</th>
              <td>
                <input type="checkbox" name="interest" value="운동" id="sports">
                <label for="sports">운동</label>
                <input type="checkbox" name="interest" value="등산" id="climibing">
                <label for="climibing">등산</label>
                <input type="checkbox" name="interest" value="낚시" id="fishing">
                <label for="fishing">낚시</label>
                <input type="checkbox" name="interest" value="요리" id="cooking">
                <label for="cooking">요리</label>
                <input type="checkbox" name="interest" value="게임" id="game">
                <label for="game">게임</label>
                <input type="checkbox" name="interest" value="영화" id="movie">
                <label for="movie">영화</label>
              </td>
              <script>
              	$(function() {
              		let interest = '<%=loginUser.getInterest() == null ? "" : loginUser.getInterest()%>';
              		// "" | "운동,등산"
              		
              		$(":checkbox").each(function(idx, el) { // el : 순차적으로 접근되는 체크박스 요소
              																						// el.value : 접근되는 체크박스의 value값
              			if(interest.indexOf(el.value) != -1){
              				$(el).prop("checked", true);
              			}																			
              		})			
              		
              	})
              </script>
              <td></td>
            </tr>
          </table>
          <br>
          <div align="center">
            <button type="submit" class="btn btn-outline-primary btn-sm">정보변경</button>
            <button type="button" class="btn btn-outline-warning btn-sm" data-toggle="modal" data-target="#changePwdModal">비밀번호변경</button>
            <button type="button" class="btn btn-outline-danger btn-sm" data-toggle="modal" data-target="#resignModal">회원탈퇴</button>
          </div>
        </form>
      </div>

    </section>
    <!-- Section end -->

    <!-- Footer start -->
    <%@ include file="/views/common/footer.jsp" %>
    <!-- Footer end -->

  </div>

  <!-- 비밀번호 변경용 Modal -->
  <div class="modal" id="changePwdModal">
    <div class="modal-dialog">
      <div class="modal-content">
  
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">비밀번호 변경</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
  
        <!-- Modal body -->
        <div class="modal-body">
          <form action="<%= contextPath %>/updatePwd.me" method="post">
          	<input type="hidden" name="userId" value="<%= loginUser.getUserId() %>">
            <table align="center">
              <tr>
                <th>* 현재 비밀번호</th>
                <td><input type="password" class="form-control" name="userPwd" required></td>
              </tr>
              <tr>
                <th>* 변경할 비밀번호</th>
                <td><input type="password" class="form-control" name="updatePwd" required></td>
              </tr>
              <tr>
                <th>* 변경할 비밀번호 재입력</th>
                <td><input type="password" class="form-control" required></td>
              </tr>
              <tr>
                <td colspan="2" style="text-align:center; padding-top: 10px;">
                  <button type="submit" class="btn btn-warning btn-sm">비밀번호 변경</button>
                </td>
              </tr>
            </table>
          </form>
        </div>
  
      </div>
    </div>
  </div>

  <!-- 회원탈퇴용 Modal -->
  <div class="modal" id="resignModal">
    <div class="modal-dialog">
      <div class="modal-content">
  
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">회원탈퇴</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
  
        <!-- Modal body -->
        <div class="modal-body">
          <form action="<%= contextPath %>/delete.me" method="post">
            <table align="center">
              <tr>
                <th colspan="2">
                  탈퇴 후 복구가 불가능합니다. <br>
                  정말로 탈퇴하시겠습니까?
                </th>
              </tr>
              <tr>
                <th>현재 비밀번호</th>
                <td><input type="password" class="form-control" name="userPwd" required></td>
              </tr>
              <tr>
                <td colspan="2" style="text-align:center; padding-top: 10px;">
                  <button type="submit" class="btn btn-danger btn-sm">회원탈퇴</button>
                </td>
              </tr>
            </table>
          </form>
        </div>
  
      </div>
    </div>
  </div>

</body>
</html>