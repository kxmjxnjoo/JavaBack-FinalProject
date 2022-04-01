package com.ezen.springfeed.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.springfeed.dto.MemberDto;
import com.ezen.springfeed.dto.Paging;
import com.ezen.springfeed.dto.ReportDto;
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
	
	@RequestMapping(value="/admin")
	public String admin() { 
		return "admin/adminLogin";
	} 		// move loginForm 


	@RequestMapping("/admin/loginForm")
	public String adminLogin( HttpServletRequest request, Model model,
			@RequestParam("adminId") String adminId,
			@RequestParam("adminPwd") String adminPwd) {
		
		System.out.println(adminId);
		System.out.println(adminPwd);
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("ref_cursor", null);
		paramMap.put("adminId", adminId);
		as.checkAdmin(paramMap); 	//confirm ID
		
		ArrayList<HashMap<String,Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
		if(list.size()==0) {	
			model.addAttribute("message", "아이디를 확인해주세요");
			return "admin/adminLogin";
		}
		
		HashMap<String,Object> resultMap = list.get(0);
		if(resultMap.get("PASSWORD")==null) {
			model.addAttribute("message", "다른 관리자에게 문의하세요");
			return "admin/adminLogin";
		}else if( adminPwd.equals((String)resultMap.get("PASSWORD"))) {
			HttpSession session = request.getSession();
			session.setAttribute("loginAdmin", resultMap);
			System.out.println("로그인 완");
			return "redirect:/admin/memberList";
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
			mav.setViewName("admin/adminLogin");
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
			mav.setViewName("/admin/member/adminMemberList");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value="/admin/searchMember")
	public String searchMember(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		if(session.getAttribute("loginAdmin") == null) {
			return "admin/admingLogin";
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
			
			String searchMember = "";
			if(request.getParameter("searchMameber")!=null) {
				searchMember = request.getParameter("searchMember");
				session.setAttribute("searchMember", searchMember);
			} else if(session.getAttribute("searchMem") != null) {
				searchMember = (String)session.getAttribute("searchMember");
			} else {
				session.removeAttribute("searchMember");
				searchMember="";
			}
		}
		return "admin/member/adminMemberList";
	}

	
	
	@RequestMapping("/admin/reportList")
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
			as.reportList(paramMap);
			
			ArrayList<HashMap<String,Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
			mav.addObject("reportList", list);
			paramMap.put("reportList", list);
			// 둘 중 뭐지?
			mav.addObject("paging", paging);
			mav.addObject("key", key);
			mav.setViewName("admin/report/adminReportList");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value="/admin/report/post")
	public ModelAndView postReportCheck( @ModelAttribute("dto")ReportDto reportdto, 
			BindingResult result, HttpServletRequest request) {
		
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
			
			String postReportCheck = "";
			paramMap.put("postReportCheck", postReportCheck);
			paramMap.put("ref_cursor", null);
			as.postReportCheck(paramMap);
			
			int report_num = Integer.parseInt(request.getParameter("reportNum"));
			paramMap.put("report_num", report_num);
			paramMap.put("post_num", reportdto.getPost_num());
			mav.setViewName("admin/report/postReportCheck");
			// check Reporeted post
		}
		mav.setViewName("redirect:/adminReportList");
		return mav;
		// back admin reportList
	}
	
	
	
	@RequestMapping(value="/admin/report/story")
	public ModelAndView storyReportCheck(@ModelAttribute("dto")ReportDto reportdto, 
			BindingResult result, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HashMap<String,Object> paramMap = new HashMap<>();
		
		String storyReportCheck = "";
		paramMap.put("storyReportCheck", storyReportCheck);
		paramMap.put("ref_cursor", null);
		as.storyReportCheck(paramMap);
		
		int report_num = Integer.parseInt(request.getParameter("reportNum"));
		paramMap.put("report_num", report_num);
		paramMap.put("post_num", reportdto.getPost_num());
		mav.setViewName("admin/report/storyReportCheck");
		// check Reporeted post
		
		mav.setViewName("redirect:/adminReportList");
		return mav;
		// back admin reportList
	}
	
	
	
	@RequestMapping(value="/admin/report/handle")
	public String handleReport(HttpServletRequest request, 
			@ModelAttribute("rdto") ReportDto reportdto,
			@ModelAttribute("mdto") MemberDto memberdto) {
		
		int report_num = Integer.parseInt(request.getParameter("report_num"));
		System.out.println(report_num);
		int result = 0;
		ReportDto rdto = new ReportDto();
		MemberDto mdto = new MemberDto();
		HashMap<String,Object> paramMap = new HashMap<>();
		
		if(request.getParameter("post_num") != null) {
			int post_num = Integer.parseInt(request.getParameter("post_num"));
			paramMap.put("report_num", report_num);
			request.setAttribute("post_num", post_num);
			request.setAttribute("ReportDto", rdto);
			as.deletePost(paramMap);
			
		} else if(request.getParameter("story_num") != null ) {
			int story_num = Integer.parseInt(request.getParameter("story_num"));
			paramMap.put("report_num", report_num);
			request.setAttribute("story_num", story_num);
			as.deleteStory(paramMap);
		} else {
			String userid = request.getParameter("userid");
			paramMap.put("report_num", report_num);
			request.setAttribute("userid", userid);
			as.blockUser(paramMap);
			
		}
		return "redirect:/admin/report/adminReportList";
	}
}