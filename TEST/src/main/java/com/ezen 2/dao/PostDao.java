package com.ezen.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.ezen.dto.PostDto;
import com.ezen.util.Dbman;

public class PostDao {
	// Singleton
	private PostDao() {}
	private static PostDao itc = new PostDao();
	public static PostDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<PostDto> getAllPost() {
		ArrayList<PostDto> list = null;
		String sql = "select * from post";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<PostDto>();
					count++;
				}
				PostDto pdto = new PostDto();
				pdto.setAddress(rs.getString("address"));
				pdto.setContent(rs.getString("content"));
				pdto.setCreate_date(rs.getDate("create_date"));
				pdto.setImg(rs.getString("img"));
				pdto.setPostNum(rs.getInt("post_num"));
				pdto.setUserid(rs.getString("userid"));
				
				list.add(pdto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		return list;
	}
	
	public PostDto getPost(int postNum) {
		String sql = "select * from post where post_num=?";
		PostDto pdto = null;
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, postNum);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				pdto = new PostDto();
				pdto.setAddress(rs.getString("address"));
				pdto.setContent(rs.getString("content"));
				pdto.setImg(rs.getString("img"));
				pdto.setCreate_date(rs.getDate("create_date"));
				pdto.setPostNum(rs.getInt("post_num"));
				pdto.setUserid(rs.getString("userid"));
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return pdto;
	}
	
}
