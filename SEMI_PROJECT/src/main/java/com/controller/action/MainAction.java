package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BookmarkDao;
import com.dao.FollowViewDao;
import com.dao.PostViewDao;
import com.dao.StoryDao;
import com.dto.FollowViewDto;
import com.dto.MemberDto;
import com.dto.PostViewDto;
import com.dto.StoryDto;

public class MainAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "main.jsp";
		HttpSession session = request.getSession();
		
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
		} else {
			
			// Get my profile
			MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
			String userid = mdto.getUserid();
			
			// Get following list
			ArrayList<FollowViewDto> followingList = FollowViewDao.getInstance().getFollowing(userid);
			
			if(followingList == null) {
				url = "info/noFollower.jsp";
			} else if(followingList.size() == 1) {
				url = "info/noFollower.jsp";
			} else {
							
				// Get follower's story
				List<List<StoryDto>> memberStoryList = new ArrayList<List<StoryDto>>();
				if(followingList != null) {
					for(FollowViewDto fdto : followingList) {
						if(fdto.getFollowing() != null) {
							String followingId = fdto.getFollowing();
							ArrayList<StoryDto> storyList = StoryDao.getInstance().getAllStory(followingId);
							// Get if following has any story
							if(storyList != null) {
								fdto.setIsStoryPresent(1);
							} else {
								fdto.setIsStoryPresent(0);
							}
							
							memberStoryList.add(storyList);
						}
					}
				}
				request.setAttribute("followingList", followingList);
				request.setAttribute("memberStoryList", memberStoryList);
				
				// Get follower's post
				ArrayList<PostViewDto> postList = new ArrayList<PostViewDto>();
				if(followingList != null) {
					ArrayList<PostViewDto> tmpPostList = PostViewDao.getInstance().getAllPost(userid);
					if(tmpPostList != null) {
						for(PostViewDto pdto : tmpPostList) {
							for(FollowViewDto fdto : followingList) {
								if(fdto.getFollowing().equals(pdto.getUserid()) || fdto.getFollowing().equals(userid)) {
									postList.add(pdto);
									break;
								}
							}
						}
					}
					
					// Get if loginUser saved the post
					for(PostViewDto pdto : tmpPostList) {
						pdto.setIsSaved(BookmarkDao.getInstance().getBookmark(userid, pdto.getPostNum()));
					}
				}
				request.setAttribute("postList", postList);
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
