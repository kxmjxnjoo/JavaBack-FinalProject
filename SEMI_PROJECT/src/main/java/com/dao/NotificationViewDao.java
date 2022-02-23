package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dto.NotificationViewDto;
import com.util.Dbman;

public class NotificationViewDao {
	private NotificationViewDao() {}
	private static NotificationViewDao itc = new NotificationViewDao();
	public static NotificationViewDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<NotificationViewDto> getNotification(String userid) {
		ArrayList<NotificationViewDto> list = null;
		String sql = "select * from notification where user_to=? order by create_date desc";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<NotificationViewDto>();
					count++;
				}
				NotificationViewDto ndto = new NotificationViewDto();
				ndto.setCreateDate(rs.getDate("create_date"));
				ndto.setUserFrom(rs.getString("user_from"));
				ndto.setUserTo(rs.getString("user_to"));
				
				// Get member_img
				String memberid = rs.getString("user_from");
				String memberImg = MemberDao.getInstance().getMember(memberid).getImg();
				ndto.setMemberImg(memberImg);
				
				int notiType = rs.getInt("noti_type");
				ndto.setNotiType(notiType);
				// 1 : follow, 2 : post like, 3 : reply, 4 : reply like
				if(notiType == 2) {
					int postNum = rs.getInt("post_num");
					String postImg = PostDao.getInstance().getPost(postNum).getPost_img();
					ndto.setPostImg(postImg);
				} else if(notiType == 3) {
					int replyNum = rs.getInt("reply_num");
					String replyContent = ReplyDao.getInstance().getReply(replyNum).getContent();
					ndto.setReplyContent(replyContent);
				}
				
				list.add(ndto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}

	public int addNotification(String userTo, String userFrom, int notiType, int num) {
		int result = 0;
		
		String followSql = "insert into notification (num, user_to, user_from, noti_type) values(notification_seq.nextval, ?, ?, 1)";
		String postSql = "insert into notification (num, user_to, user_from, noti_type, post_num) values(notification_seq.nextval, ?, ?, 2, ?)";
		String replySql = "insert into notification (num, user_to, user_from, noti_type, reply_num) values(notification_seq.nextval, ?, ?, 3, ?)";
		String replyLikeSql = "insert into notification (num, user_to, user_from, noti_type, reply_num) values(notification_seq.nextval, ?, ?, 4, ?)";
		String storySql = "insert into notification (num, user_to, user_from, noti_type, story_num) values(notification_seq.nextval, ?, ?, 5, ?)";

		con = Dbman.getConnection();
		try {
			if(notiType == 1) {
				// FOLLOW
				pstmt = con.prepareStatement(followSql);
				pstmt.setString(1, userTo);
				pstmt.setString(2, userFrom);
			} else if(notiType == 2) {
				// LIKE
				pstmt = con.prepareStatement(postSql);
				pstmt.setString(1, userTo);
				pstmt.setString(2, userFrom);
				pstmt.setInt(3, num);
			} else if(notiType == 3) {
				// COMMENT
				pstmt = con.prepareStatement(replySql);
				pstmt.setInt(3, num);
			} else if(notiType == 4) {
				// COMMENT LIKE
			} else if(notiType == 5) {
				// STORY 
				pstmt = con.prepareStatement(storySql);
				pstmt.setString(1, userTo);
				pstmt.setString(2, userFrom);
				pstmt.setInt(3, num);
			}
			
			pstmt.setString(1, userTo);
			pstmt.setString(2, userFrom);
			
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return result;
	}
}
