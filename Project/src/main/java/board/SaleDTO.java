package board;

public class SaleDTO {
	// 멤버 변수 선언
    private int idx; //일련번호
    private String name; //작성자 이름
    private String Sale_title; //글제목
    private String Sale_content; //글내용
    private java.sql.Date postdate; //날짜
    private String ofile; //원본파일이름
    private String sfile; //수정파일이름
    private String pass; //글 비밀번호
    private int visitcount; //조회 횟수
    private java.time.LocalDateTime performance_datetime;
    private int price;
    private String grade;
    private String performance_name;
	
    public String getPerformance_name() {
		return performance_name;
	}
	public void setPerformance_name(String performance_name) {
		this.performance_name = performance_name;
	}
	public String getSale_title() {
		return Sale_title;
	}
	public void setSale_title(String sale_title) {
		Sale_title = sale_title;
	}
	public String getSale_content() {
		return Sale_content;
	}
	public void setSale_content(String sale_content) {
		Sale_content = sale_content;
	}
	public java.time.LocalDateTime getPerformance_datetime() {
		return performance_datetime;
	}
	public void setPerformance_datetime(java.time.LocalDateTime performance_datetime) {
		this.performance_datetime = performance_datetime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public java.sql.Date getPostdate() {
		return postdate;
	}
	public void setPostdate(java.sql.Date postdate) {
		this.postdate = postdate;
	}
	public String getOfile() {
		return ofile;
	}
	public void setOfile(String ofile) {
		this.ofile = ofile;
	}
	public String getSfile() {
		return sfile;
	}
	public void setSfile(String sfile) {
		this.sfile = sfile;
	}
	
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public int getVisitcount() {
		return visitcount;
	}
	public void setVisitcount(int visitcount) {
		this.visitcount = visitcount;
	}
	
    
    
}
