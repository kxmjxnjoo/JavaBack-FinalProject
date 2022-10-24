package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.action.Action;
import com.dao.StoryDao;
import com.dto.StoryDto;

public class StoryReportCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/report/storyReportCheck.jsp";
		int story_num = Integer.parseInt(request.getParameter("story_num"));
		int report_num  = Integer.parseInt(request.getParameter("report_num"));
		StoryDao sdao = StoryDao.getInstance();
		StoryDto sdto = sdao.getStory(story_num);
		
		request.setAttribute("StoryDto", sdto);
		request.setAttribute("story_num", story_num);
		request.setAttribute("report_num", report_num);
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
