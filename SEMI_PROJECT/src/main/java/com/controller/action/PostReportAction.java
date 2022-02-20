package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;
import com.dao.StoryDao;
import com.dto.MemberDto;
import com.dto.PostDto;
import com.dto.StoryDto;

public class PostReportAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reported_post = 0;
		String type = null;
		String reported = null;
		String url = "/post/postReportDone.jsp";
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else {
			String loginUser = mdto.getUserid();
			String reason = request.getParameter("reportReson");
			int result = 0;
			if(reason.equals("1")) reason = "스팸";
			else if(reason.equals("2")) reason = "민감한 콘텐츠";
			else if(reason.equals("3")) reason = "폭력적인 콘텐츠";
			else if(reason.equals("4")) reason = "허위 사실 유포";
			else if(reason.equals("5")) reason = "자살 또는 자해 요소";
			else if(reason.equals("6")) reason = "불법 또는 규제 상품 판매";
			else if(reason.equals("7")) reason = "섭식 장애";
			else if(reason.equals("8")) reason = "혐오 발언 또는 상징";
			else if(reason.equals("9")) reason = "지식재산권 침해";
			
			if(request.getParameter("story_num") == null) {
				reported_post =Integer.parseInt(request.getParameter("post_num"));
				PostDto pdto = PostDao.getInstance().getPost(reported_post);
				reported = pdto.getUserid();
				type = "post";

				result = PostDao.getInstance().insertReport(loginUser, reported, reported_post, reason, type);
				request.setAttribute("post_num", reported_post);
			} else {
				reported_post = Integer.parseInt(request.getParameter("story_num"));
				StoryDto sdto = StoryDao.getInstance().getStory(reported_post);
				reported = sdto.getUserid();
				type = "story";
				
				result = PostDao.getInstance().insertStoryReport(loginUser, reported, reported_post, reason, type);
				request.setAttribute("story_num", reported_post);
			} 
			

			
			String message = "";
			if (result==1) message = "포스트를 신고했어요";
			else message = "포스트를 신고하지 못했어요. 다시 시도해주세요.";
			
			
			request.setAttribute("reason", reason);
			request.setAttribute("message", message);
			
		}
		
			response.sendRedirect(url);
	}
}
