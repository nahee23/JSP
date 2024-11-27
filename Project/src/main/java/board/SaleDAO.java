package board;

import java.util.List;

import board.SaleDTO;

public interface SaleDAO {
    

	//직원목록 가져옴
		List<SaleDTO> get();
		//직원 1명 가져옴 (id 입력)
		SaleDTO get(String idx);
		//새 직원 입력 (직원 객체 입력)
		boolean save(SaleDTO sale);
		//직원 삭제 (id 입력)
		boolean delete(int idx);
		//직원 업데이트 (직원 객체 입력)
		boolean update(SaleDTO sale);

		// 주어진 일련번호에 해당하는 게시물의 조회수를 1 증가시킵니다.
		void updateVisitCount(String idx) ;
     
		// 입력한 비밀번호가 지정한 일련번호의 게시물의 비밀번호와 일치하는지 확인합니다.
		boolean confirmPassword(String pass, String idx) ;

}

