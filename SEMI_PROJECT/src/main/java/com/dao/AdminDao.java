package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.AdminDto;
import com.dto.MemberDto;
import com.util.Dbman;
import com.util.Paging;

public class AdminDao {

	private AdminDao() {}
	private static AdminDao instance = new AdminDao();
	public static AdminDao getInstance() {
		return instance;
	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	
	public AdminDto adminCheck(String adminid) {
		AdminDto adto = null;
		String sql = "select * from admin where adminid=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, adminid);
			rs = pstmt.executeQuery();
			if( rs.next() ) {
			adto = new AdminDto();
			adto.setAdminid( rs.getString("adminid"));
			adto.setPassword( rs.getString("password"));
			/*
			 * adto.setName( rs.getString("name")); 
			 * adto.setEmail( rs.getString("eamil"));
			 * adto.setPhone( rs.getString("phone"));
			 */
			}
		} catch (SQLException e) {	e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);	}
		return adto;
	}
	
	

	public int searchAllMember(String tablename, String fieldname, String key) {
		int count = 0;
		String sql = "select count(*) as cnt from " + tablename + " where " + fieldname + " like '%'||?||'%' ";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			if(rs.next())
				 count = rs.getInt("cnt");
		} catch (SQLException e) {	e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);		}
		return count;
	}



	public ArrayList<MemberDto> MemberList(Paging paging, String key) {
		ArrayList<MemberDto> list = new ArrayList<MemberDto>();
		String sql = "select * from (select * from (select rownum as rn, m.* from "
				+ " ((select * from member where userid like '%'||?||'%' or name like '%'||?||'%' order by indate desc) m) "
				+ " ) where rn>=?) where rn<=?";
			
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setString(2, key);
			
			pstmt.setInt(3, paging.getStartNum());
			pstmt.setInt(4, paging.getEndNum());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDto mdto = new MemberDto();
				
				mdto.setUserid(rs.getString("userid"));
				mdto.setPassword(rs.getString("password"));
				mdto.setName(rs.getString("name"));
				mdto.setEmail(rs.getString("email"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setUseyn(rs.getString("useyn"));
				mdto.setIntroduce(rs.getString("introduce"));
				mdto.setIndate(rs.getDate("indate"));
				
				list.add(mdto);
			}
			
		} catch (SQLException e) {	e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);		}
		return list;
	}


	//신고 차단 체크
	
}