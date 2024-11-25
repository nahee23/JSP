package common;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

	@WebServlet("/ongoing")
	public class OngoingMusicalServlet extends HttpServlet {
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        // 공연 중인 공연만 전달
			try {
				if (req.getAttribute("musicals") == null) { 
				List<MusicalCrawler.Musical> ongoingMusicals = MusicalCrawler.getMusicalListFromUrl("https://m.playdb.co.kr/Play/List?maincategory=000001");
				
				req.setAttribute("ongoingMusicals", ongoingMusicals);
		        req.getRequestDispatcher("/views/ongoing.jsp").forward(req, resp);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	    }
	}

