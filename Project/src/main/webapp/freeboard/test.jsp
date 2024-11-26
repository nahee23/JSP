<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/ListController" method="POST">
	<input type="text" name="name"/>
	<input value="작성완료" type="submit">
	</form>
</body>
</html>