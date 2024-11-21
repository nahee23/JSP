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
<title>Member Directory</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<!-- 데이터 테이블 CSS -->
<link rel="stylesheet"
	href="https://cdn.datatables.net/2.1.8/css/dataTables.dataTables.min.css" />
</head>
<body>
	<div class="container">
		<h1>회원 리스트</h1>
		<hr />

		<p>${NOTIFICATION}</p>

		<p>
			<button class="btn btn-primary"
				onclick="window.location.href = 'views/employee-form.jsp'">
				회원 가입</button>
				<button class="btn btn-danger"
				onclick="window.location.href = 'logout.jsp'">
				로그아웃</button>
		</p>

		<table id="datatable" class="table table-striped table-bordered">
			<thead>
				<tr class="thead-dark">
					<th>Name</th>
					<th>Email</th>
					<th>Date of birth</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list}" var="member">
					<tr>
						<td>${member.name}</td>
						<td>${member.email}</td>
						<td>${member.birth}</td>
						<td><a
							href="${pageContext.request.contextPath}/MemberController?action=EDIT&id=${member.id}">Edit</a>
							| <a
							href="${pageContext.request.contextPath}/MemberController?action=DELETE&id=${member.id}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
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
