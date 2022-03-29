package com.ezen.springfeed.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.springfeed.dto.QnaDto;
import com.ezen.springfeed.service.FaqQnaService;

@Controller
public class FaqQnaController {

	@Autowired
	FaqQnaService fqs;
	

	@RequestMapping("/admin/faq")
	public String adminFaqList() {
		return "faq/adminFaqList";
	}
	
	
	
	@RequestMapping("/faq/add")
		public String addFaq( ) {
		return "userfaqqna/faq";
	}
	
	/*
	
	@RequestMapping("/faq/add/form")
	public String addFaqForm() {
		return "";
	}
	
	
	
	@RequestMapping("/faq/edit/form")
	public String editFaqForm() {
		return "";
	}
	
	
	
	@RequestMapping("/faq/delete?num=")
	public String deleteFaq() {
		return "";
	}
	
	
	
	@RequestMapping("/admin/qna")
	public String qnaList() {
		return "";
	}
	
	
	
	@RequestMapping("/qna/detail?num=")
	public String qnaView() {
		return "";
	}
	
	
	
	@RequestMapping("/qna/detail?num=")
	public String editQnaAction() {
		return "";
	}
	
	
	
	@RequestMapping("/qna?userid=")
	public String userQna() {
		return "";
	}
	
	
	
	@RequestMapping("/qna/add")
	public String addQna( ) {
		return "qna/qnaView";
	}
	
	
	*/
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
	    	fqs.addQna(paramMap);
	    	mav.setViewName("redirect:/qnaList");
	    }
		return mav;
	}
	
	
	/*
	@RequestMapping("/qna/edit")
	public String editQna() {
		return "";
	}
	
	
	
	@RequestMapping("/qna/delete")
	public String deleteQna() {
		return "";
	}
	
	
	
	@RequestMapping("/faqList")
	public String faqList() {
		return "userfaqqna/faq";
	}
	*/
}