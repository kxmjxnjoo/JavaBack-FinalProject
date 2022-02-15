package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FollowArrowsAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int story_num = Integer.parseInt(request.getParameter("story_num"));
		String url = "spring.do?command=storyDetail&story_num=" + story_num;
		request.getRequestDispatcher(url).forward(request, response);
	}
}
