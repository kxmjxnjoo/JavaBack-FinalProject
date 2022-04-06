package com.ezen.springfeed.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface IUtilDao {
    void getMemberSearch(HashMap<String, Object> paramMap);

    void getPostSearch(HashMap<String, Object> paramMap);
}