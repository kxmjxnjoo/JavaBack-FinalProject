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
import org.springframework.web.servlet.ModelAndView;

import com.ezen.springfeed.dto.Paging;
import com.ezen.springfeed.service.AdminService;
import com.ezen.springfeed.service.FaqQnaService;
import com.ezen.springfeed.service.PostService;
import com.ezen.springfeed.service.StoryService;


@Controller
public class AdminController {
	
	@Autowired
	AdminService as;

	@Autowired
	PostService ps;
	
	@Autowired
	StoryService ss;
	
	@Autowired
	FaqQnaService fqs;

	
	@RequestMapping("/admin/login")
	public String adminLogin() { 
		return "admin/adminLogin";
	} 		//move loginForm 

	
	@RequestMapping("/admin/loginForm")
	public String adminLogin( HttpServletRequest request, Model model,
			@RequestParam("adminId") String adminId,
			@RequestParam("adminPwd") String adminPwd) {
		
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("ref_cursor", null);
		paramMap.put("adminId", adminId);
		as.checkAdmin(paramMap); 	//confirm ID
		
		ArrayList<HashMap<String,Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
		if(list.size()==0) {	
			model.addAttribute("message", "아이디를 확인해주세요");
			return "admin/adminLoginForm";
		}
		HashMap<String,Object> resultMap = list.get(0);
		if(resultMap.get("PWD")==null) {
			model.addAttribute("message", "다른 관리자에게 문의하세요");
			return "admin/adminLogin";
		}else if( adminPwd.equals((String)resultMap.get("PWD"))) {
			HttpSession session = request.getSession();
			session.setAttribute("loginAdmin", resultMap);
			return "redirect:/adminMembertList";
		}else {
			model.addAttribute("message", "비밀번호가 틀렸습니다");
			return "admin/admingLogin";
		}
	}	

	
	
	@RequestMapping("/admin/memberList")
	public ModelAndView memberList(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginAdmin") == null) {
			mav.setViewName("admin/admingLogin");
		} else {
			int page = 1;
			String key = "";
			if(request.getParameter("first")!=null) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			} else {
				session.removeAttribute("page");
			}
			if(request.getParameter("key")!=null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if(session.getAttribute("key")!=null) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
		}
			Paging paging = new Paging();
			paging.setPage(page);
			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("cnt", 0);
			paramMap.put("key", key);
			as.getAllCount(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
			paging.setTotalCount(cnt);
			paging.paging();
			
			paramMap.put("startNum", paging.getStartNum());
			paramMap.put("endNum", paging.getEndNum());
			paramMap.put("key", key);
			paramMap.put("ref_cursor", null);
			as.memberList(paramMap);
			
			ArrayList<HashMap<String,Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
			mav.addObject("memberList", list);
			mav.addObject("paging", paging);
			mav.addObject("key", key);
			mav.setViewName("admin/adminMemberList");
		
		}
		return mav;
	}

	
	
	@RequestMapping("/admin/searchMember")
	public ModelAndView searchMember(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginAdmin") == null) {
			mav.setViewName("admin/admingLogin");
		} else {
			int page = 1;
			String key = "";
			if(request.getParameter("first")!=null) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			} else {
				session.removeAttribute("page");
			}
			if(request.getParameter("key")!=null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if(session.getAttribute("key")!=null) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
		}
			Paging paging = new Paging();
			paging.setPage(page);
			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("cnt", 0);
			paramMap.put("key", key);
			as.getAllCount(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
			paging.setTotalCount(cnt);
			paging.paging();
			//page
			as.searchMember(paramMap);

			mav.setViewName("admin/adminMemberList");
		}
		return mav;
	}

	
	
	@RequestMapping("/admin/report")
	public ModelAndView reportList(HttpServletRequest request, Model model) {
		
		ModelAndView mav = new ModelAndView();
	
		HttpSession session = request.getSession();
		if(session.getAttribute("loginAdmin") == null) {
			mav.setViewName("admin/admingLogin");
		} else {
			int page = 1;
			String key = "";
			if(request.getParameter("first")!=null) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			} else {
				session.removeAttribute("page");
			}
			if(request.getParameter("key")!=null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if(session.getAttribute("key")!=null) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
			}
			Paging paging = new Paging();
			paging.setPage(page);
			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("cnt", 0);
			paramMap.put("key", key);
			as.getAllCount(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
			paging.setTotalCount(cnt);
			paging.paging();
			
			paramMap.put("startNum", paging.getStartNum());
			paramMap.put("endNum", paging.getEndNum());
			paramMap.put("key", key);
			paramMap.put("ref_cursor", null);
			//as.reportList(paramMap);
			
			ArrayList<HashMap<String,Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
			mav.addObject("reportList", list);
			mav.addObject("paging", paging);
			mav.addObject("key", key);
			mav.setViewName("redirect:/adminReportList");
		}
		return mav;
	}
	
	
	
	@RequestMapping("/admin/report/post")
	public ModelAndView postReportCheck(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginAdmin") == null) {
			mav.setViewName("admin/admingLogin");
		} else {
			int page = 1;
			String key = "";
			if(request.getParameter("first")!=null) {
				session.removeAttribute("page");
				session.removeAttribute("key");
			}
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			} else {
				session.removeAttribute("page");
			}
			if(request.getParameter("key")!=null) {
				key = request.getParameter("key");
				session.setAttribute("key", key);
			} else if(session.getAttribute("key")!=null) {
				key = (String)session.getAttribute("key");
			} else {
				session.removeAttribute("key");
			}
			Paging paging = new Paging();
			paging.setPage(page);
			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("cnt", 0);
			paramMap.put("key", key);
			as.getAllCount(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
			paging.setTotalCount(cnt);
			paging.paging();
			
			paramMap.put("startNum", paging.getStartNum());
			paramMap.put("endNum", paging.getEndNum());
			paramMap.put("key", key);
			//paramMap.put("postReportCheck", postReportCheck);
			paramMap.put("ref_cursor", null);
			//as.postReportCheck(paramMap);
			
			mav.setViewName("redirect:/adminReportList");
		}
		return mav;
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