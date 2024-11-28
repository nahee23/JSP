package board;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import common.DBConnection;

public class CommentDAO {
	Connection con = null;
	ResultSet rs = null;
	Statement stmt = null;
	PreparedStatement psmt = null;
	
	// 댓글 추가 메서드
    public void addComment(CommentDTO commentDto) {
        String sql = "INSERT INTO comments (idx, name, text, createdAt) VALUES (?, ?, ?, NOW())";
        try {con = DBConnection.openConnection();
    			psmt = con.prepareStatement(sql);
        		
    			psmt.setInt(1, commentDto.getIdx());  // 게시글 ID
    			psmt.setString(2, commentDto.getName()); // 댓글 작성자
    			psmt.setString(3, commentDto.getText()); // 댓글 내용
    			psmt.executeUpdate();  // SQL 실행
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리
        }
    }

    // 특정 게시글의 댓글 목록 조회 메서드
    public List<CommentDTO> getCommentsForPost(int idx) {
        List<CommentDTO> comments = new ArrayList<>();
        String sql = "SELECT * FROM comments WHERE idx=? ORDER BY createdAt DESC";
        try { 	con = DBConnection.openConnection();
        		psmt = con.prepareStatement(sql);
        		
        		psmt.setInt(1, idx); // 게시글 ID로 댓글 조회
    			rs = psmt.executeQuery();
                       
            while (rs.next()) {
            	CommentDTO commentDto = new CommentDTO();
                commentDto.setIdx(rs.getInt("idx"));
                commentDto.setName(rs.getString("name"));
                commentDto.setText(rs.getString("text"));
                commentDto.setCreatedAt(rs.getTimestamp("createdAt"));
                comments.add(commentDto);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리
        } 
        return comments;
    }

    // 댓글 삭제 메서드 (옵션)
    public void deleteComment(int id) {
        String sql = "DELETE FROM comments WHERE id = ?";
        try { 	con = DBConnection.openConnection();
				psmt = con.prepareStatement(sql);
				psmt.setInt(1, id); // 댓글 ID로 삭제
				psmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // 예외 처리
        }
    }
}



