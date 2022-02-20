package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.QnaDto;
import com.util.Dbman;

public class QnaDao {
	
	private QnaDao() {}
	private static QnaDao instance = new QnaDao();
	public static QnaDao getInstance() {
		return instance;
	}
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	
	public ArrayList<QnaDto> listQna(String userid) {
		ArrayList<QnaDto> list = new ArrayList<QnaDto>();
		String sql = "select * from qna where id=? order by qna_num desc";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  userid);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				QnaDto qdto = new QnaDto();
		    	qdto.setQna_num(rs.getInt("qna_num"));
		    	qdto.setQna_subject(rs.getString("qna_subject"));
		    	qdto.setQna_content(rs.getString("qna_content"));
		    	qdto.setQna_reply(rs.getString("qna_reply"));
		    	qdto.setQna_id(rs.getString("qna_id"));
		    	qdto.setRep(rs.getString("rep"));
		    	qdto.setIndate(rs.getTimestamp("indate"));		    	
		    	//qdto.setQna_password(rs.getString("qna_password"));		    	
		    	list.add(qdto);
		    }
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);  }
		return list;
	}

	
	
	public QnaDto getQna(int qna_num) {
		QnaDto qdto = new QnaDto();
		String sql = "select * from qna where qseq = ?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  qna_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				qdto.setQna_num(qna_num);
				qdto.setQna_subject(rs.getString("Qna_subject"));
				qdto.setQna_content(rs.getString("Qna_content"));
				qdto.setQna_reply(rs.getString("Qna_reply"));
				qdto.setQna_id(rs.getString("Qna_id"));
				qdto.setIndate(rs.getTimestamp("indate"));
				qdto.setRep(rs.getString("rep"));
			//qdto.setQna_password(rs.getString("Qna_password"));
			}
		} catch (SQLException e) {e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);	}
		return qdto;
	}
	
	
	
	public QnaDto uploadQna(QnaDto qdto) {
		String sql = "insert into qna(qna_num, qna_subject, qna_content, qna_id)"
				+ "values(qna_seq.nextVal, ?, ?, ?)";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qdto.getQna_subject());
			pstmt.setString(2, qdto.getQna_content());
			pstmt.setString(3, qdto.getQna_id());
			pstmt.executeUpdate();
		} catch (SQLException e) {	e.printStackTrace();
		} finally {Dbman.close(con, pstmt, rs);	}
		return qdto;
	}
	
	
	
	public void updateQna(QnaDto qdto) {
		String sql = "Update Qna set qna_subject=?, qna_content=?"
				+ " where qna_id = ?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, qdto.getQna_subject());
			pstmt.setString(2, qdto.getQna_subject());
			pstmt.setString(3, qdto.getQna_id());
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Dbman.close(con, pstmt, rs);		}
	}



	public void addComment(QnaDto qdto) {
		
	}
}