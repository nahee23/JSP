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
		case "VIEW":
			service(request, response);
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
	
	protected void service(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	        // 게시물 불러오기
	        String idx = request.getParameter("idx");
	        boardDAO.updateVisitCount(idx);  // 조회수 1 증가
	        BoardDTO dto = boardDAO.get(idx);

	        // 줄바꿈 처리 (입력받은 내용에 줄바꿈을 <br/>로 바꿈)
	        //dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>"));
	        
//	        //첨부파일 확장자 추출 및 이미지 타입 확인
//	        String ext = null, fileName = dto.getSfile();
//	        if(fileName!=null) {
//	        	ext = fileName.substring(fileName.lastIndexOf(".")+1);
//	        }
//	        String[] mimeStr = {"png","jpg","gif"};
//	        List<String> mimeList = Arrays.asList(mimeStr);
//	        boolean isImage = false;
//	        if(mimeList.contains(ext)) {
//	        	isImage = true;
//	        }
	        
	        
	        // 게시물(dto) 저장 후 뷰로 포워드
	        request.setAttribute("dto", dto);
	        //request.setAttribute("isImage", isImage);
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

