package board;

public class BoardDTO {
	// 멤버 변수 선언
    private String idx; //일련번호
    private String name; //작성자 이름
    private String title; //글제목
    private String content; //글내용
    private java.sql.Date postdate; //날짜
    private String ofile; //원본파일이름
    private String sfile; //수정파일이름
    private int downcount; //다운로드 횟수
    private String pass; //글 비밀번호
    private int visitcount; //조회 횟수
}
