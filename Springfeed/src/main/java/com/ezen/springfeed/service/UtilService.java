package com.ezen.springfeed.service;

import com.ezen.springfeed.dao.IUtilDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class UtilService {

    @Autowired
    IUtilDao udao;

    public void getMemberSearch(HashMap<String, Object> paramMap) {
        udao.getMemberSearch(paramMap);
    }

    public void getPostSearch(HashMap<String, Object> paramMap) {
        udao.getPostSearch(paramMap);
    }
}