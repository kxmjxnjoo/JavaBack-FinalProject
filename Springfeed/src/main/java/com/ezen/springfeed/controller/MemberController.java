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

import com.ezen.springfeed.dto.MemberDto;
import com.ezen.springfeed.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	MemberService ms;
	
	//로그인 폼으로
	@RequestMapping(value="/login/form")
	public String loginForm() {
		return "member/login";
	}
    
    //로그인 액션
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@ModelAttribute("dto") @Valid MemberDto memberdto,
    		BindingResult result, HttpServletRequest request,
    		Model model) {
    	
    	String url = "member/loginForm";
    	
    	System.out.println(memberdto.getUserid());
    	if(result.getFieldError("userid")!= null) {
    		model.addAttribute("message", result.getFieldError("userid").getDefaultMessage());
    	} else if(result.getFieldError("pwd")!= null) {
    		model.addAttribute("message", result.getFieldError("pwd").getDefaultMessage());
    	} else {
    		HashMap<String, Object> paramMap = new HashMap<>();
    		paramMap.put("userid", memberdto.getUserid());
    		paramMap.put("ref_cursor", null);
    		ms.getMember(paramMap);
    		
    		ArrayList<HashMap<String, Object>> list
    			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
    		if(list.size() == 0) {
    			model.addAttribute("message", "잘못된 사용자 아이디입니다. 다시 확인하세요.");
    			return "member/login";
    		} 
    		
    		HashMap<String, Object> mvo = list.get(0);
    		if(mvo.get("PWD") == null) {
    			model.addAttribute("message", "로그인에 문제가 발생했어요:( QnA를 남겨주시면 빠르게 해결해드릴게요!");
    			//고객센터로 연결하는 버튼 모달 만들기
    			
    		} else if (memberdto.getPassword().equals((String)mvo.get("PWD"))) {
    			HttpSession session = request.getSession();
    			session.setAttribute("loginUser", mvo);
    			url = "redirect:/";
    		} else {
    			model.addAttribute("message", "잘못된 비밀번호입니다. 다시 확인하세요.");
    		}
    	}
        return url;
    }

    //로그아웃
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

    
    //이용약관
    
    

    // 회원가입 폼
    @RequestMapping("/joinForm")
    public String joinForm() {
        return "member/joinForm";
    }
    
    // 회원가입 액션
    @RequestMapping("/join")
    public String join() {
        return "";
    }


    // TODO : implement
    @RequestMapping("/follow")
    public String follow() {
        return "";
    }

    // TODO : implement
    @RequestMapping("/unfollow")
    public String unfollow() {
        return "";
    }
}
