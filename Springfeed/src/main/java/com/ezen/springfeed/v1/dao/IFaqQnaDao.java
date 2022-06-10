package com.ezen.springfeed.v1.dao;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IFaqQnaDao {

	void adminFaqList(HashMap<String, Object> paramMap);

	void addQna(HashMap<String, Object> paramMap);

	void addFaq(HashMap<String, Object> paramMap);

	void deleteFaq(HashMap<String, Object> paramMap);

	void deleteQna(HashMap<String, Object> paramMap);

	void faqEdit(HashMap<String, Object> paramMap);

	void qnaEdit(HashMap<String, Object> paramMap);

	void adminQnaList(HashMap<String, Object> paramMap);

	void qnaList(HashMap<String, Object> paramMap);
	
	void faqList(HashMap<String, Object> paramMap);
	
	void getQna(HashMap<String, Object> paramMap);

	void getQnaList(HashMap<String, Object> paramMap);

	void getAllQna(HashMap<String, Object> paramMap);

	void reportList(HashMap<String, Object> paramMap);

	void qnaDetail(HashMap<String, Object> paramMap);

	void insertReply(HashMap<String, Object> paramMap);
}