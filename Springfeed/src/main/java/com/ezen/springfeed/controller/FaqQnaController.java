package com.ezen.springfeed.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.springfeed.dto.FaqDto;
import com.ezen.springfeed.dto.Paging;
import com.ezen.springfeed.dto.QnaDto;
import com.ezen.springfeed.service.AdminService;
import com.ezen.springfeed.service.FaqQnaService;

@Controller
public class FaqQnaController {

	@Autowired
	FaqQnaService fqs;
	
	@Autowired
	AdminService as;

	@RequestMapping("/admin/faqList")
	public ModelAndView adminFaqList(Model model, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		HashMap<String,Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		
	    if (loginUser == null) {
	    	mav.setViewName("admin/admingLogin");
	    	return mav;
	    } else {
	    	HashMap<String,Object> paramMap = new HashMap<>();
	    	paramMap.put("userid", loginUser.get("USERID"));
	    	paramMap.put("ref_cursor", null);
	    	fqs.adminFaqList(paramMap);
	    	
	    	ArrayList<HashMap<String,Object>> list
	    		= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
	    	mav.addObject("adminfaqList", list);
	    	mav.setViewName("admin/faq/adminFaqList");
	    }
		return mav;
	}
	
	
	
	@RequestMapping("/faq/add")
	public ModelAndView addFaq(HttpServletRequest request, Model model) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String,Object> loginAdmin
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		if (loginAdmin == null) {
			mav.setViewName("admin/admingLogin");
		} else {
			mav.setViewName("admin/faq/faqList");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value="/faq/add/form",  method=RequestMethod.POST)
	public ModelAndView addFaqForm( @ModelAttribute("dto") @Valid FaqDto faqdto,
			BindingResult result, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String,Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		if (loginUser == null) mav.setViewName("admin/admingLogin");
	    else {
	    	if(result.getFieldError("subject") != null) {
	    		mav.addObject("message", "제목을 입력하세요");
	    		mav.setViewName("faq/addFaq");
	    		return mav;
	    	} else if(result.getFieldError("content") != null) {
	    		mav.addObject("message", "내용을 입력하세요");
	    		mav.setViewName("faq/addFaq");
	    		return mav;
	    	}
	    	
	    	HashMap<String,Object> paramMap = new HashMap<>();
	    	paramMap.put("id", loginUser.get("USERID"));
	    	paramMap.put("subject", faqdto.getFaq_subject());
	    	paramMap.put("content", faqdto.getFaq_content());
	    	fqs.addFaq(paramMap);
	    	mav.setViewName("redirect:/admin/faqList");
	    }
		return mav;
	}
	
	
	
	@RequestMapping("/faq/edit/form")
	public ModelAndView editFaqForm(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String,Object> loginAdmin
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		HashMap<String,Object> paramMap = new HashMap();
		if (loginAdmin == null) mav.setViewName("admin/admingLogin");
	    else {
	    	fqs.faqEdit(paramMap);
	    	mav.setViewName("redirect:/admin/faqList");
	    }
		return mav;
	}
	
	
	
	@RequestMapping("/faq/delete")
	public String deleteFaq(	@RequestParam("faqnum") FaqDto faq_num) {
			
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("faqnum", faq_num);
			fqs.deleteFaq(paramMap);
			
		return "redirect:/admin/faqList";
	}
	
	
	
	@RequestMapping("/admin/qnaList")
	public ModelAndView qnaList(HttpServletRequest request, Model model) {
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
			fqs.qnaList(paramMap);
			
			ArrayList<HashMap<String,Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
			mav.addObject("qnaList", list);
			mav.addObject("paging", paging);
			mav.addObject("key", key);
			mav.setViewName("/admin/adminQna/qnaView");
		}
		return mav;
	}
	
	
	
	@RequestMapping("/qna/detail")
	public String qnaView() {
		return "";
	}
	

	
	@RequestMapping("/qna/reply")
	public String qnaReply() {
		return "adminQna/qna";
	}
	
	
	
	@RequestMapping("/qna")
	public String userQna(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if(loginUser==null) return "member/login";
		else {
			return "userFaqQna/qna";
		}
	}
	
	
	
	@RequestMapping(value="/qna/add", method=RequestMethod.POST)
	public String addQna(@ModelAttribute("qdto") @Valid QnaDto qnadto,
			BindingResult result, Model model,
			HttpServletRequest request, RedirectAttributes rttr) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if(loginUser==null) {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요!");
			return "member/login";
		}
		else {

	    	System.out.println(qnadto.getQna_content());
	    	System.out.println(qnadto.getQna_subject());
	    	
	    	HashMap<String,Object> paramMap = new HashMap<>();
	    	paramMap.put("userid", loginUser.get("USERID"));
	    	paramMap.put("subject", qnadto.getQna_subject());
	    	paramMap.put("content", qnadto.getQna_content());
	    	paramMap.put("qna_num", 0);
	    	fqs.addQna(paramMap);
	    	
	    	int qna_num = Integer.parseInt(paramMap.get("qna_num").toString());
			return "redirect:/qna/view?qna_num="+qna_num;
		}
	}
	
	@RequestMapping("/qna/view")
	public ModelAndView qnaView(HttpServletRequest request, RedirectAttributes rttr) {
		ModelAndView mav = new ModelAndView();
	
		return mav;
	}
	

	@RequestMapping("/qna/add/form")
	public ModelAndView addQnaForm(HttpServletRequest request, RedirectAttributes rttr) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String,Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if (loginUser == null) {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요!");
			mav.setViewName("redirect:/login/form");
		}
	    else {
	    	mav.setViewName("userFaqQna/addQna");
	    }
		return mav;
	}
	
	
	
	@RequestMapping("/qna/edit")
	public ModelAndView editQna(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String,Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		HashMap<String,Object> paramMap = new HashMap();
		if (loginUser == null) mav.setViewName("member/login");
	    else {
	    	fqs.qnaEdit(paramMap);
	    	mav.setViewName("redirect:/qna/edit");
	    }
		return mav;
	}
	
	
	
	@RequestMapping("/qna/delete")
	public String deleteQna(@RequestParam("qna_num") QnaDto qna_num) {
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("qnanum", qna_num);
		fqs.deleteQna(paramMap);
		
		return "redirect:/qnaList";
	}
	
	
	
	@RequestMapping("/faq")
	public ModelAndView faqList() {
		
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("ref_cursor", null);
		
		fqs.faqList(paramMap);
		
		ArrayList<HashMap<String, Object>> list
        = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
		mav.addObject("faqList", list);
		mav.setViewName("userFaqQna/faq");
		
		return mav;
	}
}