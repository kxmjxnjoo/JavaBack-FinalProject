package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dto.ReplyDto;
import com.util.Dbman;

public class ReplyDao {
	private ReplyDao() {}
	private static ReplyDao itc = new ReplyDao();
	public static ReplyDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public int addReply(ReplyDto rdto) {
		String sql = "insert into reply (userid, content, post_num) values(?, ?, ?)";
		int result = 0;
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rdto.getUserid());
			pstmt.setString(2, rdto.getContent());
			pstmt.setInt(3, rdto.getPost_num());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return result;
	}
	
	public int getReplyNum() {
		String sql = "SELECT LAST_INSERT_ID()";
		int result = 0;
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return result;
	}
	
	public ReplyDto getReply(int replyNum) {
		ReplyDto rdto = null;
		String sql = "select * from reply where reply_num=?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, replyNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				rdto = new ReplyDto();
				rdto.setContent(rs.getString("content"));
				rdto.setPost_num(rs.getInt("post_num"));
				rdto.setReply_num(rs.getInt("reply_num"));
				rdto.setUserid(rs.getString("userid"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return rdto;
	}

	public int deleteAllReply(String userid) {
		int result = 0;
		String sql = "delete from reply where userid=?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return result;
		
	}
	
}
