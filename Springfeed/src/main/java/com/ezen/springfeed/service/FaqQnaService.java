package com.ezen.springfeed.service;

<<<<<<< HEAD
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springfeed.dao.IFaqQnaDao;
=======
import com.ezen.springfeed.dao.IFaqQnaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
>>>>>>> e956734fa26a809fed5375c231066c7832c5a4ef

@Service
public class FaqQnaService {

	@Autowired
<<<<<<< HEAD
	IFaqQnaDao fqdao;
	
	public void addQna(HashMap<String, Object> paramMap) {
	fqdao.addQna(paramMap);

	
	}
}
=======
    IFaqQnaDao fqdao;

    public void addQna(HashMap<String, Object> paramMap) {
    }
}
>>>>>>> e956734fa26a809fed5375c231066c7832c5a4ef
