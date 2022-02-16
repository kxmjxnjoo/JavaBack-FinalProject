package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StoryDao;

public class CheckStoryLikeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int story_num = Integer.parseInt(request.getParameter("story_num"));
		String url = "spring.do?command=storyDetail&story_num=" + story_num;
		HttpSession session = request.getSession();
		//MeberDto mdto = (MemberDto) sessio.getAttribute("loginAdmin");
		//if(mdto==null) url = "spring.do?command=login";
		//else {
			String userid = "jojo";//((MemberDto) session.getAttribute("loginUser")).getUserid();
			StoryDao sdao = StoryDao.getInstance();
			int result = sdao.storyLikeCheck(story_num, userid);
			String fileName = "";
			if(result == 0) {
				sdao.addStoryLike(story_num, userid);
				fileName = "../images/Like.png";
				result = 1;
			} else {
				sdao.deleteStoryLike(story_num, userid);
				fileName = "../images/beforeLike.png";
				result = 0;
			}
			request.setAttribute("likeResult", result);
		//}
			
		response.sendRedirect(url);
	}
}
