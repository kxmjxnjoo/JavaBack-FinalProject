package com.ezen.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.dao.FollowDao;
import com.ezen.dto.MemberDto;

public class UnfollowAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "spring.do?command=main";
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
		} else {
			// Get follower, following
			String follower = ((MemberDto)session.getAttribute("loginUser")).getUserid();
			String following = request.getParameter("userid");
			
			int result = FollowDao.getInstance().deleteFollow(follower, following);
			
			if(result == 1) {
				// SUCESS
				request.setAttribute("message", following + "님을 언팔로우 했어요");
			} else {
				// FAIL
				request.setAttribute("message", following + "님을 언팔로우 하는데 실패했어요. 다시 시도해 주세요.");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);		
	}
}
