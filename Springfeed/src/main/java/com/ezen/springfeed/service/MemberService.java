package com.ezen.springfeed.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.ezen.springfeed.dao.IMemberDao;

public class MemberService {

	@Autowired
	IMemberDao mdao;
	
	public void getMember(HashMap<String, Object> paramMap) {
		mdao.getMember(paramMap);
	}

}
