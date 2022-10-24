package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;
import com.dto.MemberDto;
import com.dto.PostDto;

public class ExploreAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/post/explore.jsp";
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else {
			ArrayList<PostDto> postList = PostDao.getInstance().getBestPost();
			
			for(int i=0; i<postList.size(); i++) {
				int post_num = postList.get(i).getPostNum();
				PostDao pdao = PostDao.getInstance();
				postList.get(i).setLikeCount(pdao.likeCount(post_num));
				postList.get(i).setReplyCount(pdao.replyCount(post_num));
			}
			
			request.setAttribute("posts", postList);
		}
		request.getRequestDispatcher(url).forward(request, response);	
	}
}
