package model2.mvcboard;

public class MVCBoardDTO {
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

    // 게터/세터
    public String getIdx() { 
        return idx;
    }
    public void setIdx(String idx) {
        this.idx = idx;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
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
    public int getDowncount() {
        return downcount;
    }
    public void setDowncount(int downcount) {
        this.downcount = downcount;
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