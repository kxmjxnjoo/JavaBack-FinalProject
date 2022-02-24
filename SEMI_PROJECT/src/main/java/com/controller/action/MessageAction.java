package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.FollowViewDao;
import com.dao.MemberDao;
import com.dao.MessageDao;
import com.dto.FollowViewDto;
import com.dto.MemberDto;
import com.dto.MessageDto;

public class MessageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/message/message.jsp";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
			request.setAttribute("message", "메세지를 확인하기 위해서는 로그인 해 주세요");
		} else {
			// Get loginUser userid
			String userid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
			
			// Get user history
			//ArrayList<MessageDto> messageUserHistory = MessageDao.getInstance().getAllMessageMember(userid);
			//request.setAttribute("userList", messageUserHistory);
			ArrayList<FollowViewDto> userList = FollowViewDao.getInstance().getFollowing(userid);
			request.setAttribute("userList", userList);
			
			// Check if messagewith is null
			if(request.getParameter("messagewith") != null) {
				// Get parameter
				String messageWith = request.getParameter("messagewith");
				MemberDto mdto = MemberDao.getInstance().getMember(messageWith);
				request.setAttribute("messageWith", mdto);
				
				// Get message history
				ArrayList<MessageDto> messageHistory = MessageDao.getInstance().getAllMessage(messageWith, userid);
				request.setAttribute("messageHistory", messageHistory);
			}
	
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
