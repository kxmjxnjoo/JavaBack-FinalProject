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
import com.ezen.springfeed.dto.ReportDto;
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
		HashMap<String,Object> loginAdmin
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		System.out.println("faqList Login");
	    if (loginAdmin == null) {
	    	mav.setViewName("admin/admingLogin");
	    	return mav;
	    } else {
	    	int page = 1;
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
			paramMap.put("startNum", paging.getStartNum());
			paramMap.put("endNum", paging.getEndNum());
			paramMap.put("ref_cursor", null);
			fqs.adminFaqList(paramMap);
			
			ArrayList<HashMap<String,Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
	    	paramMap.put("ref_cursor", null);
	    	fqs.adminFaqList(paramMap);
	    	mav.addObject("fdto", list);
	    	System.out.println(list);
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
			mav.setViewName("admin/faq/addFaq");
		}
		return mav;
	}
	
	
	
	@RequestMapping(value="/faq/add/form",  method=RequestMethod.POST)
	public ModelAndView addFaqForm( @ModelAttribute("fdto") @Valid FaqDto faqdto,
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
	
	
	
	@RequestMapping("/faq/edit")
	public ModelAndView faqEdit(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String,Object> loginAdmin
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		HashMap<String,Object> paramMap = new HashMap();
		if (loginAdmin == null) mav.setViewName("admin/admingLogin");
	    else {
	    	mav.setViewName("admin/faq/editFaq");
	    	fqs.faqEdit(paramMap);
	    }
		mav.setViewName("redirect:/admin/faqList");
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
	public ModelAndView qnaList(HttpServletRequest request, Model model,  
			@ModelAttribute("qdto") @Valid QnaDto qnadto, BindingResult result) {
		System.out.println(11111);
		System.out.println(qnadto);
		ModelAndView mav = new ModelAndView();
		QnaDto qdto = new QnaDto();
		int qna_num = qdto.getQna_num();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginAdmin 
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		if(session.getAttribute("loginAdmin") == null) {
			mav.setViewName("admin/adminLogin");
		} else {
			System.out.println("Q&A로그인 확인");
			int page = 1;
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
			paramMap.put("qna_num", qna_num);
			paramMap.put("cnt", 0);
			System.out.println(paramMap);
			as.getAllCount_q(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
			paging.setTotalCount(cnt);
			paging.paging();
			
			System.out.println(paging.getStartNum());
			System.out.println(paging.getEndNum());
			paramMap.put("startNum", paging.getStartNum());
			paramMap.put("endNum", paging.getEndNum());
			paramMap.put("ref_cursor", null);
			fqs.adminQnaList(paramMap);
			
			ArrayList<HashMap<String,Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			mav.addObject("qdto", list);
			mav.addObject("paging", paging);
			mav.setViewName("admin/adminQna/adminQnaList");
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
	public ModelAndView userQna(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if(loginUser==null) {
			model.addAttribute("message", "로그인 후 이용해주세요!");
			 mav.setViewName("member/login");
		}
		else {
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("userid", loginUser.get("USERID"));
			paramMap.put("ref_cursor", null);
			paramMap.put("ref_cursor2", null);
			
			fqs.getQnaList(paramMap);
			fqs.getAllQna(paramMap);
			
			ArrayList<HashMap<String, Object>> userQnaList 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			ArrayList<HashMap<String, Object>> AllQnaList 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");
			
			mav.addObject("userQnaList", userQnaList);
			mav.addObject("qnaList", AllQnaList);
			mav.setViewName("userFaqQna/qna");
		}
		return mav;
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

			System.out.println(qnadto.getQna_subject());
	    	System.out.println(qnadto.getQna_content());
	    	
	    	HashMap<String,Object> paramMap = new HashMap<>();
	    	paramMap.put("qna_num", 0);
	    	paramMap.put("userid", loginUser.get("USERID"));
	    	paramMap.put("subject", qnadto.getQna_subject());
	    	paramMap.put("content", qnadto.getQna_content());
	    	fqs.addQna(paramMap);

	    	int qna_num = Integer.parseInt(paramMap.get("qna_num").toString());
	    	
	    	rttr.addFlashAttribute("message", "빠르게 답변해드릴게요!");
	    	return "redirect:/qna?userid="+loginUser.get("USERID");
			//return "redirect:/qna/view?qna_num="+qna_num;
		}
	}
	
	
	
	@RequestMapping("/qna/add/form")
	public String addQnaForm(HttpServletRequest request, RedirectAttributes rttr) {
		
		HttpSession session = request.getSession();
		HashMap<String,Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if (loginUser == null) {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요!");
			return "redirect:/login/form";
		}
	    else {
	    	return "userFaqQna/addQna";
	    }
	}
	
	
	
	@RequestMapping("/qna/view") 
	public ModelAndView viewQna(HttpServletRequest request, RedirectAttributes rttr,
			@RequestParam("qna_num") String qna_num) {
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		HashMap<String,Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if (loginUser == null) {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요!");
			mav.setViewName("redirect:/login/form");
		} else {
	    	HashMap<String, Object> paramMap = new HashMap<>();
	    	paramMap.put("qna_num", qna_num);
	    	paramMap.put("ref_cursor", null);
	    	
	    	fqs.getQna(paramMap);
	    	
	    	ArrayList<HashMap<String,Object>> list 
	    		= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
	    	
	    	if(list.size() == 0) {
	    		rttr.addFlashAttribute("message", "존재하지 않는 QNA 게시물 입니다.");
	    		mav.setViewName("redirect:/");
	    	} else {
	    		HashMap<String,Object> resultMap = list.get(0);
	    		
	    		mav.addObject("qna", resultMap);
	    		mav.setViewName("userFaqQna/qnaView");
	    	}
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