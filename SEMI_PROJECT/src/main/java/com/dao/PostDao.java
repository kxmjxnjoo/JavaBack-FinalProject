package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.PostDto;
import com.dto.PostLikeDto;
import com.dto.ReplyDto;
import com.dto.ReplyLikeDto;
import com.util.Dbman;

public class PostDao {
	private PostDao() {}
	private static PostDao itc = new PostDao();
	public static PostDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public void uploadPost(PostDto pdto) {
		String sql = "insert into post(post_num, img, content, Address, userid)"
				+ " values(post_seq.nextval, ?, ?, ?, ?)";
	    con = Dbman.getConnection();
	        try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pdto.getPost_img());
				pstmt.setString(2, pdto.getContent());
				pstmt.setString(3, pdto.getAddress());
				pstmt.setString(4, pdto.getUserid());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally { Dbman.close(con, pstmt, rs); }
	}

	public int get_post_num(String userid) {
	   int post_num = 0;
	   String sql = "select max(post_num) as max from post group by userid having userid='" + userid +"'";
    	con = Dbman.getConnection();
	    try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				post_num = rs.getInt("max");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
        return post_num;
	}

	public PostDto getPost(int post_num) {
		String sql = "select * from post_view where post_num = ?";
		PostDto pdto = null;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				pdto = new PostDto();
				pdto.setUserid(rs.getString("userid"));
				pdto.setUser_img(rs.getString("user_img"));
				pdto.setContent(rs.getString("content"));
				pdto.setAddress(rs.getString("address"));
				pdto.setPost_img(rs.getString("post_img"));
				pdto.setCreate_date(rs.getTimestamp("create_date"));
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return pdto;
	}

	public ArrayList<ReplyDto> getReply(int post_num) {
		ArrayList<ReplyDto> reply_list = new ArrayList<>();
		String sql = "select * from reply_view where post_num = ? order by reply_num desc";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ReplyDto rdto = new ReplyDto();
				rdto.setContent(rs.getString("content"));
				rdto.setUserid(rs.getString("userid"));
				rdto.setReply_num(rs.getInt("reply_num"));
				rdto.setReply_date(rs.getTimestamp("reply_date"));
				rdto.setImg(rs.getString("img"));
				reply_list.add(rdto);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		
		return reply_list;
	}

	public void insertReply(ReplyDto rdto, int post_num) {
		String sql = "insert into reply(userid, content, reply_num, post_num)"
				+ " values(?,?,reply_seq.nextVal,?)";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, rdto.getUserid());
			pstmt.setString(2, rdto.getContent());
			pstmt.setInt(3, post_num);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}

	public int addPostLike(int post_num, String userid) {
		String sql = "insert into post_like values(?, ?)";
		int result = 0;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			pstmt.setString(2, userid);
			result = pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return result;
	}

	public int postLikeCheck(int post_num, String userid) {
		String sql = "select ? from post_like where post_num = ?";
		int result = 0;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, post_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return result;
	}

	public void deleteLike(int post_num, String userid) {
		String sql = "delete post_like where post_num = ? and userid = ?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, post_num);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}

	public void editPost(PostDto pdto) {
		String sql = "update post set address=?, img=?, content=? where post_num=" + pdto.getPostNum();
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, pdto.getAddress());
			pstmt.setString(2, pdto.getPost_img());
			pstmt.setString(3, pdto.getContent());
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}

	public void deletePost(int post_num) {
		String sql = "delete post where post_num=" + post_num;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}

	public int insertReport(String loginUser, String reported, int post_num, String reason, String type) {
		String sql = "insert into report(reporter_id, reported_id,reason, report_num, post_num, report_type) "
				+ " values(?,?,?,report_seq.nextVal,?,?)";
		int result = 0;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginUser);
			pstmt.setString(2, reported);
			pstmt.setString(3, reason);
			pstmt.setInt(4, post_num);
			pstmt.setString(5, type);
			result = pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return result;
	}
	
	public int insertStoryReport(String loginUser, String reported, int reported_post, String reason, String type) {
		String sql = "insert into report(reporter_id, reported_id,reason, report_num, story_num, report_type) "
				+ " values(?,?,?,report_seq.nextVal,?,?)";
		int result = 0;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, loginUser);
			pstmt.setString(2, reported);
			pstmt.setString(3, reason);
			pstmt.setInt(4, reported_post);
			pstmt.setString(5, type);
			result = pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return result;
	}

	public int replyLikeCheck(int reply_num, String userid) {
		int result = 0;
		String sql = "select ? from reply_like where reply_num=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, reply_num);
			rs=pstmt.executeQuery();
			if(rs.next()) result=1;
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		
		return result;
	}

	public void addReplyLike(int reply_num, String userid) {
		String sql = "insert into reply_like values(?, ?)";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reply_num);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}

	public void deleteReplyLike(int reply_num, String userid) {
		String sql = "delete reply_like where reply_num=? and userid=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, reply_num);
			pstmt.setString(2, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}

	public void deleteReply(int reply_num) {
		String sql = "delete reply where reply_num=" + reply_num;
		con = Dbman.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
	}

	public ArrayList<PostDto> getBestPost() {
		ArrayList<PostDto> postList =  new ArrayList<>();
		String sql = "select p.*, b.rn, b.count as likeCount  from post_view p inner join " + 
				"(select a.post_num, a.count, row_number() over (order by count desc) rn " + 
				"from (select post_num, count(post_num) as count from post_like group by post_num order by count(post_num) desc) a) b on " + 
				"(p.post_num = b.post_num) where rn < 100 order by rn" ; 
		con = Dbman.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PostDto pdto = new PostDto();
				pdto.setPostNum(rs.getInt("post_num"));
				pdto.setPost_img(rs.getString("post_img"));
				pdto.setContent(rs.getString("content"));
				pdto.setAddress(rs.getString("address"));
				pdto.setUserid(rs.getString("userid"));
				pdto.setUser_img(rs.getString("user_img"));
				pdto.setLikeCount(rs.getInt("likeCount"));
				postList.add(pdto);
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return postList;
	}

	
	 public int insertUserReport(String loginUser, String reported, String reason, String type) {
		 String sql = "insert into report(reporter_id, reported_id,reason, report_num, report_type) "
		 		+ " values(?,?,?,report_seq.nextVal,?)";
		 int result = 0; 
		 con = Dbman.getConnection(); 
		 try { 
			 pstmt = con.prepareStatement(sql); 
			 pstmt.setString(1, loginUser); 
			 pstmt.setString(2, reported);
			 pstmt.setString(3, reason); 
			 pstmt.setString(4, type); 
			 result = pstmt.executeUpdate(); 
			 } catch (SQLException e) { e.printStackTrace(); 
			 } finally { Dbman.close(con, pstmt, rs); } 
		 return result;
	  }
	 
	
	

	public int likeCount(int post_num) {
		int likes = 0;
		String sql = "select count(*) as count from post_like where post_num="+post_num;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				likes = rs.getInt("count");
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return likes;
	}

	public int replyCount(int post_num) {
		int replies = 0;
		String sql = "select count(*) as count from reply where post_num="+post_num;
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				replies = rs.getInt("count");
			}
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs); }
		return replies;
	}


}
