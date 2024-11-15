<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>JSTL - forEach 1</title></head>
<body>
    <h4>일반 for문 형태의 forEach 태그</h4>
    <c:forEach begin="1" end="3" step="1" var="i">
        <p>반복 ${ i }입니다</p>
    </c:forEach>

    <h4>varStatus 속성 살펴보기</h4>
    <table border="1">
    <c:forEach begin="3" end="5" var="i" varStatus="loop">
        <tr>
            <td>count(반복횟수) : ${ loop.count }</td>
            <td>index(인덱스번호-현재변수값) : ${ loop.index }</td>
            <td>current(현재변수값) : ${ loop.current }</td>
            <td>first(처음반복일때 true) : ${ loop.first }</td>
            <td>last(마지막반복일때 true) : ${ loop.last }</td>
        </tr>
    </c:forEach>
    </table>

    <h4>1에서 100까지 정수 중 홀수의 합</h4>
    <c:forEach begin="1" end="100" var="j">
        <c:if test="${ j mod 2 ne 0}">
            <c:set var="sum" value="${ sum + j }" />
        </c:if>
    </c:forEach>
    1~100 사이의 정수 중 홀수의 합은? ${ sum }
</body>
</html>