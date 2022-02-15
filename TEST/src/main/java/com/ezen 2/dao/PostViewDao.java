package com.ezen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ezen.dto.PostViewDto;
import com.ezen.util.Dbman;

public class PostViewDao {
	private PostViewDao() {}
	private static PostViewDao itc = new PostViewDao();
	public static PostViewDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<PostViewDto> getAllPost(String userid) {
		ArrayList<PostViewDto> list = null;
		String sql = "select * from post_view order by create_date desc;";
		String likeSql = "select count(post_num) as likes from post_like group by post_num having post_num=?;";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<PostViewDto>();
					count++;
				}
				PostViewDto pdto = new PostViewDto();
				pdto.setAddress(rs.getString("address"));
				pdto.setContent(rs.getString("content"));
				pdto.setCreateDate(rs.getDate("create_date"));
				pdto.setPostImg(rs.getString("post_img"));
				pdto.setPostNum(rs.getInt("post_num"));
				pdto.setUserid(rs.getString("userid"));
				pdto.setUserImg(rs.getString("user_img"));
				list.add(pdto);
			}
			
			for(PostViewDto pdto : list) {
				pstmt = con.prepareStatement(likeSql);
				pstmt.setInt(1, pdto.getPostNum());
				rs = pstmt.executeQuery();
				while(rs.next()) {
					pdto.setLikes(rs.getInt("likes"));
				}
			}
			
			for(PostViewDto pdto : list) {
				sql = "select * from post_like where post_num=? and userid=?;";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pdto.getPostNum());
				pstmt.setString(2, userid);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					pdto.setIsLikedByUser(1);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}

	public ArrayList<PostViewDto> getAllPostById(String userid) {
		ArrayList<PostViewDto> list = null;
		String sql = "select * from post_view where userid=?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<PostViewDto>();
					count++;
				}
				PostViewDto pdto = new PostViewDto();
				pdto.setAddress(rs.getString("address"));
				pdto.setContent(rs.getString("content"));
				pdto.setCreateDate(rs.getDate("create_date"));
				pdto.setPostImg(rs.getString("post_img"));
				pdto.setPostNum(rs.getInt("post_num"));
				pdto.setUserid(rs.getString("userid"));
				pdto.setUserImg(rs.getString("user_img"));
				list.add(pdto);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}		
		
		return list;
	}
}
