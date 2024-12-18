package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletContext;

/* DB 연결관리 클래스 */
public class JDBConnect {
    public Connection con; //DB 연결 객체
    public Statement stmt; //정적쿼리문 만들때 (변수없음) "select * from 테이블"
    public PreparedStatement psmt;  //동적쿼리문 (변수 입력가능) "where id=변수"
    public ResultSet rs;	// 결과 객체 (select 문 일때)

    // 기본 생성자
    public JDBConnect() {
        try {
            // JDBC 드라이버 로드
            Class.forName("oracle.jdbc.OracleDriver");

            // DB에 연결
            String url = "jdbc:oracle:thin:@localhost:1521:xe";  
            String id = "musthave";
            String pwd = "1234"; 
            con = DriverManager.getConnection(url, id, pwd); 

            System.out.println("DB 연결 성공(기본 생성자)");
        }
        catch (Exception e) {            
            e.printStackTrace();
        }
    }

    // 두 번째 생성자
    public JDBConnect(String driver, String url, String id, String pwd) {
        try {
            // JDBC 드라이버 로드
            Class.forName(driver);  

            // DB에 연결
            con = DriverManager.getConnection(url, id, pwd);

            System.out.println("DB 연결 성공(인수 생성자 1)");
        }
        catch (Exception e) {            
            e.printStackTrace();
        }
    }

    // 세 번째 생성자
    public JDBConnect(ServletContext application) {
        try {
            // JDBC 드라이버 로드
            String driver = application.getInitParameter("OracleDriver"); 
            Class.forName(driver); 

            // DB에 연결
            String url = application.getInitParameter("OracleURL"); 
            String id = application.getInitParameter("OracleId");
            String pwd = application.getInitParameter("OraclePwd");
            con = DriverManager.getConnection(url, id, pwd);

            System.out.println("DB 연결 성공(인수 생성자 2)"); 
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 연결 해제(자원 반납)
    public void close() { 
        try {            
            if (rs != null) rs.close(); 
            if (stmt != null) stmt.close();
            if (psmt != null) psmt.close();
            if (con != null) con.close(); 

            System.out.println("JDBC 자원 해제");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}