<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ include file="../Login/IsLoggedIn.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>양도게시판-수정하기</title>
<script type="text/javascript">
    function validateForm(form) {
        if (form.name.value == "") {
            alert("작성자를 입력하세요.");
            form.name.focus();
            return false;
        }
        if (form.sale_title.value == "") {
            alert("제목을 입력하세요.");
            form.title.focus();
            return false;
        }
        if (form.sale_content.value == "") {
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

<form name="writeFrm" method="post"  action="${pageContext.request.contextPath}/SaleController" onsubmit="return validateForm(this);">
<input type="hidden" name="idx" value="${ sale.idx }"/>
<input type="hidden" name="prevOfile" value="${ sale.ofile }" />
<input type="hidden" name="prevSfile" value="${ sale.sfile }" />
    
    <br>
<table class="table-border" width="100%">
    <tr>
        <td>작성자</td>
        <td colspan="3">
            <input type="text" name="name" style="width:200px;" value="${ sale.name }" />
        </td>
    </tr>
    <tr>
        <td>공연명</td>
        <td colspan="3">
            <input type="text" name="performance_name" style="width:200px;" value="${ sale.performance_name }" />
        </td>
    </tr>
    <tr>
        <td>공연날짜</td>
        <td colspan="3">
            <input type="text" name="performance_datetime" style="width:150px;" value="${ sale.performance_datetime }" />
        </td>
        
    </tr>
    <tr>
        <td>가격</td>
        <td>
            <input type="text" name="price" style="width:150px;" value="${ sale.price }" />
        </td>
        <td>자리등급</td>
        <td>
            <input type="text" name="grade" style="width:150px;" value="${ sale.grade }" />
        </td>
    </tr>
    <tr>
        <td>제목</td>
        <td colspan="3">
            <input type="text" name="sale_title" style="width:90%;" value="${ sale.sale_title} "/>
        </td>
    </tr>
    <tr>
        <td>내용</td>
        <td colspan="3">
            <textarea name="sale_content" style="width:90%;height:100px;">${ sale.sale_content }</textarea>
        </td>
    </tr>
    <tr>
        <td >첨부 파일</td>
        <td colspan="3">
            <input type="file" name="ofile" class="mt-3" />
            <br><br>
        </td>
    </tr>
    	<td>비밀번호</td>
					<td><input type="password" name="pass" style="width: 100px;"
						required /> <input type="hidden" name="idx" value="${board.idx}" />
					</td><br>
					<br>
				</tr>
    
    <tr>
        <td colspan="4" align="center">
            <button class="btn btn-primary me-md-2" type="submit">작성 완료</button>
            <button class="btn btn-primary me-md-2" type="reset">RESET</button>
            <button class="btn btn-primary me-md-2" type="button" onclick="location.href='${pageContext.request.contextPath}/SaleController?action=LIST';">
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