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
				list.add(bdto);
			}
		} catch(Exception  e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public int insertBookmark(BookmarkDto bdto) {
		String sql = "insert into bookmark (num, post_num, save_date, userid) values(bookmark_seq.nextVal,?,?,?)";
		int result = 0;
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bdto.getNum());
			pstmt.setInt(2, bdto.getPostNum());
			pstmt.setDate(3, bdto.getSaveDate());
			pstmt.setString(4, bdto.getUserid());
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
}
