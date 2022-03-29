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

<<<<<<< HEAD
	public void searchMember(HashMap<String, Object> paramMap) {
		adao.searchMember(paramMap);
	}

	public void reportList(HashMap<String, Object> paramMap) {
		adao.reportList(paramMap);
	}

	public void postReportCheck(HashMap<String, Object> paramMap) {
		adao.postReportCheck(paramMap);
=======
	public void reportList(HashMap<String, Object> paramMap) {
	}

	public void searchMember(HashMap<String, Object> paramMap) {
>>>>>>> e956734fa26a809fed5375c231066c7832c5a4ef
	}
}