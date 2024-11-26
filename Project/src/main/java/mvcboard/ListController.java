package mvcboard;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import board.BoardDAO;
import board.BoardDAOImpl;
import board.BoardDTO;
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
		 //System.out.println("Received action: " + action); // 로그 확인
		System.out.println("Request URI: " + request.getRequestURI());
	    System.out.println("Query String: " + request.getQueryString());
	    System.out.println("Received action: " + action);

		if (action == null || action.isEmpty()) {
			action = "LIST";
		}
		

		switch (action) {
		case "LIST":
			if ("LIST".equals(request.getAttribute("processing"))) {
                System.out.println("Already processing LIST. Avoiding redirection loop.");
                return;
            }
            request.setAttribute("processing", "LIST");
            listBoard(request, response); // LIST는 무조건 forward로 처리
            break;
		case "EDIT":
			getSingleBoard(request, response);
			break;
		case "VIEW":
			handleViewAction(request, response);
			break;
		case "PLUS":
			recommendCountAction(request, response);
			break;
		case "DELETE":
			delete(request, response);
			break;
		default:
			// 알 수 없는 액션의 경우 LIST로 리디렉션
            System.out.println("Unknown action: " + action + ". Redirecting to LIST.");
            response.sendRedirect("ListController?action=LIST");
            return;
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

		BoardDTO theBoard = boardDAO.get(idx);

		request.setAttribute("board", theBoard);

		request.getRequestDispatcher("/freeboard/Edit.jsp").forward(request, response);
	}

	private void listBoard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<BoardDTO> theList = boardDAO.get();

		request.setAttribute("Boardlist", theList);

		request.getRequestDispatcher("freeboard/List.jsp").forward(request, response);
	}
	
	private void handleViewAction(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String idx = request.getParameter("idx");
	    if (idx == null || idx.isEmpty()) {
	        System.out.println("Error: idx parameter is missing for VIEW action.");
	        response.sendRedirect("ListController?action=LIST");
	        return;
	    }

	    System.out.println("Executing VIEW action for idx: " + idx);
	    boardDAO.updateVisitCount(idx); // 조회수 증가
	    BoardDTO dto = boardDAO.get(idx);

	    request.setAttribute("dto", dto);
	    request.getRequestDispatcher("freeboard/View.jsp").forward(request, response);
	}
	
	private void recommendCountAction(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    String idx = request.getParameter("idx");
	    if (idx == null || idx.isEmpty()) {
	        response.sendRedirect("ListController?action=LIST");
	        return;
	    }

	    //System.out.println("Executing VIEW action for idx: " + idx);
	    boardDAO.recommendCountPlus(idx); // 추천수 증가
	    BoardDTO dto = boardDAO.get(idx);

	    request.setAttribute("dto", dto);
	    request.getRequestDispatcher("freeboard/View.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
		System.out.println("작성시작");
		//form 입력과 form 수정 둘다 처리하기
		String idx = request.getParameter("idx");
		
		String name = request.getParameter("name");
	    String title = request.getParameter("title");
	    String content = request.getParameter("content");
	    String pass = request.getParameter("pass");

	    System.out.println("Received name: " + name);
	    System.out.println("Received title: " + title);
	    System.out.println("Received content: " + content);
	    System.out.println("Received pass: " + pass);

		BoardDTO e = new BoardDTO();
		e.setName(name);
		e.setTitle(title);
		e.setContent(content);
		e.setOfile(request.getParameter("ofile"));
		e.setSfile(request.getParameter("sfile"));
		e.setPass(pass);

		if (idx == null || idx.isEmpty()  ) {
			//id 가 없으므로 새로 직원 입력
			if (boardDAO.save(e)) {
				request.setAttribute("NOTIFICATION", "작성되었습니다!");
			}

		} else {
			//id 가 있으므로 기존 직원 수정
			e.setIdx(Integer.parseInt(idx));
			if (boardDAO.update(e)) {
				 request.setAttribute("NOTIFICATION", "수정되었습니다!");
			}

		}

		listBoard(request, response); //리스트 페이지로 

	}
}

