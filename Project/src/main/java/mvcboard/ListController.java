package mvcboard;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


import board.BoardDAO;
import board.BoardDAOImpl;
import board.BoardDTO;
import board.CommentDAO;
import board.CommentDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ListController")
public class ListController extends HttpServlet {
	
	BoardDAO boardDAO = null;
	private CommentDAO commentDAO = null;

	// 객체를 한번만 생성
	public ListController() {
		boardDAO = new BoardDAOImpl();
		commentDAO = new CommentDAO();
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
	// 댓글 추가 처리
    private void submitComment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Idx = Integer.parseInt(request.getParameter("idx"));
        String name = (String) request.getSession().getAttribute("name"); // 로그인한 사용자 이름
        String text = request.getParameter("text");

        CommentDTO commentDto = new CommentDTO();
        commentDto.setIdx(Idx);
        commentDto.setName(name);
        commentDto.setText(text);

        // 댓글 추가
        commentDAO.addComment(commentDto);

        // 댓글 목록 다시 로드
        if (!response.isCommitted()) {  // 응답이 커밋되었는지 확인
            response.sendRedirect("ListController?action=VIEW&idx=" + Idx);  // 리다이렉트
        } else {
            request.getRequestDispatcher("freeboard/View.jsp").forward(request, response);  // forward 시 응답이 커밋되지 않은 경우
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
		int idx = Integer.parseInt(request.getParameter("idx"));

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
	    int idx = Integer.parseInt(request.getParameter("idx"));
//	    if (idx == null || idx.isEmpty()) {
//	        System.out.println("Error: idx parameter is missing for VIEW action.");
//	        response.sendRedirect("ListController?action=LIST");
//	        return;
//	    }

	    System.out.println("Executing VIEW action for idx: " + idx);
	    boardDAO.updateVisitCount(idx); // 조회수 증가
	    BoardDTO dto = boardDAO.get(idx);

	    request.setAttribute("dto", dto);
	    
	 // 댓글 목록 가져오기
        List<CommentDTO> comments = commentDAO.getCommentsForPost(idx);
        request.setAttribute("comments", comments);
        
	    request.getRequestDispatcher("freeboard/View.jsp").forward(request, response);
	}
	
	private void recommendCountAction(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    int idx = Integer.parseInt(request.getParameter("idx"));
//	    if (idx == null || idx.isEmpty()) {
//	        response.sendRedirect("ListController?action=LIST");
//	        return;
//	    }

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
	    String action = request.getParameter("action");  // action 파라미터 받기

	    System.out.println("Received name: " + name);
	    System.out.println("Received title: " + title);
	    System.out.println("Received content: " + content);
	    System.out.println("Received pass: " + pass);
	    
	    // Action 처리
	    if ("SUBMIT_COMMENT".equals(action)) {
	        submitComment(request, response);  // 댓글 제출 처리
	        return;  // 댓글 처리가 끝났으면 이후 코드가 실행되지 않도록 return
	    }

		BoardDTO e = new BoardDTO();
		e.setName(name);
		e.setTitle(title);
		e.setContent(content);
		e.setOfile(request.getParameter("ofile"));
		e.setSfile(request.getParameter("sfile"));
		e.setPass(pass);
		 

	        if ("SUBMIT_COMMENT".equals(action)) {
	            submitComment(request, response);  // 댓글 제출 처리
	        }
	    

		if (idx == null || idx.isEmpty()  ) {
			//id 가 없으므로 새로 직원 입력
			if (boardDAO.save(e)) {
				request.setAttribute("NOTIFICATION", "작성되었습니다!");
			}

		} else {
			// idx가 있으므로 기존 게시글 수정
	        // 수정하려면 비밀번호를 확인해야 한다
	        boolean isPasswordCorrect = boardDAO.confirmPassword(pass, idx);
	        if (!isPasswordCorrect) {
	            // 비밀번호가 일치하지 않으면 수정 불가
	            request.setAttribute("NOTIFICATION", "비밀번호가 일치하지 않습니다.");
	            
	            String referer = request.getHeader("Referer");
	            
	         // 이전 페이지가 있을 경우 해당 페이지로 포워드 또는 리다이렉트
	            if (referer != null && !referer.isEmpty()) {
	                response.sendRedirect(referer); // 이전 페이지로 리다이렉트
	                return; // 리다이렉트 후 추가 코드 실행 방지
	            }
	            
	            request.getRequestDispatcher("Main.jsp").forward(request, response);
	            return;  // 비밀번호 오류 시 뒤로 가기
	        }

	        // 비밀번호가 일치하면 수정 진행
	        e.setIdx(Integer.parseInt(idx));
	        if (boardDAO.update(e)) {
	            request.setAttribute("NOTIFICATION", "수정되었습니다!");
	        
			}

		}

		if (!response.isCommitted()) {  // 응답이 커밋되었는지 확인
	        listBoard(request, response);
	    } else {
	        response.sendRedirect("ListController?action=LIST");  // 응답이 이미 커밋되었으면 리다이렉트
	    }

	}
}

