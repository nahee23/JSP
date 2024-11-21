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
		
		String result=loginDAO.loginCheck(login);
		
		if(result.equals("true")){
			session.setAttribute("id",login.getId());
			response.sendRedirect("MemberController?action=LIST");
		}
		//서블릿 컨텍스트패스 http://localhost:8080/DATABASE
		//String contextPath = request.getContextPath();
		 
		if(result.equals("false")){
			response.sendRedirect(request.getContextPath() +"/index.jsp?status=false");
		}
		 
		if(result.equals("error")){
		    response.sendRedirect(request.getContextPath() +"/index.jsp?status=error");
		}
		
	}
}
