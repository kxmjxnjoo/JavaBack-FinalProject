package com.ezen.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.dao.NotificationViewDao;
import com.ezen.dao.PostDao;
import com.ezen.dao.ReplyDao;
import com.ezen.dto.MemberDto;
import com.ezen.dto.ReplyDto;

public class AddReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "spring.do?command=main";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
		} else {
			// Get param
			int postNum = Integer.parseInt(request.getParameter("postnum"));
			String content = request.getParameter("content");
			String userid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
			
			// Add reply
			ReplyDto rdto = new ReplyDto();
			rdto.setContent(content);
			rdto.setPostNum(postNum);
			rdto.setUserid(userid);
			int result = ReplyDao.getInstance().addReply(rdto);
			
			// Get result
			if(result == 1) {
				request.setAttribute("message", "댓글을 추가했어요");
				String postUserid = PostDao.getInstance().getPost(postNum).getUserid();
				NotificationViewDao.getInstance().addNotification(userid, postUserid, 3, 1);
			} else {
				request.setAttribute("message", "댓글을 추가하지 못 했어요. 다시 시도해 주세요");
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}