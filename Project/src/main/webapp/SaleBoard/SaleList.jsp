<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양도게시판</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<style>
a {
	text-decoration: none;
}
.search-form {
  width: 80%;
  margin: 0 auto;
  margin-top: 1rem;
}

.search-form input {
  height: 100%;
  background: transparent;
  border: 0;
  display: block;
  width: 100%;
  padding: 1rem;
  height: 100%;
  font-size: 1rem;
}

.search-form select {
  background: transparent;
  border: 0;
  padding: 1rem;
  height: 100%;
  font-size: 1rem;
}

.search-form select:focus {
  border: 0;
}

.search-form button {
  height: 100%;
  width: 100%;
  font-size: 1rem;
}

.search-form button svg {
  width: 24px;
  height: 24px;
}

.card-margin {
  margin-bottom: 1.875rem;
}

@media (min-width: 992px) {
  .col-lg-2 {
    flex: 0 0 16.66667%;
    max-width: 16.66667%;
  }
}

.card {
  border: 0;
  box-shadow: 0px 0px 10px 0px rgba(82, 63, 105, 0.1);
  -webkit-box-shadow: 0px 0px 10px 0px rgba(82, 63, 105, 0.1);
  -moz-box-shadow: 0px 0px 10px 0px rgba(82, 63, 105, 0.1);
  -ms-box-shadow: 0px 0px 10px 0px rgba(82, 63, 105, 0.1);
}

.card {
  position: relative;
  display: flex;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  background-color: #ffffff;
  background-clip: border-box;
  border: 1px solid #e6e4e9;
  border-radius: 8px;
}
</style>
</head>
<body class="bg-dark">
	<jsp:include page="../Common/Link.jsp" />
	<!-- 공통 링크 -->
	<br>
	<div class="container">
		<h1 class="text-white">Sale</h1>
		<br>

		<!-- 목록 테이블 -->
		<div class="row bg-white">
			<table class="table" id="datatable">
				<thead>
					<tr>
						<th width="10%">번호</th>
						<th width="*">제목</th>
						<th width="15%">가격</th>
						<th width="10%">조회수</th>
						<th width="15%">작성일</th>
						<th width="8%">첨부</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${ empty Salelist }">
							<!-- 게시물이 없을 때 -->
							<tr>
								<td colspan="6" align="center">등록된 게시물이 없습니다^^*</td>
							</tr>
						</c:when>
						<c:otherwise>
							<!-- 게시물이 있을 때 -->
							<c:forEach items="${ Salelist }" var="sale" varStatus="status">
								<tr align="center">
									<td>
									${fn:length(Salelist) - status.index} <!-- fn:length로 리스트 크기 계산 -->
										<!-- ${ map.totalCount - (((map.pageNum-1) * map.pageSize) + loop.index)} -->
									</td>
									<td align="left">
										<!-- 제목(링크) --> <a href="${pageContext.request.contextPath}/SaleController?action=VIEW&idx=${ sale.idx }">${ sale.sale_title }</a>
									</td>
									<td>${ sale.name }</td>
									<!-- 작성자 -->
									<td>${ sale.visitcount }</td>
									<!-- 조회수 -->
									<td>${ sale.postdate }</td>
									<!-- 작성일 -->
									<td>
										<!-- 첨부 파일 --> <c:if test="${ not empty sale.ofile }">
											<a
												href="../mvcboard/download.do?ofile=${ sale.ofile }&sfile=${ sale.sfile }&idx=${ sale.idx }">[Down]</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>

		<!-- 하단 메뉴(바로가기, 글쓰기) -->
		<div class="row bg-white">
			<div class="d-grid gap-2 d-md-flex justify-content-md-end mb-2">

				<button type="button" class="btn btn-primary me-md-2"
					onclick="location.href='SaleBoard/SaleWrite.jsp';">글쓰기</button>
			</div>
		</div>
		<table>
			<tr>
				<td>${ map.pagingImg }</td>
			</tr>
		</table>
	</div>
</body>
<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
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
</html>