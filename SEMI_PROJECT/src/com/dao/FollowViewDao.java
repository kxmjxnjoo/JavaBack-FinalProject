package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dto.FollowViewDto;
import com.util.Dbman;

public class FollowViewDao {
	// Singleton
	private FollowViewDao() {}
	private static FollowViewDao itc = new FollowViewDao();
	public static FollowViewDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<FollowViewDto> getFollowing(String userid) {
		ArrayList<FollowViewDto> list = null;
		String sql = "select * from follow_view where follower=?";
		
		con = Dbman.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<FollowViewDto>();
					count++;
				}
				FollowViewDto fdto = new FollowViewDto();
				fdto.setFollowing(rs.getString("following"));
				fdto.setFollower(rs.getString("follower"));
				fdto.setFollowingName(rs.getString("followingName"));
				fdto.setFollowingImg(rs.getString("followingImg"));
				list.add(fdto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}
}
