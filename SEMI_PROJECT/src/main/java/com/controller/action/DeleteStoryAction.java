package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StoryDao;
import com.dto.MemberDto;

public class DeleteStoryAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "spring.do?command=main"; //유저 페이지로 이동
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else {
			int story_num = Integer.parseInt(request.getParameter("story_num"));
			StoryDao.getInstance().deleteStory(story_num);
			
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
