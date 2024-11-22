<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%
String id = (String) session.getAttribute("id");

if (id == null) {
	response.sendRedirect(request.getContextPath());
}
%>

<!DOCTYPE html>
<html>
<head>
<title>자유게시판</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<!-- 데이터 테이블 CSS -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css" />
</head>
<body>
<jsp:include page="/Common/Link.jsp" />  <!-- 공통 링크 -->
<br>
	<div class="container">
		<h1>자유 게시판</h1>
		<hr />

		<p>${NOTIFICATION}</p>

		<p>
			<button class="btn btn-primary"
						onclick="location.href='../mvcboard/write.do';">글쓰기</button>
			<button class="btn btn-danger"
				onclick="window.location.href = '../logout.jsp'">로그아웃</button>
		</p>

		<table id="datatable" class="table table-striped table-bordered">
			<thead>
				<tr class="thead-dark">
					<th width="10%">번호</th>
					<th width="*">제목</th>
					<th width="15%">작성자</th>
					<th width="10%">조회수</th>
					<th width="15%">작성일</th>
					<th width="8%">첨부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ boardLists }" var="row" varStatus="loop">
					<tr align="center">
						<td>
							<!-- 번호 --> ${ map.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index)}
						</td>
						<td align="left">
							<!-- 제목(링크) --> <a href="../mvcboard/view.do?idx=${ row.idx }">${ row.title }</a>
						</td>
						<td>${ row.name }</td>
						<!-- 작성자 -->
						<td>${ row.visitcount }</td>
						<!-- 조회수 -->
						<td>${ row.postdate }</td>
						<!-- 작성일 -->
						<td>
							<!-- 첨부 파일 --> <c:if test="${ not empty row.ofile }">
								<a
									href="../mvcboard/download.do?ofile=${ row.ofile }&sfile=${ row.sfile }&idx=${ row.idx }">[Down]</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				
			</tbody>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<!-- 데이터 테이블 JS -->
	<script src="https://cdn.datatables.net/2.1.8/js/dataTables.min.js"></script>
	<script>
		$(document).ready(function() {
			$("#datatable").DataTable({
				language : {
					lengthMenu : "표시할 줄수 선택    _MENU_",
					search : "검색",
					paginate : {
						previous : "이전",
						next : "다음"
					},
					info : "페이지 _PAGE_ / _PAGES_",
					infoEmpty : "데이터가 없습니다.",
					infoFiltered : "(전체 페이지 _MAX_ 에서 검색)",
					thousands : ",",
				},
				lengthMenu : [ 5, 10, 25 ],
				pageLength : 5,
				ordering : false,
				stateSave : true,
			});
		});
	</script>
</body>
</html>
