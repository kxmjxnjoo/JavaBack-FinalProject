package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;
import com.dto.MemberDto;

public class CheckReplyLikeAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int reply_num = Integer.parseInt(request.getParameter("reply_num"));
		int post_num = Integer.parseInt(request.getParameter("post_num"));
		String url = "spring.do?command=postDetail&post_num=" + post_num;
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else {
			String userid = mdto.getUserid();
			PostDao pdao = PostDao.getInstance();
			int result = pdao.replyLikeCheck(reply_num, userid);
			String fileName = "";
			if(result == 0) {
				pdao.addReplyLike(reply_num, userid);
				fileName = "../images/Like.png";
				result = 1;
			} else {
				pdao.deleteReplyLike(reply_num, userid);
				fileName = "../images/beforeLike.png";
				result = 0;
			}
			request.setAttribute("likeResult", result);
		}
		response.sendRedirect(url);
	}
}
