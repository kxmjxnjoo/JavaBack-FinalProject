package com.ezen.springfeed.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.springfeed.service.StoryService;

@Controller
public class testController {
	
	@Autowired 
	StoryService ss;
	
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
	
	@RequestMapping("/story/edit/form")
	public String editStory(@RequestParam("story_num") int story_num, 
			HttpServletRequest request, Model model, RedirectAttributes rttr) {

		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(loginUser== null) {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요.");
			return "redirect:http://localhost:3000/";
		} else {
			System.out.println(story_num);
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("ref_cursor", null);
			paramMap.put("story_num", story_num);
			paramMap.put("fontcolor", "");
			
			ss.getStory(paramMap);
			
			ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			HashMap<String, Object> resultMap = list.get(0);
			
			System.out.println("스토리번호 : " + resultMap.get("STORY_NUM"));
			System.out.println("폰트 컬러 : " + paramMap.get("FONTCOLOR"));
			System.out.println("이미지 : " + resultMap.get("STORY_IMG"));
			System.out.println("콘텐츠 : " + resultMap.get("STORY_CONTENT"));
			model.addAttribute("StoryDto", resultMap);
			model.addAttribute("fontcolor", (String)paramMap.get("fontcolor"));
			return "post/editStory";
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
}
