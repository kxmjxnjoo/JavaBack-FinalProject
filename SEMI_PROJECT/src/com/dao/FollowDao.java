package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dto.FollowDto;
import com.util.Dbman;

public class FollowDao {
	// Singleton
	private FollowDao() {};
	private static FollowDao itc = new FollowDao();
	public static FollowDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<FollowDto> getFollowing(String userid) {
		ArrayList<FollowDto> list = null;
		String sql = "select * from follow where follower=?";
		
		con = Dbman.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			int count = 0;
			
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<FollowDto>();
				}
				FollowDto fdto = new FollowDto();
				fdto.setFollower(rs.getString("follower"));
				fdto.setFollowing(rs.getString("following"));
				fdto.setFollowNum(rs.getInt("follow_num"));
				list.add(fdto);
				
				count++;
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}

	public int isFollowing(String follower, String following) {
		int result = 0;
		String sql = "select * from follow where following=? and follower=?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, following);
			pstmt.setString(2, follower);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = 1;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return result;
	}
	
	public int insertFollow(String follower, String following) {
		int result = 0;
		String sql = "insert into follow (follower, following) values(?, ?)";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, follower);
			pstmt.setString(2, following);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return result;
	}
	
	public int deleteFollow(String follower, String following) {
		int result = 0;
		String sql = "delete from follow where follower=? and following=?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, follower);
			pstmt.setString(2, following);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return result;
	}
	
}
