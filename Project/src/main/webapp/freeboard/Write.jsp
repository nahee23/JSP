<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판-작성하기</title>
<script type="text/javascript">
	function validateForm(form) {
		if (form.name.value == "") {
			alert("작성자를 입력하세요.");
			form.name.focus();
			return false;
		}
		if (form.title.value == "") {
			alert("제목을 입력하세요.");
			form.title.focus();
			return false;
		}
		if (form.content.value == "") {
			alert("내용을 입력하세요.");
			form.content.focus();
			return false;
		}
		if (form.pass.value == "") {
			alert("비밀번호를 입력하세요.");
			form.pass.focus();
			return false;
		}
		form.submit();
	}
</script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="../Common/Link.jsp" />
	<!-- 공통 링크 -->
	<br>
	<main class="container">
		<h2>Write</h2>

		<form 
			action="${pageContext.request.contextPath}/ListController" method="POST"  >
				<br>
			<table class="table-border" width="100%">
				<tr>
					<td>작성자</td>
					<td colspan="3"><input type="text" name="name" style="width: 150px;"required/></td>
				</tr>
				<tr>
					<td>제목</td>
					<td colspan="3"><input type="text" name="title" style="width: 300px;"required/></td>
				</tr>
				<tr>
					<td>내용</td>
					<td colspan="3"><textarea name="content"
							style="width: 90%; height: 100px;"required></textarea></td>
				</tr>
				<tr>
					<td>첨부 파일</td>
					<td><input type="file" name="ofile" class="mt-3" /> <br>
					<br></td>
					<td>비밀번호</td>
					<td><input type="password" name="pass" style="width: 100px;"required/>
					<input type="hidden" name="idx" value="${board.idx}" /> 
					</td>
				</tr>

				<tr>
					<td colspan="4" align="center">
						<input class="btn btn-primary me-md-2" value="작성완료" type="submit">
							
						<button class="btn btn-primary me-md-2" type="reset">RESET</button>
						<button class="btn btn-primary me-md-2" type="button"
							onclick="location.href='List.jsp';">목록 바로가기</button>
					</td>
				</tr>
			</table>
		</form>
		
		
	</main>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
</body>
</html>