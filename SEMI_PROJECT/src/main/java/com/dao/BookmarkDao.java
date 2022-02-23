package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.dto.BookmarkDto;
import com.util.Dbman;

public class BookmarkDao {
	// Singleton
	private BookmarkDao() {}
	private static BookmarkDao itc = new BookmarkDao();
	public static BookmarkDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ArrayList<BookmarkDto> getBookmark(String userid) {
		String sql = "select * from bookmark where userid=? order by save_date desc";
		ArrayList<BookmarkDto> list = null;
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<BookmarkDto>();
					count++;
				}
				BookmarkDto bdto = new BookmarkDto();
				bdto.setNum(rs.getInt("num"));
				bdto.setPostNum(rs.getInt("post_num"));
				bdto.setSaveDate(rs.getDate("save_date"));
				bdto.setUserid(rs.getString("userid"));
				
				bdto.setPostImg(PostDao.getInstance().getPost(bdto.getPostNum()).getPost_img());
				
				list.add(bdto);
			}
		} catch(Exception  e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public BookmarkDto getBookmarkByNum(int num) {
		String sql = "select * from bookmark where num=?";
		BookmarkDto bdto = null;
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				bdto = new BookmarkDto();
				bdto.setNum(rs.getInt("num"));
				bdto.setPostNum(rs.getInt("post_num"));
				bdto.setUserid(rs.getString("userid"));
				bdto.setSaveDate(rs.getDate("save_date"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return bdto;
	}
	
	public int insertBookmark(BookmarkDto bdto) {
		String sql = "insert into bookmark (num, post_num, userid) values(bookmark_seq.nextVal,?,?)";
		int result = 0;
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bdto.getPostNum());
			pstmt.setString(2, bdto.getUserid());
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return result;
	}

	public int deleteBookmark(int num) {
		String sql = "delete from bookmark where num=?";
		int result = 0;
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return result;
	}
 	
	public int deleteAllBookmark(String userid) {
		String sql = "delete from bookmark where userid=?";
		int result = 0;
		
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

	public int getBookmark(String userid, int postNum) {
		int result = 0;
		String sql = "select * from bookmark where userid=? and post_num=?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setInt(2, postNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return result;
	}
}
