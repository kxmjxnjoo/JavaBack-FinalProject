package com.ezen.springfeed.service;

import com.ezen.springfeed.dao.IPostDao;
import com.ezen.springfeed.dao.IStoryDao;
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

// Post
    public void getPostsByUserid(HashMap<String, Object> paramMap) {
        pdao.selectPostsByUserid(paramMap);
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

    public void getPostFeed(HashMap<String, Object> paramMap) {
        pdao.selectPostFeed(paramMap);
    }

    public void insertLike(HashMap<String, Object> paramMap) {
        pdao.insertLike(paramMap);
    }

    public void getSavedPost(HashMap<String, Object> paramMap) {
        pdao.getSavePost(paramMap);
    }

    public void getReplyByPostNum(HashMap<String, Object> paramMap) {
        pdao.getReplyByPostNum(paramMap);
    }

    public void selectPostByNum(HashMap<String, Object> paramMap) {
        pdao.selectPostByNum(paramMap);
    }

    public void insertSavePost(HashMap<String, Object> paramMap) {
        pdao.insertSavePost(paramMap);
    }
}