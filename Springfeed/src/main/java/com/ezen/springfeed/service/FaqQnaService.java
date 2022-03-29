package com.ezen.springfeed.service;

import com.ezen.springfeed.dao.IFaqQnaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class FaqQnaService {

	@Autowired
    IFaqQnaDao fqdao;

    public void addQna(HashMap<String, Object> paramMap) {
    }
}
