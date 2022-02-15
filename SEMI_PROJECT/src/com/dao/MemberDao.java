package com.ezen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ezen.dto.MemberDto;
import com.ezen.util.Dbman;

public class MemberDao {
	private MemberDao() {}
	private static MemberDao itc = new MemberDao();
	public static MemberDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MemberDto getMember(String userid) {
		MemberDto mdto = null;
		String sql = "select * from member where userid=?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mdto = new MemberDto();
				mdto.setEmail(rs.getString("email"));
				mdto.setImg(rs.getString("img"));
				mdto.setIndate(rs.getDate("indate"));
				mdto.setIntroduce(rs.getString("introduce"));
				mdto.setName(rs.getString("name"));
				mdto.setPassword(rs.getString("password"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setUserid(rs.getString("userid"));
				mdto.setUseyn(rs.getString("useyn"));
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return mdto;
	}
}
