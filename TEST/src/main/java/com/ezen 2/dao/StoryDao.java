package com.ezen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ezen.dto.StoryDto;
import com.ezen.util.Dbman;

public class StoryDao {
	private StoryDao() {}
	private static StoryDao itc = new StoryDao();
	public static StoryDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<StoryDto> getStories(String followingId) {
		ArrayList<StoryDto> list = null;
		String sql = "select * from story where userid=?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, followingId);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<StoryDto>();
				}
				StoryDto sdto = new StoryDto();
				sdto.setCreateDate(rs.getDate("create_date"));
				sdto.setImg(rs.getString("img"));
				sdto.setStoryNum(rs.getInt("story_num"));
				sdto.setUserid(rs.getString("userid"));
				list.add(sdto);
				
				count++;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	
}
