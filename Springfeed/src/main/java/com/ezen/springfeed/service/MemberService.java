package com.ezen.springfeed.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springfeed.dao.IMemberDao;

@Service
public class MemberService {

	@Autowired
	IMemberDao mdao;
	
	public void getMember(HashMap<String, Object> paramMap) {
		mdao.getMember(paramMap);
	}

	public int idCheck(String id) {
		//int cnt = mdao.idCheck(id);
		return 0;
	}

	public void insertMember(HashMap<String, Object> paramMap) {
		mdao.insertMember(paramMap);
	}

}
