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

    public void addStoryReport(HashMap<String, Object> paramMap) {
    	udao.addStoryReport(paramMap);
	}

	public void addPostReport(HashMap<String, Object> paramMap) {
		udao.addPostReport(paramMap);
	}

	public void addUserReport(HashMap<String, Object> paramMap) {
		udao.addUserReport(paramMap);
	}

    public void getExploreFeed(HashMap<String, Object> paramMap) {
        udao.exploreFeed(paramMap);
    }
}