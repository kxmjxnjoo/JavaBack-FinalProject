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
	
	@RequestMapping("/story/add") 
	public String addStory(@ModelAttribute("dto") StoryDto storydto,
			HttpServletRequest request, RedirectAttributes rttr) {
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null)  {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요.");
			return "redirect:/login/form";
		} else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			
			if(storydto.getFontColor() == null || ((String)storydto.getFontColor()).equals(""))
				paramMap.put("fontcolor", "white");
			else
				paramMap.put("fontcolor", storydto.getFontColor());
			
			paramMap.put("story_content", storydto.getStory_content());
			paramMap.put("userid", loginUser.get("USERID"));
			paramMap.put("story_num", 0);
			paramMap.put("story_img", storydto.getStory_img());
			
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
		
		if (loginUser == null)  {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요!");
			mav.setViewName("redirect:/login/form");
		} else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("story_num", story_num);
			paramMap.put("prev", 0);
			paramMap.put("next", 0);
			paramMap.put("fontcolor", null);
			paramMap.put("useyn", null);
			
			ss.getStory(paramMap);
			
			ArrayList<HashMap<String, Object>> list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			if(list.size() == 0) {
				rttr.addFlashAttribute("message", "존재하지 않는 스토리 입니다.");
				mav.setViewName("redirect:/");
			} else {
				
				HashMap<String, Object> resultMap = list.get(0);
				
				if(((String)paramMap.get("useyn")).equals("y")) {
					
					mav.addObject("StoryDto", resultMap);
					mav.addObject("fontcolor", (String)paramMap.get("fontcolor"));
					
					//이전글, 다음글 검색
					ss.getStoryPrevNext(paramMap);
	
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
					System.out.println("prev : "+ paramMap.get("prev"));
				} else {
					rttr.addFlashAttribute("message", "삭제되었거나 비활성화된 계정의 스토리 입니다.");
					mav.setViewName("redirect:/");
				}
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
	
	
	@RequestMapping("/story/edit/form")
	public String editStory(@RequestParam("story_num") int story_num, 
			HttpServletRequest request, Model model, RedirectAttributes rttr) {

		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(loginUser== null) {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요.");
			return "redirect:/login/form";
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
		
	@RequestMapping("/story/edit") 
	public String editStory(@ModelAttribute("StoryDto") @Valid StoryDto storydto,
			HttpServletRequest request, RedirectAttributes rttr,
			@RequestParam("story_num") int story_num,
			@RequestParam(value="oldFontColor", required=false) String oldFontcolor,
			@RequestParam(value="oldPicture", required=false) String oldPicture) {
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null)  {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요.");
			return "redirect:/login/form";
		} else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			
			if(storydto.getFontColor() == null || ((String)storydto.getFontColor()).equals(""))
				if(oldFontcolor == null || ((String)oldFontcolor).equals(""))
					paramMap.put("fontcolor", "white");
				else 
					paramMap.put("fontcolor", oldFontcolor);
			else
				paramMap.put("fontcolor", storydto.getFontColor());
			
			if(storydto.getStory_img() == null || ((String)storydto.getStory_img()).equals(""))
				paramMap.put("story_img", oldPicture);
			else 
				paramMap.put("story_img", storydto.getStory_img());
			
			paramMap.put("story_content", storydto.getStory_content());
			paramMap.put("story_num", story_num);
			
			ss.editStory(paramMap);
			
			rttr.addFlashAttribute("message", "스토리를 수정했어요!");
			return "redirect:/story?story_num="+story_num;
			
		}
	}
	
	
}