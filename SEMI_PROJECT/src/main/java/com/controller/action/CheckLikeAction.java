package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;
import com.dto.MemberDto;

public class CheckLikeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int post_num = Integer.parseInt(request.getParameter("post_num"));
		String url = "spring.do?command=postDetail&post_num=" + post_num;
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else {
			String userid = mdto.getUserid();
			PostDao pdao = PostDao.getInstance();
			int result = pdao.postLikeCheck(post_num, userid);

			System.out.println("likeResult : " + result);
			if(result == 0) {
				pdao.addPostLike(post_num, userid);
				result = 1;
			} else {
				pdao.deleteLike(post_num, userid);
				result = 0;
			}
			request.setAttribute("likeResult", result);
			System.out.println("likeResult : " + result);
		}
			
		response.sendRedirect(url);
	}
}
