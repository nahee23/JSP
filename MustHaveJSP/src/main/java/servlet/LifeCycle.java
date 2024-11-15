package servlet;

import java.io.IOException;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/12Servlet/LifeCycle.do")
public class LifeCycle extends HttpServlet {

    @PostConstruct
    public void myPostConstruct() {
        System.out.println("myPostConstruct() 호출");
    }
    
    //init 은 브라우저를 바꾸더라도 처음에만 실행됨
    @Override
    public void init() throws ServletException {
        System.out.println("init() 호출");
    }
    
    //모든 메서드 요청시 실행
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("service() 호출");
        super.service(req, resp);
    }        

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        System.out.println("doGet() 호출");
        req.getRequestDispatcher("/12Servlet/LifeCycle.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        System.out.println("doPost() 호출");
        req.getRequestDispatcher("/12Servlet/LifeCycle.jsp").forward(req, resp);
    }
    
    //서버를 종료해야 콘솔에 나옴
    @Override
    public void destroy() {
        System.out.println("destroy() 호출");
    }

    @PreDestroy
    public void myPreDestroy() {
        System.out.println("myPreDestroy() 호출");
    }
}
