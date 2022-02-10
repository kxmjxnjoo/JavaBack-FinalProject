package com.ezen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ezen.dto.NotificationViewDto;
import com.ezen.util.Dbman;

public class NotificationViewDao {
	private NotificationViewDao() {}
	private static NotificationViewDao itc = new NotificationViewDao();
	public static NotificationViewDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<NotificationViewDto> getNotification(String userid) {
		ArrayList<NotificationViewDto> list = null;
		String sql = "select * from notification_view where user_to=? order by create_date";
		
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
				ndto.setMemberImg(rs.getString("member_img"));
				ndto.setUserFrom(rs.getString("user_from"));
				ndto.setUserTo(rs.getString("user_to"));
				
				int notiType = rs.getInt("noti_type");
				ndto.setNotiType(notiType);
				// 1 : follow, 2 : post like, 3 : reply, 4 : reply like
				if(notiType == 2) {
					int postNum = rs.getInt("post_num");
					String postImg = PostDao.getInstance().getPost(postNum).getImg();
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
}
