package membership;

import membership.MemberDTO;

public interface LoginDAO {
	String loginCheck(MemberDTO loginBean);
}
