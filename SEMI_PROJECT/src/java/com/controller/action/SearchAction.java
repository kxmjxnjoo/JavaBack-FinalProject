package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.FollowDao;
import com.dao.MemberDao;
import com.dto.MemberDto;

public class SearchAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/search/search.jsp";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
		} else {
			String userid = ((MemberDto)session.getAttribute("loginUser")).getUserid();
					
			ArrayList<MemberDto> results;
			
			if(request.getParameter("searchfor") == null) {
				results = MemberDao.getInstance().getAllMembers();
				if(results != null) {
					for(MemberDto mdto : results) {
						int isFollowing = FollowDao.getInstance().isFollowing(userid, mdto.getUserid());
						mdto.setIsFollowing(isFollowing);
					}
				}
			} else if(request.getParameter("searchfor").equals("")) {
				results = MemberDao.getInstance().getAllMembers();
				if(results != null) {
					for(MemberDto mdto : results) {
						int isFollowing = FollowDao.getInstance().isFollowing(userid, mdto.getUserid());
						mdto.setIsFollowing(isFollowing);
					}
				}
			} else {
				// Get Param
				String searchWord = request.getParameter("searchfor");
				
				// Get result
				results = MemberDao.getInstance().getMembers(searchWord);
				if(results != null) {
					for(MemberDto mdto : results) {
						int isFollowing = FollowDao.getInstance().isFollowing(userid, mdto.getUserid());
						mdto.setIsFollowing(isFollowing);
					}
				}
			} 
			
			request.setAttribute("results", results);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}
}
