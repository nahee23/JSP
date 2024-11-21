package membership;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import membership.MemberDTO;
import common.DBConnection;

public class LoginDAOImpl implements LoginDAO{

	@Override
	public String loginCheck(MemberDTO loginBean) {
String query="select * from MEMBER where ID=? and password=?";
		
		try{
			Connection con= DBConnection.openConnection();
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, loginBean.getId() );
			ps.setString(2, loginBean.getPassword() );
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				return "true";
			}
			else{
				return "false";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return "error";

	}

}
