<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.img-preview {border: 1px solid lightgray}
	.title-img {width: 400px; height:300px;}
	.content-img {width: 180px; height: 100px;}
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

        <h2 class="m-4">사진게시글 작성</h2>
        
        <form action="<%= contextPath %>/insert.th" method="post" enctype="multipart/form-data" class="m-4">
          <table class="table">
            <tr>
              <th>제목</th>
              <td colspan="3"><input type="text" class="form-control" name="title" required></td>
            </tr>
            <tr>
              <th>내용</th>
              <td colspan="3"><textarea rows="10" class="form-control" name="content" style="resize:none;" required></textarea></td>
            </tr>
            <tr>
              <th width="120">대표이미지</th>
              <td> 
              	<img class="img-preview title-img" onclick="chooseFile(0);"> 
              </td>
              <th width="120">상세이미지</th>
              <td>
              	<img class="img-preview content-img" onclick="chooseFile(1);">
              	<img class="img-preview content-img" onclick="chooseFile(2);">
              	<img class="img-preview content-img" onclick="chooseFile(3);">
              </td>
            </tr>
            <tr>
              <td colspan="4" align="center">
                <button type="submit" class="btn btn-outline-secondary btn-sm">등록하기</button>
                <button type="reset" class="btn btn-outline-danger btn-sm">초기화</button>
                <button type="button" class="btn btn-outline-warning btn-sm" onclick="history.back();">뒤로가기</button>
              </td>
            </tr>
          </table>
          <div style="display: none;">
          	<!-- 대표이미지(필수) -->
          	<input type="file" name="upfile1" onchange="loadImg(0);" required>
          	<!-- 상세이미지(선택) -->
          	<input type="file" name="upfile2" onchange="loadImg(1);">
          	<input type="file" name="upfile3" onchange="loadImg(2);">
          	<input type="file" name="upfile4" onchange="loadImg(3);">
          </div>
          
          <script>
          	function chooseFile(idx){
          		$(':file').eq(idx).click();
          	}
                    
          	function loadImg(idx){
          		
          		const inputFile = window.event.target;
          		
          		// idx : img요소들 중 몇 번 인덱스 img요소에 미리보기 효과를 넣을건지
          		// inputFile : 현재 change이벤트(파일선택|파일해제)가 발생된 input type="file" 요소 객체
          		
          		//console.log(inputFile.files); 	 // FileList객체{0:File객체, length:파일갯수}
          		//console.log(inputFile.files[0]); // File객체 (우리가 읽어들여야되는 파일)
          		
          		if(inputFile.files.length == 1){ // 선택된 파일이 있을 경우 => 미리보기
          			
          			const reader = new FileReader(); // 파일을 읽어들이는 FileReader객체
          			reader.readAsDataURL(inputFile.files[0]); // 파일을 읽어들이기 => 해당 이파일의 고유한 url이 만들어짐
          			
          			reader.onload = function(evt){ // 파일 읽어들이기가 완료됐을 경우 실행될 function
          				// evt.target.result : 읽어들인 파일의 고유한 url값
          				$('.img-preview').eq(idx).prop('src', evt.target.result);
          			}
          			
          		}else{ // 선택된 파일이 취소됐을 경우 => 미리보기 제거
          			$('.img-preview').eq(idx).prop('src', null);
          		}
          		
          		
          	}
          </script>
          
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