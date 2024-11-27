<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../Login/IsLoggedIn.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판-수정하기</title>
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
<h2>Edit</h2>

<form name="writeFrm" method="POST" action="${pageContext.request.contextPath}/ListController" onsubmit="return validateForm(this);">
<input type="hidden" name="idx" value="${ board.idx }"/>
<input type="hidden" name="prevOfile" value="${ board.ofile }" />
<input type="hidden" name="prevSfile" value="${ board.sfile }" />
    
    <br>
<table class="table-border" width="100%">
    <tr>
        <td>작성자</td>
        <td>
            <input type="text" name="name" style="width:150px;" value="${ board.name }" />
        </td>
    </tr>
    <tr>
        <td>제목</td>
        <td>
            <input type="text" name="title" style="width:90%;" value="${ board.title }" />
        </td>
    </tr>
    <tr>
        <td>내용</td>
        <td>
            <textarea name="content" style="width:90%;height:100px;">${ board.content }</textarea>
        </td>
    </tr>
    <tr>
        <td >첨부 파일</td>
        <td>
            <input type="file" name="ofile" class="mt-3" />
            <br><br>
        </td>
    </tr>
    
    <tr>
        <td colspan="2" align="center">
            <button class="btn btn-primary me-md-2" type="submit">작성 완료</button>
            <button class="btn btn-primary me-md-2" type="reset">RESET</button>
            <button class="btn btn-primary me-md-2" type="button" onclick="location.href='${pageContext.request.contextPath}/ListController?action=LIST';">
                목록 바로가기
            </button>
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