<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- Bootstrap 사용을 위한 CDN -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<!-- ------------------------- -->
    
<style>
  header{height: 150px}
  header a{color:black;}
</style>

<header class="row m-3">
  <div class="col-3 d-flex justify-content-center align-items-center">
    <a href=""><img src="/assets/image/goodee_logo.png"></a>
  </div>
  <div class="col-6"></div>
  <div class="col-3 d-flex justify-content-center align-items-center">

      
    <!-- case1. 로그인전 -->
    <form action="" method="">
      <table>
        <tr>
          <th>ID</th>
          <td><input type="text" name="userId" class="form-control form-control-sm" placeholder="Enter Your ID" required></td>
        </tr>
        <tr>
          <th>PWD</th>
          <td><input type="password" name="userPwd" class="form-control form-control-sm" placeholder="Enter Your PWD" required></td>
        </tr>
        <tr>
          <td colspan="2" align="center">
            <button type="submit" class="btn btn-secondary btn-sm">로그인</button>
            <button type="button" class="btn btn-secondary btn-sm" id="signup-btn">회원가입</button>
            
          </td>
        </tr>
      </table>
    </form>
    <!-- case2. 로그인후 -->
    <!-- 
    <div>
      <b></b>님 환영합니다. <br><br>

      <a href="">마이페이지</a>
      <a href="">로그아웃</a>
    </div>
    -->

  </div>
</header>
<nav class="navbar m-3 navbar-expand-sm bg-dark navbar-dark d-flex justify-content-center">
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="">Home</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="">공지사항</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="">일반게시판</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="#">사진게시판</a>
    </li>
  </ul>
</nav>