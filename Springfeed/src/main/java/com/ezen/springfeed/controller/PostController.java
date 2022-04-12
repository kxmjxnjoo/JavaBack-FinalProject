package com.ezen.springfeed.controller;

import com.ezen.springfeed.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.springfeed.service.PostService;
import com.ezen.springfeed.dto.MemberDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class PostController {
	
	@Autowired
	PostService ps;

	@RequestMapping("/")
	public String main(HttpServletRequest request, Model model) {
		// Get if user is logged in
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			return "redirect:/login/form";
		}

		// Get userid
		String userid = (String) ((HashMap<String, Object>)session.getAttribute("loginUser")).get("USERID");
		System.out.println(userid);

		// Create paramMap
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("ref_cursor", null);

		// Get Posts
		ps.getPostsByUserid(paramMap);
		ArrayList<HashMap<String, Object>> postList =
				(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		session.setAttribute("postList", postList);

		// Get follower
		//ps.getFollowers(paramMap);
		//ArrayList<HashMap<String, Object>> followerList =
		//		(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");

		// Get Story
		//ps.getDisplayStories(paramMap);
		//ArrayList<HashMap<String, Object>> storyList =
		//		(ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");

		return "main";
	}

	@RequestMapping("/post")
	public String viewPost() {
		// Determine whether userid or postnum is provided

		return "post/postDetail";
	}

	@RequestMapping("/post/add/form")
	public String addPostForm(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null) {
			return "redirect:/login/form";
		}

		return "post/addPost";
	}

	@RequestMapping("/post/add")
	public String addPost() {
		return "redirect:/";
	}

	@RequestMapping("/post/edit/form")
	public String editPostForm(HttpServletRequest request, @RequestParam("num") int postNum) {
		// Get whether this post belongs to loginUser
		HttpSession session = request.getSession();
		Object member = session.getAttribute("loginUser");
		if(member == null) {
			return "redirect:/login/form";
		}
		//String userid = (MemberVO)member.getUserid();

		return "post/addPost";
	}

	@RequestMapping("/post/edit")
	public String editPost() {
		return "";
	}

	@RequestMapping("/post/delete")
	public String deletePost() {
		return "";
	}

	@RequestMapping("/select")
	public String selectPostStory() {
		return "";
	}

	@RequestMapping("/reply/add")
	public String addReply() {
		return "";
	}
	
	@RequestMapping("/reply/delete")
	public String deleteReply() {
		return "";
	}
	
	@RequestMapping("/bookmark/add")
	public String addBookmark() {
		return "";
	}

	@RequestMapping("/bookmark/delete")
	public String deleteBookmark() {
		return "";
	}

}