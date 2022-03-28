package com.ezen.springfeed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.springfeed.dao.IAdminDao;

@Service
public class AdminService {

	@Autowired
	IAdminDao adao;
}