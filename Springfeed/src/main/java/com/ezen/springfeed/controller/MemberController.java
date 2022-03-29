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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    	
    	String url = "member/login";
    	
    	System.out.println(memberdto.getUserid());
    	if(result.getFieldError("userid")!= null) {
    		model.addAttribute("message", result.getFieldError("userid").getDefaultMessage());
    	} else if(result.getFieldError("userpwd")!= null) {
    		model.addAttribute("message", result.getFieldError("userpwd").getDefaultMessage());
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
    			
    		} else if (memberdto.getUserpwd().equals((String)mvo.get("PWD"))) {
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
    @RequestMapping("/join/form")
    public String joinForm() {
        return "member/join";
    }
    
    
    //아이디 체크
    @PostMapping("/join/idCheck")
    @ResponseBody
    public int idCheck(@RequestParam("joinId") String id) {
    	System.out.println("userIdCheck 진입");
    	System.out.println("전달 받은 id" + id);
    	//int cnt = ms.idCheck(id);
    	//System.out.println("확인 결과 : " + cnt );
    	
    	return 0;
       
    }
    
    // 회원가입 액션
    @RequestMapping(value="/join", method=RequestMethod.POST)
    public String join(@ModelAttribute("dto") @Valid MemberDto memberdto,
    		BindingResult result,
    		@RequestParam(value="reid", required=false) String reid,
    		HttpServletRequest request, Model model) {
    	
    	model.addAttribute("reid", reid);
    	String url = "member/join";
    	
    	if(result.getFieldError("userid")!= null) {
            model.addAttribute("message", result.getFieldError("userid").getDefaultMessage());
         } else if(result.getFieldError("userpwd")!= null) {
            model.addAttribute("message", result.getFieldError("userpwd").getDefaultMessage());
         } else if(result.getFieldError("name")!= null) {
            model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
         } else if(result.getFieldError("email")!= null) {
            model.addAttribute("message", result.getFieldError("email").getDefaultMessage());
         } else if(result.getFieldError("phone")!= null) {
            model.addAttribute("message", result.getFieldError("phone").getDefaultMessage());
         } else if(reid==null || (reid != null && !reid.equals(memberdto.getUserid()))) {
 			model.addAttribute("message", "아이디 중복체크를 하지 않으셨습니다.");
 		}  else {
 			
 			HashMap<String, Object> paramMap = new HashMap<>();
 			paramMap.put("userid",memberdto.getUserid());
 			paramMap.put("pwd",memberdto.getUserpwd());
 			paramMap.put("name",memberdto.getName());
 			paramMap.put("email",memberdto.getEmail());
 			paramMap.put("phone",memberdto.getPhone());
 			
 			ms.insertMember(paramMap);
 			
 			model.addAttribute("message", "회원가입이 완료되었어요:) 로그인 후 이용해주세요.");
 			url = "member/login";
 		}
    	
        return url;
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
