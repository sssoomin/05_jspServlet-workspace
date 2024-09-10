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
        <h2 class="m-4">회원가입</h2>
        
        <form id="signup-form" action="<%= contextPath %>/insert.me" method="post" class="m-4">
          <table class="table">
            <tr>
              <th>* 아이디</th>
              <td><input type="text" name="userId" class="form-control" placeholder="Enter Your ID" required></td>
              <td><button type="button" class="btn btn-secondary btn-sm" onclick="fnIdCheck();">중복확인</button></td>
            </tr>
            <tr>
              <th>* 비밀번호</th>
              <td><input type="password" name="userPwd" class="form-control" placeholder="Enter Your Password" required></td>
              <td></td>
            </tr>
            <tr>
              <th>* 비밀번호 확인</th>
              <td><input type="password" class="form-control" placeholder="Enter Your Password Check" required></td>
              <td></td>
            </tr>
            <tr>
              <th>* 이름</th>
              <td><input type="text" name="userName" class="form-control" placeholder="Enter Your Name" required></td>
              <td></td>
            </tr>
            <tr>
              <th>&nbsp;&nbsp;전화번호</th>
              <td><input type="text" name="phone" class="form-control" placeholder="Enter Your Phone (- include)"></td>
              <td></td>
            </tr>
            <tr>
              <th>&nbsp;&nbsp;이메일</th>
              <td><input type="text" name="email" class="form-control" placeholder="Enter Your Email (@ include)"></td>
              <td></td>
            </tr>
            <tr>
              <th>&nbsp;&nbsp;주소</th>
              <td><input type="text" name="address" class="form-control" placeholder="Enter Your Address"></td>
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
              <td></td>
            </tr>
          </table>

          <br>

          <div align="center">
            <button type="submit" class="btn btn-primary btn-sm" disabled>회원가입</button>
            <button type="reset" class="btn btn-danger btn-sm">초기화</button>
          </div>

        </form>
        
        <script>
        // 아이디 중복체크용 함수
        function fnIdCheck(){
        	
        	// 사용자가 입력한 아이디값 전달하면서 ajax요청
        	// NNNNN (사용불가) | NNNNY(사용가능) 응답데이터 돌려받기
        	
        	const $idInput = $("#signup-form input[name=userId]");
        	
        	$.ajax({
        		url: '<%=contextPath%>/idcheck.me',
        		data: {checkId: $idInput.val()},
        		success: function(res){
        			console.log(res);
        			if(res == 'NNNNN'){ // 사용불가능
        				alert('이미 존재하거나 탈퇴한 회원의 아이디입니다.');
        				$idInput.select(); // 다시 입력 유도
        			}else{ // 사용가능
        				if(confirm('사용가능한 아이디입니다. 사용하시겠습니까?')){
        					$('#signup-form :submit').removeAttr('disabled'); // 회원가입 버튼 활성화
        					$idInput.prop('readonly', true); //  더이상 아이디 수정 불가하도록
        				}else{
        					$idInput.select(); // 다시 입력 유도
        				}
        			
        			}
        			
        			
        		},
        		error: function() {
        			console.log('아이디 중복체크용 ajax 통신 실패');
        		}
        			
        	})
        }
        
        
        </script>

      </div>
    </section>
    <!-- Section end -->

    <!-- Footer start -->
    <%@ include file="/views/common/footer.jsp" %>
    <!-- Footer end -->

  </div>
	

</body>
</html>