package mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import board.BoardDAO;
import board.BoardDAOImpl;
import board.BoardDTO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListController")
public class ListController extends HttpServlet {
	
	BoardDAO boardDAO = null;

	// 객체를 한번만 생성
	public ListController() {
		boardDAO = new BoardDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 액션 파라미터
		String action = request.getParameter("action");

		if (action == null) {
			action = "LIST";
		}

		switch (action) {
		case "LIST":
			listBoard(request, response);
			break;
		case "EDIT":
			getSingleBoard(request, response);
			break;
		case "DELETE":
			delete(request, response);
			break;
		default:
			listBoard(request, response);
			break;
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idx = request.getParameter("idx");

		if (boardDAO.delete(Integer.parseInt(idx))) {
			request.setAttribute("NOTIFICATION", "Employee Deleted Successfully!");
		}

		listBoard(request, response);
	}

	private void getSingleBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idx = request.getParameter("idx");

		BoardDTO theMember = boardDAO.get(Integer.parseInt(idx));

		request.setAttribute("member", theMember);

		request.getRequestDispatcher("/Login/LoginForm.jsp").forward(request, response);
	}

	private void listBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<BoardDTO> theList = boardDAO.get();

		request.setAttribute("list", theList);

		request.getRequestDispatcher("freeboard/List.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//form 입력과 form 수정 둘다 처리하기
		String idx = request.getParameter("idx");

		BoardDTO e = new BoardDTO();
		e.setName(request.getParameter("name"));
		e.setTitle(request.getParameter("title"));
		e.setContent(request.getParameter("content"));
		e.setOfile(request.getParameter("ofile"));
		e.setSfile(request.getParameter("sfile"));
		e.setPass(request.getParameter("pass"));

		if (idx == null || idx.isEmpty()  ) {
			//id 가 없으므로 새로 직원 입력
			if (boardDAO.save(e)) {
				request.setAttribute("NOTIFICATION", "회원가입이 완료되었습니다!");
			}

		} else {
			//id 가 있으므로 기존 직원 수정
			e.setIdx(Integer.parseInt(idx));
			if (boardDAO.update(e)) {
				 request.setAttribute("NOTIFICATION", "회원 정보가 수정되었습니다!");
			}

		}

		listBoard(request, response); //리스트 페이지로 

	}

	
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//       // DAO 생성
//       BoardDAOImpl dao = new BoardDAOImpl();
//
//        // 뷰에 전달할 매개변수 저장용 맵 생성
//        Map<String, Object> map = new HashMap<String, Object>();
//
//        String searchField = req.getParameter("searchField");
//        String searchWord = req.getParameter("searchWord");
//        if (searchWord != null) {
//            // 쿼리스트링으로 전달받은 매개변수 중 검색어가 있다면 map에 저장
//            map.put("searchField", searchField);
//            map.put("searchWord", searchWord);
//        }
//        int totalCount = dao.selectCount(map);  // 게시물 개수
//
//        /* 페이지 처리 설정 현제페이지 10개  */
//        ServletContext application = getServletContext();
//        int pageSize = Integer.parseInt(application.getInitParameter("POSTS_PER_PAGE"));
//        int blockPage = Integer.parseInt(application.getInitParameter("PAGES_PER_BLOCK"));
//
//        // 현재 페이지 확인
//        int pageNum = 1;  // 기본값
//        String pageTemp = req.getParameter("pageNum");
//        if (pageTemp != null && !pageTemp.equals(""))
//            pageNum = Integer.parseInt(pageTemp); // 요청받은 페이지로 수정
//
//        // 목록에 출력할 게시물 범위 계산
//        int start = (pageNum - 1) * pageSize + 1;  // 첫 게시물 번호
//        int end = pageNum * pageSize; // 마지막 게시물 번호
//        map.put("start", start);
//        map.put("end", end);
//        /* 페이지 처리 end */
//
//        List<BoardDTO> boardLists = dao.selectListPage(map);  // 게시물 목록 받기
//        //dao.close(); // DB 연결 닫기
//
//        // 뷰에 전달할 매개변수 추가 (아래 페이지 표시)
//        String pagingImg = BoardPage.pagingStr(totalCount, pageSize,
//                blockPage, pageNum, "../mvcboard/list.do");  // 바로가기 영역 HTML 문자열
//        map.put("pagingImg", pagingImg);
//        map.put("totalCount", totalCount);
//        map.put("pageSize", pageSize);
//        map.put("pageNum", pageNum);
//
//        // 전달할 데이터를 request 영역에 저장 후 List.jsp로 포워드
//        req.setAttribute("boardLists", boardLists);
//        req.setAttribute("map", map);
//        req.getRequestDispatcher("/freeboard/List.jsp").forward(req, resp);
    }

