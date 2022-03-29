package com.ezen.springfeed.service;

import com.ezen.springfeed.dao.IPostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PostService {
    // CRUD
    // dao      service
    // select   get
    // insert   add
    // update   update
    // delete   delete

    @Autowired
    IPostDao pdao;

    public void getPost(HashMap<String, Object> paramMap) {
        pdao.selectPost(paramMap);
    }

    public void addPost(HashMap<String, Object> paramMap) {
        pdao.insertPost(paramMap);
    }

    public void updatePost(HashMap<String, Object> paramMap) {
        pdao.updatePost(paramMap);
    }

    public void deletePost(HashMap<String, Object> paramMap) {
        pdao.deletePost(paramMap);
    }
}