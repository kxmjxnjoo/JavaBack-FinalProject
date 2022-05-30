package com.ezen.springfeed.tmp.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springfeed.tmp.dao.IAdminDao;

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
	
	public void updateReportPostBlock(HashMap<String, Object> paramMap) {
		adao.updateReportPostBlock(paramMap);
	}

	public void updateReportStoryBlock(HashMap<String, Object> paramMap) {
		adao.updateReportStoryBlock(paramMap);
	}

	public void updateReportUserBlock(HashMap<String, Object> paramMap) {
		adao.updateReportUserBlock(paramMap);
	}

	public void userReportCheck(HashMap<String, Object> paramMap) {
		adao.userReportCheck(paramMap);
	}

	public void updateReportPostUnBlock(HashMap<String, Object> paramMap) {
		adao.updateReportPostUnBlock(paramMap);
	}

	public void getAllCount_f(HashMap<String, Object> paramMap) {
		adao.getAllCount_f(paramMap);
	}

}