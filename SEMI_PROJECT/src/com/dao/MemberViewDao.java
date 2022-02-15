package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ezen.dto.MemberViewDto;
import com.ezen.util.Dbman;

public class MemberViewDao {
	private MemberViewDao() {}
	private static MemberViewDao itc = new MemberViewDao();
	public static MemberViewDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MemberViewDto getMember(String userid) {
		String memberSql = "select * from member where userid=?";
		String postSql = "select count(post_num) as post_count from post group by userid having userid=?";
		String followerSql = "select count(follow_num) as follower_count from follow group by follower having follower=?";
		String followingSql = "select count(follow_num) as following_count from follow group by following having following=?";
		
		MemberViewDto mdto = null;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(memberSql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mdto = new MemberViewDto();
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
			
			pstmt = con.prepareStatement(postSql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mdto.setPostNum(rs.getInt("post_count"));
			}

			pstmt = con.prepareStatement(followingSql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mdto.setFollowingNum(rs.getInt("following_count"));
			}
			
			pstmt = con.prepareStatement(followerSql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				mdto.setFollowerNum(rs.getInt("follower_count"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return mdto;
	}
}
