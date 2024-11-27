<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
	
	<style>
	
    .carousel-item img {
        width: 100%;
        height: 500px; /* 모든 이미지의 높이를 400px로 고정 */
        object-fit: cover; /* 이미지를 잘라내 비율 유지 */

    }

    @media (max-width: 768px) {
        .carousel-item img {
            height: 300px; /* 모바일 환경에서 높이를 줄임 */
        }
    }
</style>
</head>
<body class="bg-dark">

<!-- 서버에서 전달된 알림 메시지가 있다면 표시 -->
    <%
        String NOTIFICATION = (String) request.getAttribute("NOTIFICATION");
        if (NOTIFICATION != null) {
    %>
        <script>
            alert("<%= NOTIFICATION %>");
        </script>
    <%
        }
    %>
	<jsp:include page="/Common/Link.jsp" />
	<!-- 공통 링크 -->

	<br>
	<div class="container">
		<h1 class="text-white">Musical</h1>

		<p class="text-white">뮤지컬에 대한 정보 조회 및 자유로운 대화, 티켓 양도가 가능합니다.</p>

		<p>
			<button class="btn btn-secondary" onclick="location.href='${pageContext.request.contextPath}/views/ongoing.jsp'">바로가기</button>
		</p>
		<br>

		<div id="carouselExampleSlidesOnly" class="carousel slide carousel-fade"
			data-bs-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="images/4.jpg" class="d-block" alt="..." >
				</div>
				<div class="carousel-item">
					<img src="images/3.jpg" class="d-block" alt="..." >
				</div>
				<div class="carousel-item">
					<img src="images/2.jpg" class="d-block " alt="...">
				</div>
				<div class="carousel-item">
					<img src="images/1.jpg" class="d-block " alt="...">
				</div>
				<div class="carousel-item">
					<img src="images/5.jpg" class="d-block " alt="...">
				</div>
				<div class="carousel-item">
					<img src="images/6.jpg" class="d-block" alt="...">
				</div>
				<div class="carousel-item">
					<img src="images/7.jpg" class="d-block " alt="...">
				</div>
				<div class="carousel-item">
					<img src="images/8.jpg" class="d-block " alt="...">
				</div>
				<div class="carousel-item">
					<img src="images/9.jpg" class="d-block " alt="...">
				</div>
			</div>
		</div>
	</div>
	<br><br>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>