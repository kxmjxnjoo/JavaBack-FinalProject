package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BookmarkDao;
import com.dao.FollowDao;
import com.dao.MemberViewDao;
import com.dao.PostDao;
import com.dao.PostViewDao;
import com.dto.BookmarkDto;
import com.dto.MemberDto;
import com.dto.MemberViewDto;
import com.dto.PostViewDto;

public class UserPageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "post/userPage.jsp";
		
		// Get parameter
		String userid = request.getParameter("userid");
		String isBookmark = request.getParameter("bookmark");
		
		// Get user's info
		MemberViewDto user = MemberViewDao.getInstance().getMember(userid);
		request.setAttribute("user", user);

		
		// Determine if user is entering bookmark
		if(isBookmark != null) {
			// Get user's bookmark
			ArrayList<BookmarkDto> bookmarkList = BookmarkDao.getInstance().getBookmark(userid);
			
			request.setAttribute("bookmark", "y");
			request.setAttribute("bookmarkList", bookmarkList);
			
		} else {
			
			// Get user's post
			ArrayList<PostViewDto> postList = PostViewDao.getInstance().getAllPostById(userid);
			
			// Get count reply & likes
			if(postList != null) {
				for(int i=0; i<postList.size(); i++) {
					int post_num = postList.get(i).getPostNum();
					PostDao pdao = PostDao.getInstance();
					postList.get(i).setLikeCount(pdao.likeCount(post_num));
					postList.get(i).setReplyCount(pdao.replyCount(post_num));
				}
				request.setAttribute("posts", postList);
			}
			
		}
		
		// Get whether loginUser is following user
		HttpSession session = request.getSession();
		int isFollowing = 0;
		if(session.getAttribute("loginUser") != null) {
			String loginUserid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
			isFollowing = FollowDao.getInstance().isFollowing(loginUserid, userid);
		} 
		request.setAttribute("isFollowing", isFollowing);
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
