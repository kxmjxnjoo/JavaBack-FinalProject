package com.ezen.springfeed.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
