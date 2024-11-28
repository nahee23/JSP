package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import board.SaleDTO;
import common.DBConnection;

public class SaleDAOImpl implements SaleDAO{
    
    Connection con = null;
	ResultSet rs = null;
	Statement stmt = null;
	PreparedStatement psmt = null;
	
	@Override
	public List<SaleDTO> get() {
		List<SaleDTO> Salelist = null;
		SaleDTO sale = null;
		
		try {			
			Salelist = new ArrayList<SaleDTO>();
			String sql = "SELECT * FROM saleticket ORDER BY postdate DESC";
			con = DBConnection.openConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				sale = new SaleDTO();
				sale.setIdx(rs.getInt("idx"));
				sale.setName(rs.getString("name"));
				sale.setSale_title(rs.getString("Sale_title"));
				sale.setSale_content(rs.getString("Sale_content"));
				sale.setPostdate(rs.getDate("postdate"));
				sale.setOfile(rs.getString("ofile"));
				sale.setSfile(rs.getString("sfile"));
				sale.setPass(rs.getString("pass"));
				sale.setVisitcount(rs.getInt("visitcount"));
				sale.setPerformance_datetime(rs.getTimestamp("performance_datetime").toLocalDateTime());
				sale.setPrice(rs.getInt("price"));
				sale.setGrade(rs.getString("grade"));
				sale.setPerformance_name(rs.getString("performance_name"));
				
				Salelist.add(sale);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return Salelist;
}

	@Override
	public SaleDTO get(String idx) {
		SaleDTO sale = null;
		try {
			sale = new SaleDTO();
			String sql = "SELECT * FROM saleticket where idx="+ idx;
			con = DBConnection.openConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				sale.setIdx(rs.getInt("idx"));
				sale.setName(rs.getString("name"));
				sale.setSale_title(rs.getString("Sale_title"));
				sale.setSale_content(rs.getString("Sale_content"));
				sale.setPostdate(rs.getDate("postdate"));
				sale.setOfile(rs.getString("ofile"));
				sale.setSfile(rs.getString("sfile"));
				sale.setPass(rs.getString("pass"));
				sale.setVisitcount(rs.getInt("visitcount"));
				sale.setPerformance_datetime(rs.getTimestamp("performance_datetime").toLocalDateTime());
				sale.setPrice(rs.getInt("price"));
				sale.setGrade(rs.getString("grade"));
				sale.setPerformance_name(rs.getString("performance_name"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return sale;
}

	@Override
	public boolean save(SaleDTO e) {
		boolean flag = false;
		try {
			String sql = "INSERT INTO saleticket(name, Sale_title, Sale_content,ofile,sfile, pass, performance_datetime, price, grade, performance_name) VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?)";
			con = DBConnection.openConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, e.getName() );
			psmt.setString(2, e.getSale_title() );
			psmt.setString(3, e.getSale_content() );
			psmt.setString(4, e.getOfile() );
			psmt.setString(5, e.getSfile() );
			psmt.setString(6, e.getPass() );
			psmt.setTimestamp(7, Timestamp.valueOf(e.getPerformance_datetime()));
			psmt.setInt(8, e.getPrice() );
			psmt.setString(9, e.getGrade() );
			psmt.setString(10, e.getPerformance_name() );
			
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
			String sql = "DELETE FROM saleticket where idx="+idx;
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
	public boolean update(SaleDTO sale) {
		boolean flag = false;
		try {
			String sql = "UPDATE saleticket SET name = ?, Sale_title = ?, Sale_content = ?, ofile = ? , sfile = ?, performance_datetime = ?, price =?, grade = ?,performance_name =? where idx= ?";
			con = DBConnection.openConnection();
			psmt = con.prepareStatement(sql);
			
			psmt = con.prepareStatement(sql);
			psmt.setString(1, sale.getName() );
			psmt.setString(2, sale.getSale_title() );
			psmt.setString(3, sale.getSale_content() );
			psmt.setString(4, sale.getOfile() );
			psmt.setString(5, sale.getSfile() );
			psmt.setTimestamp(6, Timestamp.valueOf(sale.getPerformance_datetime()));
			psmt.setInt(7, sale.getPrice() );
			psmt.setString(8, sale.getGrade() );
			psmt.setString(9, sale.getPerformance_name());
			psmt.setInt(10, sale.getIdx()); 
			
			psmt.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

    // 주어진 일련번호에 해당하는 게시물의 조회수를 1 증가시킵니다.
    public void updateVisitCount(String idx) {
        String query = "UPDATE saleticket SET "
                     + " visitcount=visitcount+1 "
                     + " WHERE idx=?"; 
        try {
        	con = DBConnection.openConnection();
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            psmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("게시물 조회수 증가 중 예외 발생");
            e.printStackTrace();
        }
    }

    
    // 입력한 비밀번호가 지정한 일련번호의 게시물의 비밀번호와 일치하는지 확인합니다.
    public boolean confirmPassword(String pass, String idx) {
        boolean isCorr = true;
        try {
            String sql = "SELECT COUNT(*) FROM saleticket WHERE pass=? AND idx=?";
            con = DBConnection.openConnection();
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

