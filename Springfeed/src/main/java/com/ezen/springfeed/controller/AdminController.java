package com.ezen.springfeed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.springfeed.service.AdminService;
import com.ezen.springfeed.service.PostService;

@Controller
public class AdminController {
	
	@Autowired
	AdminService as;

	@Autowired
	PostService ps;
}