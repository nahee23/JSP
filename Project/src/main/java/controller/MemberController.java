package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import membership.MemberDAO;
import membership.MemberDAOImpl;
import membership.MemberDTO;

@WebServlet("/MemberController")
public class MemberController extends HttpServlet {

	MemberDAO memberDAO = null;

	// 객체를 한번만 생성
	public MemberController() {
		memberDAO = new MemberDAOImpl();
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
			listMember(request, response);
			break;
		case "EDIT":
			getSingleMember(request, response);
			break;
		case "DELETE":
			deleteMember(request, response);
			break;
		default:
			listMember(request, response);
			break;
		}
	}

	private void deleteMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idx = request.getParameter("idx");

		if (memberDAO.delete(Integer.parseInt(idx))) {
			request.setAttribute("NOTIFICATION", "Employee Deleted Successfully!");
		}

		listMember(request, response);
	}

	private void getSingleMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idx = request.getParameter("idx");

		MemberDTO theMember = memberDAO.get(Integer.parseInt(idx));

		request.setAttribute("member", theMember);

		request.getRequestDispatcher("/Login/LoginForm.jsp").forward(request, response);
	}

	private void listMember(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<MemberDTO> theList = memberDAO.get();

		request.setAttribute("list", theList);

		request.getRequestDispatcher("Main.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//form 입력과 form 수정 둘다 처리하기
		String idx = request.getParameter("idx");

		MemberDTO e = new MemberDTO();
		e.setId(request.getParameter("id"));
		e.setName(request.getParameter("name"));
		e.setEmail(request.getParameter("email"));
		e.setPassword(request.getParameter("password"));
		e.setBirth(request.getParameter("birth"));

		if (idx == null || idx.isEmpty()  ) {
			//id 가 없으므로 새로 직원 입력
			if (memberDAO.save(e)) {
				request.setAttribute("NOTIFICATION", "회원가입이 완료되었습니다!");
			}

		} else {
			//id 가 있으므로 기존 직원 수정
			e.setIdx(Integer.parseInt(idx));
			if (memberDAO.update(e)) {
				 request.setAttribute("NOTIFICATION", "회원 정보가 수정되었습니다!");
			}

		}

		listMember(request, response); //리스트 페이지로 

	}
}
