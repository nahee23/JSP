package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.DBConnection;

public class BoardDAOImpl implements BoardDAO{
    
    Connection con = null;
	ResultSet rs = null;
	Statement stmt = null;
	PreparedStatement psmt = null;
	
	@Override
	public List<BoardDTO> get() {
		List<BoardDTO> Boardlist = null;
		BoardDTO board = null;
		
		try {			
			Boardlist = new ArrayList<BoardDTO>();
			String sql = "SELECT * FROM freeboard";
			con = DBConnection.openConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				board = new BoardDTO();
				board.setIdx(rs.getInt("Idx"));
				board.setName(rs.getString("name"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setPostdate(rs.getDate("postdate"));
				board.setOfile(rs.getString("ofile"));
				board.setSfile(rs.getString("sfile"));
				board.setDowncount(rs.getInt("downcount"));
				board.setPass(rs.getString("pass"));
				board.setVisitcount(rs.getInt("visitcount"));
				board.setRecommendcount(rs.getInt("recommendcount"));
				
				Boardlist.add(board);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return Boardlist;
}

	@Override
	public BoardDTO get(int idx) {
		BoardDTO board = null;
		try {
			board = new BoardDTO();
			String sql = "SELECT * FROM freeboard where idx="+ idx;
			con = DBConnection.openConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				board.setIdx(rs.getInt("Idx"));
				board.setName(rs.getString("name"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setPostdate(rs.getDate("postdate"));
				board.setOfile(rs.getString("ofile"));
				board.setSfile(rs.getString("sfile"));
				board.setDowncount(rs.getInt("downcount"));
				board.setPass(rs.getString("pass"));
				board.setVisitcount(rs.getInt("visitcount"));
				board.setRecommendcount(rs.getInt("recommendcount"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return board;
}

	@Override
	public boolean save(BoardDTO e) {
		boolean flag = false;
		try {
			String sql = "INSERT INTO freeboard(idx,name, title, content,ofile,sfile, pass) VALUES"
					+ "(?,?,?,?,?,?,?)";
			con = DBConnection.openConnection();
			psmt = con.prepareStatement(sql);
			psmt.setInt(1, e.getIdx() );
			psmt.setString(2, e.getName() );
			psmt.setString(3, e.getTitle() );
			psmt.setString(4, e.getContent() );
			psmt.setString(5, e.getOfile() );
			psmt.setString(6, e.getSfile() );
			psmt.setString(7, e.getPass() );
			
			psmt.executeUpdate();
			flag = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
}

	@Override
	public boolean delete(int idx) {
		boolean flag = false;
		try {
			String sql = "DELETE FROM MEMBER where idx="+idx;
			con = DBConnection.openConnection();
			psmt = con.prepareStatement(sql);
			psmt.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
}

	@Override
	public boolean update(BoardDTO board) {
		boolean flag = false;
		try {
			String sql = "UPDATE MEMBER SET name = ?, title = ?, content = ?, ofile = ? , sfile = ? where id= ?";
			con = DBConnection.openConnection();
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, board.getName() );
			psmt.setString(2, board.getTitle() );
			psmt.setString(3, board.getContent() );
			psmt.setString(4, board.getOfile() );
			psmt.setString(5, board.getSfile() );
			psmt.setInt(6, board.getIdx()); 
			
			psmt.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

    // 주어진 일련번호에 해당하는 게시물의 조회수를 1 증가시킵니다.
    public void updateVisitCount(String idx) {
        String query = "UPDATE freeboard SET "
                     + " visitcount=visitcount+1 "
                     + " WHERE idx=?"; 
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            psmt.executeQuery();
        }
        catch (Exception e) {
            System.out.println("게시물 조회수 증가 중 예외 발생");
            e.printStackTrace();
        }
    }

    // 다운로드 횟수를 1 증가시킵니다.
    public void downCountPlus(String idx) {
        String sql = "UPDATE freeboard SET "
                + " downcount=downcount+1 "
                + " WHERE idx=? "; 
        try {
            psmt = con.prepareStatement(sql);
            psmt.setString(1, idx);
            psmt.executeUpdate();
        }
        catch (Exception e) {}
    }
 // 다운로드 횟수를 1 증가시킵니다.
    public void recommendCountPlus(String idx) {
        String sql = "UPDATE freeboard SET "
                + " recommendcount=recommendcount+1 "
                + " WHERE idx=? "; 
        try {
            psmt = con.prepareStatement(sql);
            psmt.setString(1, idx);
            psmt.executeUpdate();
        }
        catch (Exception e) {}
    }
    // 입력한 비밀번호가 지정한 일련번호의 게시물의 비밀번호와 일치하는지 확인합니다.
    public boolean confirmPassword(String pass, String idx) {
        boolean isCorr = true;
        try {
            String sql = "SELECT COUNT(*) FROM freeboard WHERE pass=? AND idx=?";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, pass);
            psmt.setString(2, idx);
            rs = psmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                isCorr = false;
            }
        }
        catch (Exception e) {
            isCorr = false;
            e.printStackTrace();
        }
        return isCorr;
    }
}

