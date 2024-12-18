<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>

	<div class="container">

		<h1>회원 가입</h1>
		<hr />

		<div class="row">
			<div class="col-md-4">
				<form id="signupForm"
					action="${pageContext.request.contextPath}/MemberController"
					method="POST">

					<div class="form-group mb-3">
						<label for="id">아이디</label> 
						<div class="d-flex">
						<input type="text"
							class="form-control" id="id" name="id" placeholder="Enter ID"
							value="${member.id}" required> 
						<input type="button"
							onclick="sendServer()" value="중복체크"  class="btn btn-primary ml-2" />
							</div>
						<div id="output"></div>
					</div>

					<!-- Name -->
					<div class="form-group mb-3">
						<label for="name">이름</label> <input type="text"
							class="form-control" id="name" name="name"
							placeholder="Enter Name" required>
					</div>

					<!-- Email -->
					<div class="form-group mb-3">
						<label for="email">이메일</label> <input type="email"
							class="form-control" id="email" name="email"
							placeholder="Enter Email" value="${member.email}" required>
					</div>

					<!-- Password -->
					<div class="form-group mb-3">
						<label for="password">비밀번호</label> <input type="password"
							class="form-control" id="password" name="password"
							placeholder="Enter Password" required>
					</div>

					<!-- Confirm Password -->
					<div class="form-group mb-3">
						<label for="confirmPassword">비밀번호 확인</label> <input
							type="password" class="form-control" id="confirmPassword"
							name="confirmPassword" placeholder="Confirm Password" required>
						<div id="passwordError" class="error-message text-danger mt-1"></div>
					</div>

					<!-- Birth -->
					<div class="form-group mb-3">
						<label for="birth">생년월일</label> <input type="date"
							class="form-control" id="birth" name="birth"
							value="${member.birth}">
					</div>

					<input type="hidden" name="id" value="${member.id}" />

					<button type="submit" class="btn btn-primary">가입하기</button>
				</form>
			</div>
		</div>
		<a
			href="${pageContext.request.contextPath}/MemberController?action=LIST">Back
			to Home</a>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
	<script>
		const input = document.querySelector('input[type="button"]');
		const output = document.getElementById('output');
		const request = new XMLHttpRequest; //ajax 객체 생성

		function sendServer() {
			//아이디를 jsp로 보내 중복체크
			let id = input.value;
			let url = "IDCheck.jsp?id=" + document.getElementById('id').value ;
			request.onreadystatechange = getInfo;
			request.open('GET', url , true);
			request.send();
		}
		function getInfo() { //Id 중복체크후 결과
			if (request.readyState == 4 && request.status == 200) {
				let info = request.responseText;
				output.textContent = info;
			}
		}
	</script>
	<script>
		//JavaScript to validate password confirmation
		document.getElementById("signupForm").addEventListener(
				"submit",
				function(event) {
					const password = document.getElementById("password").value;
					const confirmPassword = document
							.getElementById("confirmPassword").value;
					const passwordError = document
							.getElementById("passwordError");

					if (password !== confirmPassword) {
						event.preventDefault(); // Prevent form submission
						passwordError.textContent = "비밀번호가 일치하지 않습니다.";
					} else {
						passwordError.textContent = ""; // Clear error message
					}
				});
	</script>
</body>
</html>