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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.springfeed.dto.MemberDto;
import com.ezen.springfeed.dto.Paging;
import com.ezen.springfeed.dto.PostDto;
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
	
	
	@RequestMapping(value="/admin/loginForm", method=RequestMethod.POST)
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
			return "redirect:/admin/memberList";
		}else {
			model.addAttribute("message", "비밀번호가 틀렸습니다");
			return "admin/admingLogin";
		}
	}	
	
	
	
    @RequestMapping("/logoutAdmin")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginAdmin");
        return "redirect:/";
    }
	
    
    
	@RequestMapping("/admin/memberList")
	public ModelAndView memberList(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		System.out.println("B");
		HttpSession session = request.getSession();
		HashMap<String, Object> loginAdmin 
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		String adminId = (String)loginAdmin.get("ADMINID");
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
			System.out.println(adminId);
			paramMap.put("adminId", adminId);
			paramMap.put("cnt", 0);
			paramMap.put("key", key);
			as.getAllCount(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
			paging.setTotalCount(cnt);
			System.out.println(cnt);
			paging.paging();
			
			System.out.println(paging.getStartNum());
			System.out.println(paging.getEndNum());
			System.out.println(key);
			paramMap.put("startNum", paging.getStartNum());
			paramMap.put("endNum", paging.getEndNum());
			paramMap.put("key", key);
			paramMap.put("ref_cursor", null);
			as.memberList(paramMap);
			
			ArrayList<HashMap<String,Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			mav.addObject("mdto", list);
			mav.addObject("paging", paging);
			mav.addObject("key", key);
			mav.setViewName("/admin/member/adminMemberList");
		}
		return mav;
	}
	
	
	
	@RequestMapping("/admin/searchMember")
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
			} else if(session.getAttribute("searchMember") != null) {
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
		ReportDto rdto = new ReportDto();
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginAdmin 
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		if(session.getAttribute("loginAdmin") == null) {
			mav.setViewName("admin/adminLogin");
		} else {
			int page = 1;
			String key = "";
			if(request.getParameter("first")!=null) {
				session.removeAttribute("page");
			}
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			} else {
				session.removeAttribute("page");
			}
			Paging paging = new Paging();
			paging.setPage(page);
			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("cnt", 0);
			as.getAllCount_r(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
			paging.setTotalCount(cnt);
			paging.paging();
			
			System.out.println(paging.getStartNum());
			System.out.println(paging.getEndNum());
			System.out.println(key);
			paramMap.put("startNum", paging.getStartNum());
			paramMap.put("endNum", paging.getEndNum());
			paramMap.put("ref_cursor", null);
			as.reportList(paramMap);
			
			ArrayList<HashMap<String,Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			mav.addObject("rdto", list);
			System.out.println(list);
			mav.addObject("paging", paging);
			mav.setViewName("admin/report/adminReportList");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value="/admin/report/post")
	public ModelAndView postReportCheck( @ModelAttribute("rdto")ReportDto reportdto, 
			BindingResult result, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginAdmin") == null) {
			mav.setViewName("admin/admingLogin");
		} else {
			HashMap<String,Object> paramMap = new HashMap<>();
			
			System.out.println(request.getParameter("post_num"));
			int post_num = Integer.parseInt(request.getParameter("post_num"));
			int report_num = Integer.parseInt(request.getParameter("report_num"));
			paramMap.put("post_num", post_num);
			paramMap.put("cnt", 0);
			as.postReportCheck(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
		if(cnt>0) {
			paramMap.put("report_num", report_num);
			paramMap.put("handled", "y");
			// UPDATE 상태변경.
			as.updateReportPostBlock(paramMap);
			}
		}
		mav.setViewName("redirect:/admin/reportList");
		return mav;
		// back admin reportList
	}
	
	
	
	@RequestMapping(value="/admin/report/story")
	public ModelAndView storyReportCheck( @ModelAttribute("rdto")ReportDto reportdto, 
			BindingResult result, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginAdmin") == null) {
			mav.setViewName("admin/admingLogin");
		} else {
			HashMap<String,Object> paramMap = new HashMap<>();
			
			System.out.println(request.getParameter("post_num"));
			int story_num = Integer.parseInt(request.getParameter("story_num"));
			int report_num = Integer.parseInt(request.getParameter("report_num"));
			paramMap.put("story_num", story_num);
			paramMap.put("cnt", 0);
			as.storyReportCheck(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
		if(cnt>0) {
			paramMap.put("report_num", report_num);
			paramMap.put("handled", "y");
			// UPDATE 상태변경.
			as.updateReportStoryBlock(paramMap);
			}
		}
		mav.setViewName("redirect:/admin/reportList");
		return mav;
		// back admin reportList
	}
	
	
	
	@RequestMapping(value="/admin/report/user")
	public ModelAndView userReportCheck( @ModelAttribute("rdto") ReportDto reportdto, 
			BindingResult result, HttpServletRequest request) {
		
		System.out.println("userid 전달 확인 : " +reportdto.getReported_id());
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginAdmin") == null) {
			mav.setViewName("admin/admingLogin");
		} else {
			HashMap<String,Object> paramMap = new HashMap<>();
			
			paramMap.put("userid", request.getParameter("reported_id"));
			//paramMap.put("cnt", 0);
			as.userReportCheck(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
			System.out.println(request.getParameter("reported_id") 
					+ "   " + cnt);
			
		if(cnt>0) {
			paramMap.put("report_num", reportdto.getReport_num());
			// UPDATE 상태변경.`
			as.updateReportUserBlock(paramMap);
			}
		}
		mav.setViewName("redirect:/admin/reportList");
		return mav;
		// back admin reportList
	}
}