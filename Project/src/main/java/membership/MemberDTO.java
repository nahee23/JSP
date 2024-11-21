package membership;

public class MemberDTO {
	private int idx;            // 회원 고유 번호
    private String id;          // 회원 ID
    private String name;        // 이름
    private String email;       // 이메일
    private String password;    // 비밀번호 (해시 값)
    private String birth;   	// 생년월일
    private String created_At;   // 계정 생성일
    private String updated_At;   // 계정 수정일
	
    public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getCreated_At() {
		return created_At;
	}
	public void setCreated_At(String createdAt) {
		this.created_At = createdAt;
	}
	public String getUpdated_At() {
		return updated_At;
	}
	public void setUpdated_At(String updatedAt) {
		this.updated_At = updatedAt;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [idx=" + idx + ", id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", birth=" + birth + ", createdAt=" + created_At + ", updatedAt=" + updated_At + "]";
	}
    
	
    
}
