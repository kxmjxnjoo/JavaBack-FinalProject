package com.ezen.springfeed.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface IPostDao {
    void selectPostsByUserid(HashMap<String, Object> paramMap);
    void selectPostByNum(HashMap<String, Object> paramMap);
    void insertPost(HashMap<String, Object> paramMap);
    void updatePost(HashMap<String, Object> paramMap);
    void deletePost(HashMap<String, Object> paramMap);
    void selectPostFeed(HashMap<String, Object> paramMap);
    void insertLike(HashMap<String, Object> paramMap);
    void getSavePost(HashMap<String, Object> paramMap);
    void getReplyByPostNum(HashMap<String, Object> paramMap);
    void insertSavePost(HashMap<String, Object> paramMap);
    void getPostDetail(HashMap<String, Object> paramMap);
    void getReply(HashMap<String, Object> paramMap);
    void addReply(HashMap<String, Object> paramMap);
}