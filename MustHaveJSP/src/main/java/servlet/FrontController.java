package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//localhost:8080/*****.one (.one 으로 끝나는 모든 요청을 받는 프론트컨트롤러)
@WebServlet("*.one")
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String uri = req.getRequestURI(); //요청한 주소
        int lastSlash = uri.lastIndexOf("/"); //마지막 슬래쉬 위치
        String commandStr = uri.substring(lastSlash);

        if (commandStr.equals("/regist.one"))
            registFunc(req);
        else if (commandStr.equals("/login.one"))
            loginFunc(req);
        else if (commandStr.equals("/freeboard.one"))
            freeboardFunc(req);

        req.setAttribute("uri", uri);
        req.setAttribute("commandStr", commandStr); 
        req.getRequestDispatcher("/12Servlet/FrontController.jsp").forward(req, resp);
    }

    // 페이지별 처리 메서드
    void registFunc(HttpServletRequest req) {
        req.setAttribute("resultValue", "<h4>회원가입</h4>");
    }

    void loginFunc(HttpServletRequest req) {
        req.setAttribute("resultValue", "<h4>로그인</h4>");
    }

    void freeboardFunc(HttpServletRequest req) {
        req.setAttribute("resultValue", "<h4>자유게시판</h4>");
    }
}
