package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.AdminDto;
import com.dto.MemberDto;
import com.dto.ReportDto;
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
	
	

	public MemberDto searchAllMember(String tablename, String fieldname, String key) {
		MemberDto mdto = new MemberDto();
		String sql = "select count(*) as cnt from " + tablename + " where " + fieldname + " like '%'||?||'%' ";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			if(rs.next())
				mdto = new MemberDto();
				mdto.setName(rs.getString("name"));
				mdto.setUserid(rs.getString("userid"));
		} catch (SQLException e) {	e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);		}
		return mdto;
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



	public int getAllCount() {
		int allCount = 0;
		String sql = "select count(*) as count from report";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) allCount = rs.getInt("count");
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return allCount;
	}



	public int getOrderCount(String type) {
		int orderCount = 0;
		String sql = "select count(*) as count from report where report_type=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			if(rs.next()) orderCount = rs.getInt("count");
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return orderCount;
	}



	public ReportDto getReport(int report_num) {
		ReportDto rdto = null;
		String sql = "select * from report where report_num="+report_num;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rdto = new ReportDto();
				rdto.setIndate(rs.getTimestamp("indate"));
				rdto.setPost_num(rs.getInt("post_num"));
				rdto.setReason(rs.getString("reason"));
				rdto.setReport_num(rs.getInt("report_num"));
				rdto.setReported_id(rs.getString("reported_id"));
				rdto.setHandled(rs.getString("handled"));
				rdto.setReporter_id(rs.getString("reporter_id"));
				rdto.setStory_num(rs.getInt("story_num"));
				rdto.setType(rs.getString("report_type"));
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return rdto;
	}



	public int reportDone(int report_num) {
		String sql = "update report set handled='y' where report_num=" + report_num;
		int result = 0;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return result;
	}

	public void reportSameUser(String userid) {
		String sql = "update report set handled='y' where reported_id=? and report_type='user'";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}



	public int getSort(String type) {
		int result = 0;
		String sql = "select count(*) as count from report where report_type=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, type);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count");
			}	
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		
		return result;
	}

	


	

	//신고 차단 체크
	
}