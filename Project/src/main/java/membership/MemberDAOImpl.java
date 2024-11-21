package membership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import membership.MemberDTO;
import common.DBConnection;

public class MemberDAOImpl implements MemberDAO {

	Connection con = null;
	ResultSet rs = null;
	Statement stmt = null;
	PreparedStatement psmt = null;
	
	@Override
	public List<MemberDTO> get() {
		List<MemberDTO> list = null;
		MemberDTO member = null;
		
		try {			
			list = new ArrayList<MemberDTO>();
			String sql = "SELECT * FROM MEMBER";
			con = DBConnection.openConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				member = new MemberDTO();
				member.setIdx(rs.getInt("Idx"));
				member.setId(rs.getString("Id"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setBirth(rs.getString("birth"));
				member.setCreated_At(rs.getString("created_at"));
				member.setUpdated_At(rs.getString("updated_at"));
				list.add(member);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
}

	@Override
	public MemberDTO get(int id) {
		MemberDTO member = null;
		try {
			member = new MemberDTO();
			String sql = "SELECT * FROM MEMBER where id="+ id;
			con = DBConnection.openConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				member.setIdx(rs.getInt("Idx"));
				member.setId(rs.getString("Id"));
				member.setName(rs.getString("name"));
				member.setEmail(rs.getString("email"));
				member.setBirth(rs.getString("birth"));
				member.setCreated_At(rs.getString("created_at"));
				member.setUpdated_At(rs.getString("updated_at"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return member;
}

	@Override
	public boolean save(MemberDTO e) {
		boolean flag = false;
		try {
			String sql = "INSERT INTO MEMBER(id,name, email, password,birth) VALUES"
					+ "(?,?,?,?,?)";
			con = DBConnection.openConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, e.getId() );
			psmt.setString(2, e.getName() );
			psmt.setString(3, e.getEmail() );
			psmt.setString(4, e.getPassword() );
			psmt.setString(5, e.getBirth() );			
			
			psmt.executeUpdate();
			flag = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return flag;
}

	@Override
	public boolean delete(int id) {
		boolean flag = false;
		try {
			String sql = "DELETE FROM MEMBER where id="+id;
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
	public boolean update(MemberDTO member) {
		boolean flag = false;
		try {
			String sql = "UPDATE MEMBER SET name = ?, email = ?, birth = ? where id="+member.getId();
			con = DBConnection.openConnection();
			psmt = con.prepareStatement(sql);
			
			psmt.setString(1, member.getName() );
			psmt.setString(2, member.getEmail() );
			psmt.setString(3, member.getBirth() );
			
			psmt.executeUpdate();
			flag = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
