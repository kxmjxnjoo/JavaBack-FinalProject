package com.ezen.springfeed.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class testController {
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
}
