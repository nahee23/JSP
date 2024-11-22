<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%
String ID = request.getParameter("id");
if (ID != "" && ID.length() >= 4) {
	try {
		String url = "jdbc:mysql://localhost:3306/webshop?useSSL=false&serverTimezone=UTC";
		Connection con = DriverManager.getConnection( url , "root", "1234");
		PreparedStatement ps = con.prepareStatement("select * from Member where id=?");
		ps.setString( 1 , ID );
		ResultSet rs = ps.executeQuery();
		if (rs.next() ) {
			out.print("이미 존재하는 아이디 입니다.");
		} else {
			out.print("사용가능한 아이디 입니다.");
		}
	} catch (Exception e) {
		out.print(e);
	}
} else {
	out.print("잘못된 아이디 형식입니다.");
}
%>