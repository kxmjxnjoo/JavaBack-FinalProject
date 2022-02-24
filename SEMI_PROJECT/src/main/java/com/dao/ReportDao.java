package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.QnaDto;
import com.dto.ReportDto;
import com.util.Dbman;
import com.util.Paging;

public class ReportDao {
	
	private ReportDao() {}
	private static ReportDao instance = new ReportDao();
	public static ReportDao getInstance() {
		return instance;
	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	
	public ArrayList<ReportDto> reportList(Paging paging, String key) {
		ArrayList<ReportDto> list = new ArrayList<ReportDto>();
		String sql = "select * from (select * from (select rownum as rn, m.* from "
				+ " ((select * from report where reporter_id like '%'||?||'%' or reported_id like '%'||?||'%' order by indate desc) m) "
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
				ReportDto rdto = new ReportDto();
		    	rdto.setReporter_id(rs.getString("reporter_id"));
		    	rdto.setReported_id(rs.getString("reported_id"));
		    	rdto.setPost_num(rs.getInt("post_num"));
		    	rdto.setStory_num(rs.getInt("story_num"));
		    	rdto.setIndate(rs.getTimestamp("indate"));
		    	rdto.setReason(rs.getString("reason"));
		    	rdto.setReport_num(rs.getInt("report_num"));
		    	rdto.setType(rs.getString("report_type"));
		    	list.add(rdto);
		    }
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);  }
		return list;
	}



	public ArrayList<ReportDto> reportListAsc(Paging paging, String key) {
		ArrayList<ReportDto> list = new ArrayList<ReportDto>();
		String sql = "select * from (select * from (select rownum as rn, m.* from "
				+ " ((select * from report where reporter_id like '%'||?||'%' or reported_id like '%'||?||'%' order by indate) m) "
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
				ReportDto rdto = new ReportDto();
		    	rdto.setReporter_id(rs.getString("reporter_id"));
		    	rdto.setReported_id(rs.getString("reported_id"));
		    	rdto.setPost_num(rs.getInt("post_num"));
		    	rdto.setStory_num(rs.getInt("story_num"));
		    	rdto.setIndate(rs.getTimestamp("indate"));
		    	rdto.setReason(rs.getString("reason"));
		    	rdto.setReport_num(rs.getInt("report_num"));
		    	rdto.setType(rs.getString("report_type"));
		    	list.add(rdto);
		    }
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);  }
		return list;
	}



	public ArrayList<ReportDto> reportList(Paging paging, String key, String type) {
		ArrayList<ReportDto> list = new ArrayList<ReportDto>();
		String sql = "select * from ("
				+ " select * from (select * from (select rownum as rn, m.* from "
				+ " ((select * from report where reporter_id like '%'||?||'%' or reported_id like '%'||?||'%' order by indate desc) m) "
				+ " ) where rn>=?) where rn<=? ) where report_type=?"  ;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, key);
			pstmt.setString(2, key);
			pstmt.setInt(3, paging.getStartNum());
			pstmt.setInt(4, paging.getEndNum());
			pstmt.setString(5, type);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReportDto rdto = new ReportDto();
		    	rdto.setReporter_id(rs.getString("reporter_id"));
		    	rdto.setReported_id(rs.getString("reported_id"));
		    	rdto.setPost_num(rs.getInt("post_num"));
		    	rdto.setStory_num(rs.getInt("story_num"));
		    	rdto.setIndate(rs.getTimestamp("indate"));
		    	rdto.setReason(rs.getString("reason"));
		    	rdto.setReport_num(rs.getInt("report_num"));
		    	rdto.setType(rs.getString("report_type"));
		    	list.add(rdto);
		    }
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);  }
		return list;
	}
	
	
	
	
}
