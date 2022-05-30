package com.ezen.springfeed.tmp.service;

import com.ezen.springfeed.tmp.dao.IPostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class PostService {
    @Autowired
    IPostDao pdao;

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

    public void getPostDetail(HashMap<String, Object> paramMap) {
        pdao.getPostDetail(paramMap);
    }

    public void getReply(HashMap<String, Object> paramMap) {
        pdao.getReply(paramMap);
    }

    public void addReply(HashMap<String, Object> paramMap) {
        pdao.addReply(paramMap);
    }

    public void deleteSavePost(HashMap<String, Object> paramMap) {
        pdao.deleteSavePost(paramMap);
    }

    public void deleteLike(HashMap<String, Object> paramMap) {
        pdao.deleteLike(paramMap);
    }
}