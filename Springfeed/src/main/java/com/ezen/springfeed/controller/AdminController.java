package com.ezen.springfeed.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import com.ezen.springfeed.service.AdminService;
import com.ezen.springfeed.service.PostService;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdminController {
	
	@Autowired
	AdminService as;

	@Autowired
	PostService ps;

	// TODO : implement
	@RequestMapping("/admin/login")
	public String adminLogin() {
		return "";
	}

	// TODO : implement
	@RequestMapping("/admin/loginForm")
	public String adminLoginForm() {
		return "";
	}

	// TODO : implement
	@RequestMapping("/admin/memberList")
	public String memberList() {
		return "";
	}

	// TODO : implement
	@RequestMapping("/admin/searchMember")
	public String searchMember() {
		return "";
	}

	// TODO : implement
	@RequestMapping("/admin/report")
	public String adminReport() {
		return "";
	}

	// TODO : implement

}