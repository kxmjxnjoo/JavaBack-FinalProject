package com.ezen.springfeed.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springfeed.dao.IAdminDao;

@Service
public class AdminService {

	@Autowired
	IAdminDao adao;

	public void checkAdmin(HashMap<String, Object> paramMap) {
		adao.checkAdmin(paramMap);
	}

	public void getAllCount(HashMap<String, Object> paramMap) {
		adao.getAllCount(paramMap);
	}

	public void memberList(HashMap<String, Object> paramMap) {
		adao.memberList(paramMap);
	}

	public void searchMember(HashMap<String, Object> paramMap) {
		adao.searchMember(paramMap);
	}

	public void reportList(HashMap<String, Object> paramMap) {
		adao.reportList(paramMap);
	}

	public void postReportCheck(HashMap<String, Object> paramMap) {
		adao.postReportCheck(paramMap);
	}

	public void storyReportCheck(HashMap<String, Object> paramMap) {
		adao.storyReportCheck(paramMap);
	}

	public void deletePost(HashMap<String, Object> paramMap) {
		adao.deletePost(paramMap);
	}

	public void deleteStory(HashMap<String, Object> paramMap) {
		adao.deleteStory(paramMap);
	}

	public void blockUser(HashMap<String, Object> paramMap) {
		adao.blockUser(paramMap);
	}

	public void getAllCount_r(HashMap<String, Object> paramMap) {
		adao.getAllCount_r(paramMap);
	}

	public void getAllCount_q(HashMap<String, Object> paramMap) {
		adao.getAllCount_q(paramMap);
	}
}