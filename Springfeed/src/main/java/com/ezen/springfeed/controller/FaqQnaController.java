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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.springfeed.dto.FaqDto;
import com.ezen.springfeed.dto.QnaDto;
import com.ezen.springfeed.service.FaqQnaService;

@Controller
public class FaqQnaController {

	@Autowired
	FaqQnaService fqs;
	
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
	public String addFaq(HttpServletRequest request, Model model) {
	
		
	return "";
	}
	

	
	@RequestMapping("/faq/add/form")
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
		    	mav.setViewName("redirect:/faqList");
		    }
			return mav;
		}

	
	
	@RequestMapping("/faq/edit/form")
	public String editFaqForm() {
		return "editFaq";
	}
	
	
	
	@RequestMapping("/faq/delete")
	public String deleteFaq(	@RequestParam("faqnum") FaqDto faq_num) {
			
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("faqnum", faq_num);
			fqs.deleteFaq(paramMap);
			
		return "redirect:/adminFaqList";
	}
	
	

	@RequestMapping("/admin/qnaList")
	public String qnaList() {
		return "qnaView";
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
	public String userQna() {
		return "userFaqQna/qna";
	}
	
	
	
	@RequestMapping("/qna/add")
	public String addQna(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if(loginUser==null) return "member/login";
		
		return "qna/qnaView";
	}
	
	

	@RequestMapping("/qna/add/form")
	public ModelAndView addQnaForm( @ModelAttribute("dto") @Valid QnaDto qnadto,
			BindingResult result, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String,Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if (loginUser == null) mav.setViewName("member/login");
	    else {
	    	if(result.getFieldError("subject") != null) {
	    		mav.addObject("message", "제목을 입력하세요");
	    		mav.setViewName("qna/qnaWrite");
	    		return mav;
	    	} else if(result.getFieldError("content") != null) {
	    		mav.addObject("message", "내용을 입력하세요");
	    		mav.setViewName("redirect:/addQna");
	    		return mav;
	    	}
	    	
	    	HashMap<String,Object> paramMap = new HashMap<>();
	    	paramMap.put("id", loginUser.get("USERID"));
	    	paramMap.put("subject", qnadto.getQna_subject());
	    	paramMap.put("content", qnadto.getQna_content());
	    	//fqs.addQna(paramMap);
	    	mav.setViewName("redirect:/qnaList");
	    }
		return mav;
	}
	
	
	
	@RequestMapping("/qna/edit")
	public String editQna() {
		return "editQna";
	}
	
	
	
	@RequestMapping("/qna/delete")
	public String deleteQna(@RequestParam("qnanum") QnaDto qna_num) {
			
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("qnanum", qna_num);
		fqs.deleteQna(paramMap);
		
		return "redirect:/qnaList";
	}
	
	

	@RequestMapping("/faqList")
	public String faqList() {
		return "userfaqqna/faq";
	}
}