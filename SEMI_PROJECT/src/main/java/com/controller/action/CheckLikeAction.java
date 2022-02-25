package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.NotificationViewDao;
import com.dao.PostDao;
import com.dto.MemberDto;
import com.dto.PostDto;

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
			PostDto pdto = pdao.getPost(post_num);
			String postingUser = pdto.getUserid();
			
			System.out.println("pdto : " + postingUser);
			System.out.println("mdto : " + userid);
			if(result == 0) {
				pdao.addPostLike(post_num, userid);
				if(!postingUser.equals(userid)) {
					NotificationViewDao.getInstance().addNotification(pdto.getUserid(), userid, 2, post_num);
				}
				result = 1;
			} else {
				pdao.deleteLike(post_num, userid);
				result = 0;
			}
			request.setAttribute("likeResult", result);
			System.out.println("checklikeResult : " + result);
		}
			
		response.sendRedirect(url);
	}
}
