package com.ezen.springfeed.tmp.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.springfeed.tmp.service.PostService;
import com.ezen.springfeed.tmp.service.StoryService;
import com.ezen.springfeed.tmp.service.UtilService;

//localhost:8070 테스트용 controller입니다 
@Controller
public class testController {
	
	@Autowired 
	StoryService ss;
	
	@Autowired
	PostService ps;
	
	@Autowired
	UtilService us;
	
	@RequestMapping(value="/login/form")
	public String loginForm() {
		return "member/login";
	}
	
    @RequestMapping("/join/form")
    public String joinForm() {
        return "member/join";
    }
	
    @RequestMapping("/edit/form")
    public String edit() {
        return "member/editProfile";
    }
    
    @RequestMapping("/find/form")
    public String findIdPw() {
    	return "member/findIdPwd";
    }
    
	@RequestMapping("/story/add/form") 
	public String addStoryForm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null)  {
			model.addAttribute("message", "로그인 후 이용해주세요!");
			return "member/login";
		} else 
			return "post/addStory";
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
	

	@RequestMapping("/report/form") 
	public String ReportForm(HttpServletRequest request, Model model, 
			@RequestParam(value="story_num", required=false) String story_num,
			@RequestParam(value="post_num", required=false) String post_num,
			@RequestParam(value="userid", required=false) String userid ){
		
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		
		String url = "";
		if(loginUser==null) {
			model.addAttribute("message", "로그인 후 이용해주세요!");
			url = "member/login";
		}
		else {
			if(userid == null || userid.equals("")) {
				url = "post/report";
				if(story_num != null) 
					model.addAttribute("story_num", story_num);
				else
					model.addAttribute("post_num", post_num);
			} else {
				model.addAttribute("reported", userid);
				url = "post/reportUser";
			}
		}
		return url;
	}
}
