package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.dto.MemberDto;
import com.util.Dbman;

public class MemberDao {
	private MemberDao() {}
	private static MemberDao itc = new MemberDao();
	public static MemberDao getInstance() { return itc; }
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MemberDto getMember(String userid) {
		MemberDto mdto = null;
		String sql = "select * from member where userid=?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mdto = new MemberDto();
				mdto.setEmail(rs.getString("email"));
				mdto.setImg(rs.getString("img"));
				mdto.setIndate(rs.getDate("indate"));
				mdto.setIntroduce(rs.getString("introduce"));
				mdto.setName(rs.getString("name"));
				mdto.setPassword(rs.getString("password"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setUserid(rs.getString("userid"));
				mdto.setUseyn(rs.getString("useyn"));
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return mdto;
	}
	
	public ArrayList<MemberDto> getAllMembers() {
		ArrayList<MemberDto> list = null;
		String sql = "select * from member";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<MemberDto>();
					count++;
				}
				MemberDto mdto = new MemberDto();
				mdto.setEmail(rs.getString("email"));
				mdto.setImg(rs.getString("img"));
				mdto.setIndate(rs.getDate("indate"));
				mdto.setIntroduce(rs.getString("introduce"));
				mdto.setName(rs.getString("name"));
				mdto.setPassword(rs.getString("password"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setUserid(rs.getString("userid"));
				mdto.setUseyn(rs.getString("useyn"));
				list.add(mdto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}
	
	public ArrayList<MemberDto> getMembers(String searchWord) {
		ArrayList<MemberDto> list = null;
		String sql = "select * from member where userid like ? or name like ? or email like ? or phone like ?";
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchWord + "%");
			pstmt.setString(2, "%" + searchWord + "%");
			pstmt.setString(3, "%" + searchWord + "%");
			pstmt.setString(4, "%" + searchWord + "%");
			rs = pstmt.executeQuery();
			
			int count = 0;
			while(rs.next()) {
				if(count == 0) {
					list = new ArrayList<MemberDto>();
					count++;
				}
				MemberDto mdto = new MemberDto();
				mdto.setEmail(rs.getString("email"));
				mdto.setImg(rs.getString("img"));
				mdto.setIndate(rs.getDate("indate"));
				mdto.setIntroduce(rs.getString("introduce"));
				mdto.setName(rs.getString("name"));
				mdto.setPassword(rs.getString("password"));
				mdto.setPhone(rs.getString("phone"));
				mdto.setUserid(rs.getString("userid"));
				mdto.setUseyn(rs.getString("useyn"));
				list.add(mdto);
			} 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return list;
	}

	public int insertMember(MemberDto mdto) {
		String sql = "insert into member (userid, password, name, phone, email, img) values(?,?,?,?,?, 'tmpUserIcon.png')";
		con = Dbman.getConnection();
		int result = 0;

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getUserid());
			pstmt.setString(2, mdto.getPassword());
			pstmt.setString(3, mdto.getName());
			pstmt.setString(4, mdto.getPhone());
			pstmt.setString(5, mdto.getEmail());
			result = pstmt.executeUpdate(); 
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}

		return result;
	}
	
	public int updateMember(MemberDto mdto, String userid) {
		String sql = "update member set password=?, name=?, email=?, phone=?, introduce=? where userid=?";
		int result = 0;
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, mdto.getPassword());
			pstmt.setString(2, mdto.getName());
			pstmt.setString(3, mdto.getEmail());
			pstmt.setString(4, mdto.getPhone());
			pstmt.setString(5, mdto.getIntroduce());
			pstmt.setString(6, userid);
			result = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return result;
	}

	public int deleteMember(String userid) {
		String sql = "delete member where userid=?";
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

	public MemberDto getUserid(String name, String phone) {
		String sql = "select * from member where (name=? and phone=?)";
		MemberDto mdto = null;
		
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phone);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				mdto = new MemberDto();
				mdto.setUserid(rs.getString("userid"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			Dbman.close(con, pstmt, rs);
		}
		
		return mdto;
	}

	public void blockUser(String userid) {
		String sql = "Update Member set useyn ='n' where userid=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		}  finally { Dbman.close(con, pstmt, rs); }
	}
	
	public void unBlockUser(String userid) {
		String sql = "Update Member set useyn ='y' where userid=?";
		con = Dbman.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (SQLException e) { e.printStackTrace();
		}  finally { Dbman.close(con, pstmt, rs); }
	}
}