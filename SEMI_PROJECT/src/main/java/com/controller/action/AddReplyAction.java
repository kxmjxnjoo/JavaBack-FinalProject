package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;
import com.dto.MemberDto;
import com.dto.ReplyDto;

public class AddReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int post_num =  Integer.parseInt(request.getParameter("post_num"));
		
		String url = "spring.do?command=postDetail&post_num=" + post_num;
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else {
			ReplyDto rdto = new ReplyDto();
			String userid = mdto.getUserid();
			rdto.setUserid(userid);
			rdto.setContent(request.getParameter("reply_content"));
			rdto.setPost_num(post_num);

			System.out.println(rdto.getUserid());
			System.out.println(rdto.getContent());
			
			PostDao pdao = PostDao.getInstance();
			pdao.insertReply(rdto, post_num);
		}
		response.sendRedirect(url);
	}
}
