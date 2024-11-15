package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//서블릿 클래스는 HttpServlet 상속
public class Test extends HttpServlet {
	//요청 request 응답 response
	@Override //Ctrl + space
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Get 요청 시 실행 코드
		// 1. 로그인 화면 요청시 => 로그인 화면 보여줌 forward 또는 redirect 로그인.jsp
		// 2. 게시판 보여주기 => DB 해당 테이블 리스트 가져옴 => list.jsp (리스트도 함께 보냄)
		// 3.
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Post 요청 시 실행 코드
		//로그인 요청시 id,pw => DB 확인 => 로그인 맞으면 로그인.jsp / 실패시 실패.jsp
	}
}
