<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
 <title>Document</title>
 <!-- Bootstrap 사용을 위한 CDN-->
 <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
 <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
 <!-- ========================= -->
</head>
<body>
<body>

    <div class="container p-3">

        <!-- Header, Nav start -->
        <%@ include file="/views/common/header.jsp" %>
        <!-- Header, Nav end -->

        <!-- Section start -->
        <section class="row m-3" style="min-height: 500px">

            <div class="container border p-5 m-4 rounded">
                <h2 class="m-4">해당 페이지의 내용이 보여져야되는 자리</h2>
            </div>

        </section>
        <!-- Section end -->

        <!-- Footer start -->
         <%@ include file="/views/common/footer.jsp" %>
        <!-- Footer end -->

    </div>

</body>
</html>