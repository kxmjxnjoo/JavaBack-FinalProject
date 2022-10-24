package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MessageDao;
import com.dto.MemberDto;
import com.dto.MessageDto;

public class AddMessageAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/loginForm";
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") == null) {
			request.setAttribute("message", "메세지를 보내기 위해 로그인해 주세요");
		} else {
			// Get parameter
			String messageWith = request.getParameter("messagewith");
			String content = request.getParameter("content");
			String userid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
			
			// Create messageDto
			MessageDto mdto = new MessageDto();
			mdto.setContent(content);
			mdto.setMessageFrom(userid);
			mdto.setMessageTo(messageWith);
			
			// Insert into db
			int result = MessageDao.getInstance().sendMessage(mdto);
			
			// Get Result
			if(result == 0) {
				request.setAttribute("message", "메세지를 보낼 수 없었어요! 다시 시도해 주세요");
			}
			
			// Set url
			url = "spring.do?command=message&messagewith=" + messageWith;
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
