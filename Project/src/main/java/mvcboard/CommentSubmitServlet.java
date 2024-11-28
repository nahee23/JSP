package mvcboard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import common.DBConnection;

@WebServlet("/submitComment")
public class CommentSubmitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8"); // 한글 입력 처리
    	HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        String text = request.getParameter("text");
        int Idx = Integer.parseInt(request.getParameter("idx"));
        
        if (name == null || name.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("로그인이 필요합니다.");
            return;
        }

        if (text == null || text.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("댓글 내용을 입력해주세요.");
            return;
        }

        try (Connection conn = DBConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO Comments (idx, name, text) VALUES (?, ?, ?)")) {
        	
        	stmt.setInt(1, Idx);
            stmt.setString(2, name);
            stmt.setString(3, text);
            stmt.executeUpdate();
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
