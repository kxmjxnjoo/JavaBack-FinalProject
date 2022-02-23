package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.FollowDao;
import com.dao.PostDao;
import com.dto.MemberDto;
import com.dto.PostDto;
import com.dto.ReplyDto;

public class PostDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/post/postDetail.jsp";
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else {
				int post_num = Integer.parseInt(request.getParameter("post_num"));
				int isFollowing = 0;
				String likeColor = "";
				String message = request.getParameter("message");
				String loginUserid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
				PostDao pdao = PostDao.getInstance();
				PostDto pdto = pdao.getPost(post_num);
				ArrayList<ReplyDto> rdto = pdao.getReply(post_num);
				
				int result = pdao.postLikeCheck(post_num, loginUserid);
				System.out.println("before likeResult : " + result);
				if (result==1) likeColor = "red"; 
				
				for(int i=0; i<rdto.size(); i++) {
					int replyLikeResult = pdao.replyLikeCheck(rdto.get(i).getReply_num(), loginUserid);
					if(replyLikeResult==0) rdto.get(i).setReplyFileName("");
					else rdto.get(i).setReplyFileName("red");
				}
				
				isFollowing = FollowDao.getInstance().isFollowing(loginUserid, loginUserid);
				
				
				request.setAttribute("likeColor", likeColor);
				request.setAttribute("isFollowing", isFollowing);
				request.setAttribute("likeResult", result);
				request.setAttribute("post_num", post_num);
				request.setAttribute("PostDto", pdto);
				request.setAttribute("ReplyDto", rdto);
				request.setAttribute("message", message);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
