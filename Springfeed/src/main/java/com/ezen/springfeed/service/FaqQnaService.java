package com.ezen.springfeed.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springfeed.dao.IFaqQnaDao;

@Service
public class FaqQnaService {

	@Autowired
	IFaqQnaDao fqdao;
	
	public void addQna(HashMap<String, Object> paramMap) {
	fqdao.addQna(paramMap);

	}
}