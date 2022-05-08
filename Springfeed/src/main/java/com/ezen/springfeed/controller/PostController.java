package com.ezen.springfeed.controller;

import com.ezen.springfeed.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.springfeed.service.PostService;
import com.ezen.springfeed.dto.MemberDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value="/post/detail/reply/{num}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public ArrayList<HashMap<String, Object>> getPostReply(@PathVariable(value="num") int postNum) {
		// paramMap
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("num", postNum);
		paramMap.put("ref_cursor", null);

		ps.getReply(paramMap);

		ArrayList<HashMap<String, Object>> result = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");

		if(result == null || result.size() == 0) {
			return new ArrayList<>();
		}

		ArrayList<HashMap<String, Object>> replies = new ArrayList<>();
		for(HashMap<String, Object> reply : result) {
			HashMap<String, Object> tmp = new HashMap<>();
			tmp.put("userid", reply.get("USERID"));
			tmp.put("userImg", reply.get("USER_IMG"));
			tmp.put("content", reply.get("CONTENT"));
			tmp.put("createdDate", reply.get("REPLY_DATE"));
			tmp.put("like", reply.get("REPLY_LIKE_COUNT"));

			replies.add(tmp);
		}

		return replies;
	}

	@RequestMapping(value="/post/detail/{num}", produces="application/json;charset=UTF-8")
	@ResponseBody
	public HashMap<String, Object> viewPost(@PathVariable(value="num") int postNum) {
		// paramMap
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("num", postNum);
		paramMap.put("ref_cursor", null);

		ps.getPostDetail(paramMap);
		ArrayList<HashMap<String, Object>> result = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");

		if(result == null || result.size() == 0) {
			return new HashMap<>();
		}

		HashMap<String, Object> returnMap = new HashMap<>();
		returnMap.put("userProfile", result.get(0).get("USER_IMG"));
		returnMap.put("userid", result.get(0).get("USERID"));
		returnMap.put("address", result.get(0).get("ADDRESS"));
		returnMap.put("post_img", result.get(0).get("POST_IMG"));
		returnMap.put("likeCount", 0);
		returnMap.put("isLiked", 0);
		returnMap.put("isSaved", 0);
		returnMap.put("content", result.get(0).get("CONTENT"));
		returnMap.put("post_num", result.get(0).get("POST_NUM"));

		return returnMap;
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