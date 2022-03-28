package com.ezen.springfeed.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springfeed.service.AdminService;
import com.ezen.springfeed.service.PostService;


@Controller
public class AdminController {
	
	@Autowired
	AdminService as;

	@Autowired
	PostService ps;

	
	@RequestMapping("/admin/login")
	public String adminLogin() { 
		return "admin/adminLogin";
	} 		//move loginForm 

	
	
	@RequestMapping("/admin/loginForm")
	public String adminLogin( HttpServletRequest request, Model model,
			@RequestParam("adminID") String adminID,
			@RequestParam("adminPwd") String adminPwd) {
		
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("ref_cursor", null);
		paramMap.put("adminID", adminID);
		as.checkAdmin(paramMap); 	//confirm ID
		
		ArrayList<HashMap<String,Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
		if(list.size()==0) {	
			model.addAttribute("message", "아이디가 없습니다");
			return "admin/adminLoginForm";
		}
		HashMap<String,Object> resultMap = list.get(0);
		if(resultMap.get("PWD")==null) {
			model.addAttribute("message", "관리자에게 문의하세요");
			return "admin/adminLogin";
		}else if( adminPwd.equals((String)resultMap.get("PWD"))) {
			HttpSession session = request.getSession();
			session.setAttribute("loginAdmin", resultMap);
			return "redirect:/adminMembertList";
		}else {
			model.addAttribute("message", "비밀번호가 맞지 않습니다");
			return "admin/admingLogin";
		}
	}	

	
	
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
	public String reportList() {
		return "";
	}
	
	
	
	@RequestMapping("/admin/report/post")
	public String postReportCheck() {
		return "";
	}
	
	
	
	@RequestMapping("/admin/report/story")
	public String storyReportCheck() {
		return "";
	}
	
	
	
	@RequestMapping("/admin/report/handle")
	public String handleReport() {
		return "";
	}


}