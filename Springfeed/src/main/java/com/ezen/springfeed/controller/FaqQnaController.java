package com.ezen.springfeed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.springfeed.service.FaqQnaService;

@Controller
public class FaqQnaController {

	@Autowired
	FaqQnaService fas;
	
	
	@RequestMapping("/admin/faq")
	public String adminFaqList() {
		return "";
	}
	

	
	@RequestMapping("/faq/add")
	public String addFaq() {
		return "";
	}
	
	
	
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
	public String addQna() {
		return "";
	}
	
	
	
	@RequestMapping("/qna/add/form")
	public String addQnaForm() {
		return "";
	}
	
	
	
	@RequestMapping("/qna/edit")
	public String editQna() {
		return "";
	}
	
	
	
	@RequestMapping("/qna/delete")
	public String deleteQna() {
		return "";
	}
	
	
	
	@RequestMapping("/faq?userid=")
	public String userFaq() {
		return "";
	}
	
	
}