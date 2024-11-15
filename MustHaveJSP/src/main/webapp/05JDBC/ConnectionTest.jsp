<%@page import="common.DBConnPool"%>
<%@ page import="common.JDBConnect"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>JDBC</title></head>
<body>
    <h2>JDBC 테스트 1</h2>
    <%
    //DB 연결
    JDBConnect jdbc1 = new JDBConnect(); 
    jdbc1.close(); //종료
    %>
    
    <h2>JDBC 테스트 2</h2>
    <%
    //web.xml 설정에서 가져옴
    String driver = application.getInitParameter("OracleDriver");
    String url = application.getInitParameter("OracleURL");
    String id = application.getInitParameter("OracleId");
    String pwd = application.getInitParameter("OraclePwd");
    
    //DB 연결
    JDBConnect jdbc2 = new JDBConnect(driver,url,id,pwd); 
    jdbc2.close(); //종료
    %>
    
    <h2>JDBC 테스트 3</h2>
    <%
    //DB 연결
    JDBConnect jdbc3 = new JDBConnect(application); 
    jdbc3.close(); //종료
    %>
    
    <h2>커넥션 풀 테스트</h2>
    <%
    //DB 연결
    DBConnPool pool = new DBConnPool();
    pool.close();
    %>
    
</body>
</html>