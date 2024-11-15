package model1.board;

import java.sql.Date;

//테이블 board 의 데이터를 전달하기 위함
//화면 게시글 리스트를 보여주기 위함
public class BoardDTO {
	private String num;
	private String title;
	private String content;
	private String id;
	private Date postdate;
	private String visitcount;
	private String name; //유저이름 (member 테이블과 조인해서 가져올 수 있음)
	
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getPostdate() {
		return postdate;
	}
	public void setPostdate(Date postdate) {
		this.postdate = postdate;
	}
	public String getVisitcount() {
		return visitcount;
	}
	public void setVisitcount(String visitcount) {
		this.visitcount = visitcount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
