package com.ezen.springfeed.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IFaqQnaDao {

	void addQna(HashMap<String, Object> paramMap);

}