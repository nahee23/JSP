<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%     
if (request.getAttribute("ongoingMusicals") == null) {
    String contextPath = request.getContextPath();
    response.sendRedirect(contextPath + "/ongoing");
    return; // 리다이렉트 후 추가 처리 방지
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>현재 공연</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<style>
.nav-link {
text-decoration : none;
color : white;
}
.card {
	width: 15rem; /* 카드 너비 */
	height: 400px; /* 카드 높이 */
}

.card-img-top {
	width: 100%;
	height: 280px; /* 원하는 높이로 설정 */
	object-fit: contain; /* 비율 유지하면서 잘림 */
}

.card-title {
	font-size: 1.0rem; /* 제목 크기 작게 */
}

.card-text {
	font-size: 0.7rem; /* 본문 텍스트 크기 작게 */
}
</style>
</head>
<body class="bg-dark">
	<jsp:include page="/Common/Link.jsp" />
	<!-- 공통 링크 -->
	<br>
	<div class="container">
		<h1 class="text-white">Musical_Information</h1>
		<br><br>
		
		<!-- 탭 메뉴 -->
        <ul class="nav nav-tabs" id="tabMenu" role="tablist">
            <li class="nav-item" role="presentation">
                <a class="nav-link active" id="ongoing-tab" href="views/ongoing.jsp">현재 공연</a>
            </li>
            <li class="nav-item" role="presentation">
                <a class="nav-link" id="upcoming-tab" href="views/upcoming.jsp">예정 공연</a>
            </li>
        </ul>
        <br>
        
		<div class="row row-cols-1 row-cols-md-3 g-4">
			<c:forEach var="musical" items="${ongoingMusicals}">
				<div class="col flex-grow-0">
					<div class="card">
						<img src="${musical.imageUrl}" class="card-img-top"
							alt="${musical.title}">
						<div class="card-body">
							<h5 class="card-title">${musical.title}</h5>
							<p class="card-text">
								날짜: ${musical.date} <br> 장소: ${musical.place}
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>