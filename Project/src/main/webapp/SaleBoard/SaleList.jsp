<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
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

		<div class="row">
			<div class="card card-margin search-form">
				<div class="card-body p-0">
					<!-- 검색 폼 -->
					<form id="search-form" method="get">
						<div class="row">
							<div class="col-12">
								<div class="row no-gutters">
									<div class="col-lg-3 col-md-3 col-sm-12 p-0">
										<label for="search-type" hidden>검색 유형</label> <select
											class="form-control" id="search-type" name="searchField">
											<option value="title">제목</option>
											<option value="content">내용</option>
										</select>
									</div>
									<div class="col-lg-8 col-md-6 col-sm-12 p-0">
										<label for="search-value" hidden>검색어</label> <input
											type="text" placeholder="검색어..." class="form-control"
											id="search-value" name="searchWord" />
									</div>
									<div class="col-lg-1 col-md-3 col-sm-12 p-0">
										<button type="submit" class="btn btn-base">
											<svg xmlns="http://www.w3.org/2000/svg" width="24"
												height="24" viewBox="0 0 24 24" fill="none"
												stroke="currentColor" stroke-width="2"
												stroke-linecap="round" stroke-linejoin="round"
												class="feather feather-search">
                                            <circle cx="11" cy="11"
													r="8"></circle>
                                            <line x1="21" y1="21"
													x2="16.65" y2="16.65"></line>
                                        </svg>
										</button>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>

		<!-- 목록 테이블 -->
		<div class="row bg-white">
			<table class="table" id="article-table">
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
						<c:when test="${ empty boardLists }">
							<!-- 게시물이 없을 때 -->
							<tr>
								<td colspan="6" align="center">등록된 게시물이 없습니다^^*</td>
							</tr>
						</c:when>
						<c:otherwise>
							<!-- 게시물이 있을 때 -->
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
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>

		<!-- 하단 메뉴(바로가기, 글쓰기) -->
		<div class="row bg-white">
			<div class="d-grid gap-2 d-md-flex justify-content-md-end">

				<button type="button" class="btn btn-primary me-md-2"
					onclick="location.href='../mvcboard/write.do';">글쓰기</button>
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
</html>