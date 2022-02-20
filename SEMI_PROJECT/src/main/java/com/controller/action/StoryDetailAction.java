package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StoryDao;
import com.dto.MemberDto;
import com.dto.ReplyDto;
import com.dto.StoryDto;

public class StoryDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/post/storyDetail.jsp";
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=loginform";
		else {
				StoryDao sdao = StoryDao.getInstance();
				String userid = request.getParameter("userid");
				int story_num = 0;
				StoryDto sdto = null;
				if(userid == null) {
					story_num = Integer.parseInt(request.getParameter("story_num"));
					sdto = sdao.getStory(story_num);
					userid = sdto.getUserid();

				} else {
					story_num = sdao.get_story_num(userid);
					sdto = sdao.getStory(story_num);
				}
				
				
				String loginUser = mdto.getUserid();
				int result = sdao.storyLikeCheck(story_num, loginUser);
				
				
				int prev = sdao.searchPrevStory(story_num, userid);
				int next = sdao.searchNextStory(story_num, userid);
				
				////////////테스트용 코드 
//				System.out.println("///");
//				loginUser = ((MemberDto) session.getAttribute("loginUser")).getUserid();
//				System.out.println("loginUser : " + loginUser);
//				System.out.println("스토리 좋아요? : " + result);
//				System.out.println("prev : " +prev + ", next : " + next);
//				System.out.println("스토리작성자 : " + sdto.getUserid());
//				System.out.println("스토리이미지 : " + sdto.getStory_img());
//				System.out.println("fontColor:" + sdto.getFontColor());
//				System.out.println("likeResult : " + result);
				////////////테스트용 코드 여기까지 /////////////////////////
				
				request.setAttribute("likeResult", result);
				request.setAttribute("likeResult", result);
				request.setAttribute("story_num", story_num);
				request.setAttribute("StoryDto", sdto);
				request.setAttribute("prev", prev);
				request.setAttribute("next", next);
				
			
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
