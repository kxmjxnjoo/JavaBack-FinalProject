package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.NotificationViewDao;
import com.dao.StoryDao;
import com.dto.MemberDto;
import com.dto.StoryDto;

public class CheckStoryLikeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int story_num = Integer.parseInt(request.getParameter("story_num"));
		String url = "spring.do?command=storyDetail&story_num=" + story_num;
		String likeColor = "";
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else {
			String userid = mdto.getUserid();
			StoryDao sdao = StoryDao.getInstance();
			int result = sdao.storyLikeCheck(story_num, userid);
			StoryDto sdto = sdao.getStory(story_num);
			
			if(result == 0) {
				sdao.addStoryLike(story_num, userid);
				sdao.getStory(story_num);
				if(!sdto.getUserid().equals(userid)) {
					NotificationViewDao.getInstance().addNotification(sdto.getUserid(), userid, 5, story_num);
				}
			} else if (result == 1 ) {
				sdao.deleteStoryLike(story_num, userid);
			}
		}
		response.sendRedirect(url);
	}
}
