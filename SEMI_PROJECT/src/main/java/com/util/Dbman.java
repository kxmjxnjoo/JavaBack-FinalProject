package com.util;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class Dbman {
	public static Connection getConnection() {
		Connection con = null;
		
		Context initContext;
		try { 
			//서버의 DB에 연결객체를 관리하고 있는 DataSource에서 연결을 얻어옴
			initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			con = ds.getConnection();
		} catch (NamingException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		}
		return con;
	}
	
	public static Connection getMySqlConnection() {
		Connection con = null;
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/jinkparkDB";
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, "scott", "tiger");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close (Connection con, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(con!=null) con.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
