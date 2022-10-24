package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dto.MemberDto;
import com.dto.MessageDto;
import com.util.Dbman;

public class MessageDao {
	// Singleton
	private MessageDao() {}
	private static MessageDao itc = new MessageDao();
	public static MessageDao getInstance() { return itc; }
	
	// DB
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// Return all messages between certain users
	public ArrayList<MessageDto> getAllMessage(String userFrom, String userTo) {
		ArrayList<MessageDto> list = null;
		String sql = "select * from message where (message_from=? and message_to=?) or (message_from=? and message_to=?) order by send_date";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userFrom);
			pstmt.setString(2, userTo);
			pstmt.setString(3, userTo);
			pstmt.setString(4, userFrom);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<MessageDto>();
					count++;
				}
				MessageDto mdto = new MessageDto();
				mdto.setContent(rs.getString("content"));
				mdto.setMessageFrom(rs.getString("message_from"));
				mdto.setMessageTo(rs.getString("message_to"));
				mdto.setNum(rs.getInt("num"));
				mdto.setSendDate(rs.getTimestamp("send_date"));
				
				// get img
				String img = MemberDao.getInstance().getMember(mdto.getMessageFrom()).getImg();
				mdto.setFromImg(img);
				
				list.add(mdto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	// Return single instance of all mdto where userid received/send messages
	public ArrayList<MessageDto> getAllMessageMember(String userid) {
		ArrayList<MessageDto> list = null;
		String sql = "select distinct message_from from message where message_to=?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<MessageDto>();
					count++;
				}
				MemberDto memdto = MemberDao.getInstance().getMember(rs.getString("message_to"));
				MessageDto mdto = new MessageDto();
				mdto.setFromImg(memdto.getImg());
				mdto.setMessageFrom(rs.getString("message_from"));
				mdto.setContent("메세지를 시작하세요");
				
				list.add(mdto);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	// Return result of insertion into db
	public int sendMessage(MessageDto mdto) {
		int result = 0;
		String sql = "insert into message (num, message_to, message_from, content) values(message_seq.nextVal, ?,?,?)";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getMessageTo());
			pstmt.setString(2, mdto.getMessageFrom());
			pstmt.setString(3, mdto.getContent());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return result;
	}

	public int deleteAllMessage(String userid) {
		int result = 0;
		String sql = "delete from message where (message_to=? or message_from=?)";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, userid);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return result;	
	}
}
