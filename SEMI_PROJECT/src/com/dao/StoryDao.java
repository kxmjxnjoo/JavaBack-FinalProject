package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dto.PostDto;
import com.dto.StoryDto;
import com.util.Dbman;

public class StoryDao {
	private StoryDao() {}
	private static StoryDao itc = new StoryDao();
	public static StoryDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void uploadStory(StoryDto sdto) {
		String sql = "insert into story(story_num, img, story_content, userid) "
				+ " values(story_seq.nextval, ?, ?, ?)";
	    con = Dbman.getConnection();
	        try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, sdto.getStory_img());
				pstmt.setString(2, sdto.getContent());
				pstmt.setString(3, sdto.getUserid());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { Dbman.close(con, pstmt, rs); }
	}

	public int get_story_num(String userid) {
	   int story_num = 0;
	   String sql = "select max(story_num) as max from story group by userid having userid='" + userid + "'";
    	con = Dbman.getConnection();
	    try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				story_num = rs.getInt("max");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
        return story_num;
	}

	public StoryDto getStory(int story_num) {
			String sql = "select * from story_view where story_num = ?";
			StoryDto sdto = null;
			con = Dbman.getConnection();
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, story_num);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					sdto = new StoryDto();
					sdto.setUserid(rs.getString("userid"));
					sdto.setUser_img(rs.getString("user_img"));
					sdto.setContent(rs.getString("story_content"));
					sdto.setStory_img(rs.getString("story_img"));
					sdto.setCreate_date(rs.getTimestamp("create_date"));
				}
			} catch (SQLException e) { e.printStackTrace();
			} finally { Dbman.close(con, pstmt, rs); }
			return sdto;
	}

	public int storyLikeCheck(int story_num, String userid) {
		String sql = "select ? from story_like where story_num = ?";
		int result = 0;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, story_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return result;
	}

	public void addStoryLike(int story_num, String userid) {
		String sql = "insert into story_like values(?, ?)";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, story_num);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}

	public void deleteStoryLike(int story_num, String userid) {
		String sql = "delete story_like where story_num =? and userid=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, story_num);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}

	public int searchPrevStory(int story_num, String userid) {
		int prev = 0;
		String sql = "select max(story_num) as prev from story_view where story_num < ? group by userid having userid=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, story_num);
			pstmt.setString(2, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				prev = rs.getInt("prev");
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return prev;
	}

	public int searchNextStory(int story_num, String userid) {
		int next = 0;
		String sql = "select min(story_num) as next from story_view where story_num > ? group by userid having userid=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, story_num);
			pstmt.setString(2, userid);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				next = rs.getInt("next");
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return next;
	}

	public void deleteStory(int story_num) {
		String sql = "delete story where story_num="+story_num;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}

	public void editPost(StoryDto sdto) {
		String sql = "update story set img=?, story_content=? where story_num=" + sdto.getStory_num();
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sdto.getStory_img());
			pstmt.setString(2, sdto.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}
}
