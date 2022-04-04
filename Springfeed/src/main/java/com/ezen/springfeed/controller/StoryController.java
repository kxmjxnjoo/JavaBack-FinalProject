package com.ezen.springfeed.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.springfeed.dto.StoryDto;
import com.ezen.springfeed.service.MemberService;
import com.ezen.springfeed.service.StoryService;

@Controller
public class StoryController {

	@Autowired
	StoryService ss;
	
	@Autowired
	MemberService ms;
	
	
	@RequestMapping("/story/add/form") 
	public String addStoryForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null)  
			return "member/login";
		else 
			return "post/addStory";
	}
	
	@RequestMapping("/story/add") 
	public String addStory(@ModelAttribute("dto") @Valid StoryDto storydto,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null)  
			return "member/login";
		else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("story_img", storydto.getStory_img());
			paramMap.put("content", storydto.getStory_content());
			paramMap.put("fontcolor", storydto.getFontColor());
			paramMap.put("userid", loginUser.get("USERID"));
			paramMap.put("story_num", 0);
			
			ss.addStory(paramMap);
			
			int story_num = Integer.parseInt(paramMap.get("story_num").toString());
			
			return "redirect:/story?story_num="+story_num;
		}
	}
	
	
	
	@RequestMapping("/story")
	public ModelAndView storyDetail(@RequestParam("story_num") int story_num, 
			HttpServletRequest request, Model model, RedirectAttributes rttr) {
		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null)  
			mav.setViewName("redirect:/login/form");
		else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("story_num", story_num);
			paramMap.put("prev", 0);
			paramMap.put("next", 0);
			
			ss.getStory(paramMap);
			
			ArrayList<HashMap<String, Object>> list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			
			if(list.size() == 0) {
				
				rttr.addFlashAttribute("message", "존재하지 않는 스토리 입니다.");
			
				mav.setViewName("redirect:/");
				
			} else {
				
				HashMap<String, Object> resultMap = list.get(0);
				mav.addObject("StoryDto", resultMap);
				
				if(paramMap.get("prev") == null) 
					mav.addObject("prev", 0);
				else 
					mav.addObject("prev", Integer.parseInt(paramMap.get("prev").toString()));
				
				if(paramMap.get("next") == null)
					mav.addObject("next", 0);
				else
					mav.addObject("next", Integer.parseInt(paramMap.get("next").toString()));

				
				
				//팔로우 검사
				HashMap<String, Object> followMap = new HashMap<>();
				followMap.put("followed", resultMap.get("USERID"));
				followMap.put("follower", loginUser.get("USERID"));
				followMap.put("result", 0);
				
				ms.getFollow(followMap);
				
				mav.addObject("isFollowing", followMap.get("result"));
				mav.setViewName("post/storyDetail");
				
				
				System.out.println("next : "+ paramMap.get("next"));
				System.out.println("prev : "+paramMap.get("prev"));
			}
		}
		return mav;
	}
	
	

	@RequestMapping("/story/delete")
	public String deleteStory(@RequestParam("story_num") int story_num, 
			HttpServletRequest request, Model model, RedirectAttributes rttr) {
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		
		if(loginUser== null) {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요.");
			return "redirect:/login/form";
		} else {
			String userid = (String) loginUser.get("USERID");
			
			ss.deleteStory(story_num);
			
			rttr.addFlashAttribute("message", "스토리를 삭제했어요!");
		    return "redirect:/post?userid="+userid;
		}
	}
	
	
	
	
	
	
	
	
}