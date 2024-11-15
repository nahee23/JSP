<%@ page import="common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 속성 저장
pageContext.setAttribute("pageInteger", 1000);
pageContext.setAttribute("pageString", "페이지 영역의 문자열");
pageContext.setAttribute("pagePerson", new Person("한석봉", 99)); 
%>
<html>
<head><title>page 영역</title></head>
<body>
    <h2>page 영역의 속성값 읽기</h2>
    <%
    // 속성 읽기
    int pInteger = (Integer)(pageContext.getAttribute("pageInteger"));
    String pString = pageContext.getAttribute("pageString").toString();
    Person pPerson = (Person)(pageContext.getAttribute("pagePerson"));
    %>
    <ul>
        <li>Integer 객체 : <%= pInteger %></li>
        <li>String 객체 : <%= pString %></li>
        <li>Person 객체 : <%= pPerson.getName() %>, <%= pPerson.getAge() %></li>
    </ul>

    <h2>include된 파일에서 page 영역 읽어오기</h2> <!-- 다른페이지를 불러와 같은 페이지에 출력되기때문에? 값 쓸 수 있음 -->
    <%@ include file="PageInclude.jsp" %>
       
    <h2>페이지 이동 후 page 영역 읽어오기</h2> <!-- 다른페이지로 이동하기 때문에 값이 소멸되어 값 출력X -->
    <a href="PageLocation.jsp">PageLocation.jsp 바로가기</a>
</body>
</html>