package com.ezen.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ezen.dao.NotificationViewDao;
import com.ezen.dao.PostDao;
import com.ezen.dao.PostLikeDao;
import com.ezen.dto.MemberDto;

public class LikePostAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "spring.do?command=main";
		HttpSession session = request.getSession();
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
		} else {
			// Get postNum, userid
			String userid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
			int postNum = Integer.parseInt(request.getParameter("postnum"));
			
			// Insert post_like into db
			int result = PostLikeDao.getInstance().insertPostLike(postNum, userid);
			
			// get result
			if(result == 1) {
				request.setAttribute("message", "좋아요!했어요");
				String postUserid = PostDao.getInstance().getPost(postNum).getUserid();
				NotificationViewDao.getInstance().addNotification(postUserid, userid, 2, postNum);
			} else {
				request.setAttribute("message", "좋아요!하지 못 했어요. 다시 시도해 주세요");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
