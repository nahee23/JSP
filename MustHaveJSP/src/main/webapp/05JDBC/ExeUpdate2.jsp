<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>JDBC</title></head>
<body>
    <h2>회원 수정 테스트(executeUpdate() 사용)</h2>
    <%
    // DB에 연결
    JDBConnect jdbc = new JDBConnect();
    
    // 테스트용 입력값 준비 
    String id = "test1";
    String pass = "1234";
    String name = "테스트1회원수정";

    // 쿼리문 생성
    String sql = "UPDATE member SET pass=?,name=? WHERE ID=?";  
    PreparedStatement psmt = jdbc.con.prepareStatement(sql);  
    psmt.setString(1, pass); //첫번째 물음표 pass 입력
    psmt.setString(2, name); 
    psmt.setString(3, id);

    // 쿼리 수행 (입력/수정/삭제 시 업데이트)
    int inResult = psmt.executeUpdate(); 
    out.println(inResult + "행이 수정되었습니다.");

    // 연결 닫기
    jdbc.close(); 
    %>
</body>
</html>
