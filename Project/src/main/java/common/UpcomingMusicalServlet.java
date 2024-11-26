package common;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/upcoming")
public class UpcomingMusicalServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 예정된 공연만 전달
		
		
		List<MusicalCrawler.Musical> upcomingMusicals;
		try {
			if (req.getAttribute("upcomingMusicals") == null) { 
			upcomingMusicals = MusicalCrawler.getMusicalListFromUrl(
					"https://m.playdb.co.kr/Play/List?maincategory=000001&playtype=3&sortby=weekviewcnt");
		
			req.setAttribute("upcomingMusicals", upcomingMusicals);
			req.getRequestDispatcher("/views/upcoming.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
