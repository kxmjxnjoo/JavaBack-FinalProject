package com.ezen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.ezen.dto.ReplyDto;
import com.ezen.util.Dbman;

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
			pstmt.setInt(3, rdto.getPostNum());
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
				rdto.setPostNum(rs.getInt("post_num"));
				rdto.setReplyNum(rs.getInt("reply_num"));
				rdto.setUserid(rs.getString("userid"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return rdto;
	}
	
}
