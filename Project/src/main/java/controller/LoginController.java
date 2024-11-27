package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import membership.LoginDAO;
import membership.LoginDAOImpl;
import membership.MemberDTO;

@WebServlet("/loginprocess")
public class LoginController extends HttpServlet {
	
	LoginDAO loginDAO = null;

	public LoginController() {
		loginDAO = new LoginDAOImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberDTO login = new MemberDTO();
		login.setId(request.getParameter("id"));
		login.setPassword(request.getParameter("password"));
		login.setName(request.getParameter("name"));
		
		String result=loginDAO.loginCheck(login);
		
		if(result.equals("true")){
			 if ("must".equals(login.getId()) && "1234".equals(login.getPassword())) {
			session.setAttribute("id",login.getId());
			response.sendRedirect("MemberController?action=LIST");
			 } else {
	        // 일반인 로그인 시 Main.jsp로 리다이렉트
			session.setAttribute("id",login.getId());
			session.setAttribute("name", login.getName());
			System.out.println("저장된 name: " + session.getAttribute("name"));
	        response.sendRedirect("Main.jsp");
	    }
		//서블릿 컨텍스트패스 http://localhost:8080/DATABASE
		//String contextPath = request.getContextPath();
		}
		if(result.equals("false")){
			response.sendRedirect(request.getContextPath() +"/index.jsp?status=false");
		}
		 
		if(result.equals("error")){
		    response.sendRedirect(request.getContextPath() +"/index.jsp?status=error");
		}
		
	}

}