package mvcboard;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import common.DBConnection;

@WebServlet("/getComments")
public class CommentListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 int Idx = Integer.parseInt(request.getParameter("idx"));
    	response.setContentType("application/json; charset=UTF-8");
  
        try (Connection conn = DBConnection.openConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT name, text, DATE_FORMAT(createdAt, '%Y-%m-%d %H:%i:%s') AS createdAt FROM Comments WHERE postId = ? ORDER BY createdAt DESC")) {
             
        		 stmt.setInt(1, Idx);
        		ResultSet rs = stmt.executeQuery();
        		
            PrintWriter out = response.getWriter();
                        
            out.write("[");
            boolean isFirst = true;

            while (rs.next()) {
                if (!isFirst) {
                    out.write(",");
                }
                out.write(String.format("{\"name\":\"%s\", \"text\":\"%s\", \"createdAt\":\"%s\"}",
                        rs.getString("name"),
                        rs.getString("text"),
                        rs.getString("createdAt")
                ));
                isFirst = false;
            }

            out.write("]");
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
