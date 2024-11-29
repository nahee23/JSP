package API;

import org.json.JSONArray;
import org.json.JSONObject;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String url = "https://m.playdb.co.kr/Play/List?maincategory=000001";
            List<ProjectDTO> musicalList = new ArrayList<>();
            JSONArray jsonArray = Project.getMusicalListAsJson(url);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                // JSON 객체에서 데이터를 추출하여 ProjectDTO 객체를 생성
                String title = jsonObject.getString("title");
                String date = jsonObject.getString("date");
                String place = jsonObject.getString("place");
                String imageUrl = jsonObject.getString("imageUrl");

                // ProjectDTO 객체를 List에 추가
                musicalList.add(new ProjectDTO(title, date, place, imageUrl));
            }

            // List를 JSP로 전달
            request.setAttribute("musicalList", musicalList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Project_Main.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error fetching musical list.");
        }
    }

}
