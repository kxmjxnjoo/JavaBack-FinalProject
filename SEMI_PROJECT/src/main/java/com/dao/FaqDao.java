package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.FaqDto;
import com.dto.QnaDto;
import com.util.Dbman;

public class FaqDao {
	
	private FaqDao() {}
	private static FaqDao instance = new FaqDao();
	public static FaqDao getInstance() {
		return instance;
	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	
	public ArrayList<FaqDto> listFaq() {
		ArrayList<FaqDto> list = new ArrayList<FaqDto>();
		String sql = "select * from faq order by faq_num desc";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FaqDto fdto = new FaqDto();
		    	fdto.setFaq_num(rs.getInt("faq_num"));
		    	fdto.setFaq_subject(rs.getString("faq_subject"));
		    	fdto.setFaq_content(rs.getString("faq_content"));
		    	list.add(fdto);
		    }
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);  }
		
		return list;
	}

	

	public FaqDto getFaqDetail(int faq_num) {
		FaqDto fdto = new FaqDto();
		String sql = "select * from faq where faq_num=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  faq_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fdto.setFaq_num(faq_num);
				fdto.setFaq_subject(rs.getString("faq_subject"));
				fdto.setFaq_content(rs.getString("faq_content"));
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);	}
		return fdto;
	}



	public void uploadFaq(FaqDto fdto) {
		String sql = "insert into faq(faq_num, faq_subject, faq_content)"
				+ "values(faq_seq.nextVal, ?, ?)";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fdto.getFaq_subject());
			pstmt.setString(2, fdto.getFaq_content());
		} catch (SQLException e) { 	e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);		}
	}
	
	
	
	public void updateFaq(FaqDto fdto) {
		String sql = "Update faq set faq_subject=?, faq_content"
				+ " where faq_num = ?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, fdto.getFaq_subject());
			pstmt.setString(2, fdto.getFaq_content());
			pstmt.setInt(3, fdto.getFaq_num());
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);		}
	}


	/*
	 * public ArrayList<CartVO> selectCart(String id) { ArrayList<CartVO> list = new
	 * ArrayList<CartVO>(); String sql =
	 * "select * from cart_view where id=? and result='1'"; con =
	 * Dbman.getConnection(); try { pstmt = con.prepareStatement(sql);
	 * pstmt.setString(1, id); rs = pstmt.executeQuery(); while( rs.next() ) {
	 * CartVO cvo = new CartVO(); cvo.setCseq(rs.getInt("cseq"));
	 * cvo.setId(rs.getString("id")); cvo.setMname(rs.getString("mname"));
	 * cvo.setPseq(rs.getInt("pseq")); cvo.setPname(rs.getString("pname"));
	 * cvo.setQuantity(rs.getInt("quantity")); cvo.setPrice2(rs.getInt("price2"));
	 * cvo.setIndate(rs.getTimestamp("indate")); list.add(cvo); } } catch
	 * (SQLException e) { e.printStackTrace(); } finally { Dbman.close(con, pstmt,
	 * rs); } return list; }
	 */
	
	
	public void deleteFaq(int faq_num) {
		String sql = "delete from faq where faq_num=?";
		con = Dbman.getConnection();
		try {
		      pstmt = con.prepareStatement(sql); 
		      pstmt.setInt(1, faq_num);
		      pstmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace();
	    } finally { Dbman.close(con, pstmt, rs); }   	
}


	

}