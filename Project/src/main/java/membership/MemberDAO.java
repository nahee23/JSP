package membership;

import java.util.List;

import membership.MemberDTO;

//필요한 기능을 요약
public interface MemberDAO {
	//직원목록 가져옴
	List<MemberDTO> get();
	//직원 1명 가져옴 (id 입력)
	MemberDTO get(int id);
	//새 직원 입력 (직원 객체 입력)
	boolean save(MemberDTO member);
	//직원 삭제 (id 입력)
	boolean delete(int id);
	//직원 업데이트 (직원 객체 입력)
	boolean update(MemberDTO member);
}
