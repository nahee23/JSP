package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//이 서블릿은 web.xml 에 가상의 주소로 매핑됨
public class HelloServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L; //없어도 됨

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("message", "Hello Servlet....!!!");
		req.getRequestDispatcher("/12Servlet/HelloServlet.jsp")
			.forward(req, resp);
	}
}