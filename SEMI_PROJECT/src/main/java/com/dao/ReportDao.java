package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.QnaDto;
import com.dto.ReportDto;
import com.util.Dbman;

public class ReportDao {
	
	private ReportDao() {}
	private static ReportDao instance = new ReportDao();
	public static ReportDao getInstance() {
		return instance;
	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	
	public ArrayList<ReportDto> reportList(int report_num) {
		ArrayList<ReportDto> list = new ArrayList<ReportDto>();
		String sql = "select * from report where report_num=? order by indate desc";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  report_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReportDto rdto = new ReportDto();
		    	rdto.setReporter_id(rs.getString("reporter_id"));
		    	rdto.setReported_id(rs.getString("reported_id"));
		    	rdto.setPost_num(rs.getInt("post_num"));
		    	rdto.setStory_num(rs.getInt("story_num"));
		    	rdto.setIndate(rs.getTimestamp("date"));
		    	rdto.setReason(rs.getString("reason"));
		    	rdto.setReport_num(rs.getInt("report_num"));
		    	list.add(rdto);
		    }
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);  }
		return list;
	}
	
	
	
	
}
