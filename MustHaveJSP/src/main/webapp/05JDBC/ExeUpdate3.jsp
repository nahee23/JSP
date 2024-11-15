<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="common.JDBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>JDBC</title></head>
<body>
    <h2>회원 삭제 테스트(executeUpdate() 사용)</h2>
    <%
    // DB에 연결
    JDBConnect jdbc = new JDBConnect();
    
    // 테스트용 입력값 준비 
    String id = "test1";
    //id만 있으면 삭제됨

    // 쿼리문 생성
    String sql = "DELETE FROM member WHERE ID=?";  
    PreparedStatement psmt = jdbc.con.prepareStatement(sql);  
    psmt.setString(1, id);

    // 쿼리 수행 (입력/수정/삭제 시 업데이트)
    int inResult = psmt.executeUpdate(); 
    out.println(inResult + "행이 삭제되었습니다.");

    // 연결 닫기
    jdbc.close(); 
    %>
</body>
</html>
