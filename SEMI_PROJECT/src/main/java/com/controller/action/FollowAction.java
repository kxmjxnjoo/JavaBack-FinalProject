package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.FollowDao;
import com.dao.NotificationViewDao;
import com.dto.MemberDto;

public class FollowAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "spring.do?command=main";
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
		} else {
			// Get follower(loginUser), following(param)
				String follower = ((MemberDto) session.getAttribute("loginUser")).getUserid();
				String following = request.getParameter("userid");
				
			// Insert follow into db
				int result = FollowDao.getInstance().insertFollow(follower, following);
			
			// Get result
				if(result == 1) {
					// SUCESS
					request.setAttribute("message", following + "님을 팔로우 했어요");
					NotificationViewDao.getInstance().addNotification(following, follower, 1, 0);
				} else {
					// FAIL
					request.setAttribute("message", following = "님을 팔로우 하지 못 했어요. 다시 시도해 주세요");
				}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
