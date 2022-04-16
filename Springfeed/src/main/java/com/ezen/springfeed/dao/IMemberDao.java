package com.ezen.springfeed.dao;

import java.util.HashMap;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IMemberDao {
	void getMember(HashMap<String, Object> paramMap);
	void insertMember(HashMap<String, Object> paramMap);
	void idCheck(HashMap<String, Object> paramMap);
	void insertFollow(HashMap<String, Object> paramMap);
	void addNotification(HashMap<String, Object> paramMap);
	void unfollow(HashMap<String, Object> paramMap);
	void phoneCheck(HashMap<String, Object> paramMap);
	void getNotification(HashMap<String, Object> paramMap);
	void userEdit(HashMap<String, Object> paramMap);
	void deleteAcount(HashMap<String, Object> paramMap);
	void activateAccount(String userid);
	void getFollow(HashMap<String, Object> followMap);
	void findId(HashMap<String, Object> paramMap);
	void userEmailCheck(String email, String name, boolean pwFindCheck);
	void updatePassword(String str, String email);
	void getFollowedList(HashMap<String, Object> paramMap);
	void getNotiCount(HashMap<String, Object> paramMap);
	void blockMember(String userid, String blocked);
	void unblockMember(String userid, String blocked);
	void blockCheck(HashMap<String, Object> paramMap);
	void privateAccount(String userid);
	void PublicAccount(String userid);
	void findPw(HashMap<String, Object> paramMap);
	void changePw(String userid, String tempPassword);
	
	void getFollowerCount(HashMap<String, Object> paramMap);
	void getFollowingCount(HashMap<String, Object> paramMap);
	void getFollowerList(HashMap<String, Object> paramMap);
	
}