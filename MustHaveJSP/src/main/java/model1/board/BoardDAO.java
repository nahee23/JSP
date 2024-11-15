package model1.board;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import common.JDBConnect;
import jakarta.servlet.ServletContext;

public class BoardDAO extends JDBConnect {
	
	public BoardDAO(ServletContext application) {
		super(application);
	}
	
	//게시물 갯수 세기(검색한 게시물의 숫자, 100개 /10 페이지 계산)
	public int selectCount(Map<String, Object> map) { //Map = 검색어
		int totalCount = 0; //게시물 갯수 변수
		
		String query = "SELECT COUNT(*) FROM board";
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next(); //select 쿼리 결과의 1번째 행
			totalCount = rs.getInt(1); //1번째 열의 값
		} catch (Exception e) {
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	} 
	//게시물 목록 가져오기
	public List<BoardDTO> selectList(Map<String, Object> map) {
		List<BoardDTO> bbs = new ArrayList<BoardDTO>();
		
		String query = "SELECT * FROM board";
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%'";
		}
		query += " ORDER BY num DESC "; //내림차순으로 
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto);
			}
		} catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return bbs;
	}
	
	//게시물 목록 가져오기(페이징)
		public List<BoardDTO> selectListPage(Map<String, Object> map) {
			List<BoardDTO> bbs = new ArrayList<BoardDTO>();
			
			String query = "SELECT * FROM "
					+ "(SELECT ROWNUM AS RN, TB.* FROM (SELECT * FROM board";
			if(map.get("searchWord") != null) {
				query += " WHERE " + map.get("searchField") + " "
						+ " LIKE '%" + map.get("searchWord") + "%'";
			}
			query += " ORDER BY num DESC) TB ) "
					+ "WHERE RN BETWEEN ? AND ? ";  
			
			try {
				psmt = con.prepareStatement(query);
				psmt.setString(1, map.get("start").toString());
				psmt.setString(2, map.get("end").toString());
				
				rs = psmt.executeQuery();
				
				while (rs.next()) {
					
					BoardDTO dto = new BoardDTO();
					
					dto.setNum(rs.getString("num"));
					dto.setTitle(rs.getString("title"));
					dto.setContent(rs.getString("content"));
					dto.setPostdate(rs.getDate("postdate"));
					dto.setId(rs.getString("id"));
					dto.setVisitcount(rs.getString("visitcount"));
					
					bbs.add(dto);
				}
			} catch (Exception e) {
				System.out.println("게시물 조회 중 예외 발생");
				e.printStackTrace();
			}
			
			return bbs;
		}
	
	//게시글 데이터를 받아서 DB에 추가
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		
		try {
			String query =  "INSERT INTO board (num,title,content,id,visitcount) "
					+ "VALUES(seq_board_num.NEXTVAL, ?, ?, ?, 0)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getId());
			
			result = psmt.executeUpdate(); //입력,수정,삭제시 리턴값은 행의 숫자
		} catch (Exception e){
			System.out.println("게시물 입력중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	//글번호로 게시물을 찾아서 내용 반환
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();
		
		String query = "SELECT B.*, M.name"
				+ " FROM board B JOIN member M"
				+ " ON B.id = M.id"
				+ " WHERE num = ?" ;
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1,num);
			rs = psmt.executeQuery();
			
			if (rs.next()) {
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				dto.setName(rs.getString("name"));
			}
			
		} catch (Exception e) {
			System.out.println("게시물 상세보기중 예외 발생");
			e.printStackTrace();
		}
		return dto;	
	}
	//게시글 클릭시 조회수 증가 +1
	public void updateVisitCount(String num) {
		String query = "UPDATE board SET visitcount=visitcount+1"
				+" WHERE num = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, num);
			psmt.executeQuery();
			
		} catch (Exception e) {
			System.out.println("게시물 조회수 증가 예외 발생");
			e.printStackTrace();
		}
	}
	
	// 지정한 게시물 수정
	public int updateEdit(BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "UPDATE board SET "
					+ "title=?, content=? "
					+ "WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시물 수정중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	//지정된 계시글을 삭제
	public int deletePost (BoardDTO dto) {
		int result = 0;
		
		try {
			String query = "DELETE FROM board WHERE num=?";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getNum());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("게시글 삭제 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
}
