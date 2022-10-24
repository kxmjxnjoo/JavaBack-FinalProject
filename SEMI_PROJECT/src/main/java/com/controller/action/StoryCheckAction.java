package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StoryDao;
import com.dto.MemberDto;

public class StoryCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		int story_num = 0;
		String url = ""; 

		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=loginform";
		else {
			StoryDao sdao = StoryDao.getInstance();
			int result = sdao.checkStory(userid);
			
			if(result==1) {
				story_num = sdao.get_story_num(mdto.getUserid());
				url = "spring.do?command=storyDetail&story_num="+story_num;
				request.setAttribute("story_num", story_num);
			} else {
				url = "spring.do?command=storyUploadForm";
			}
		}	
		request.getRequestDispatcher(url).forward(request, response);
	}
}
