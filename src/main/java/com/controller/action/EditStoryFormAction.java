package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;
import com.dao.StoryDao;
import com.dto.MemberDto;
import com.dto.StoryDto;

public class EditStoryFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/post/editStory.jsp";
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else {
			int story_num = Integer.parseInt(request.getParameter("story_num"));
			request.setAttribute("story_num", story_num);
			StoryDto sdto = StoryDao.getInstance().getStory(story_num);
			request.setAttribute("StoryDto", sdto);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
