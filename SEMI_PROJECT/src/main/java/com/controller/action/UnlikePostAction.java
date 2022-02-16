package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostLikeDao;
import com.dto.MemberDto;

public class UnlikePostAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "spring.do?command=main";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm";
		} else {
			// Get user(session), postNum(param)
			String userid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
			int postNum = Integer.parseInt(request.getParameter("postnum"));
			
			// Delete like
			int result = PostLikeDao.getInstance().deleteLike(postNum, userid);
			
			// Get result
			if(result == 1) {
				// SUCCESS
				request.setAttribute("message", "좋아요!를 취소했어요");
			} else {
				request.setAttribute("message", "좋아요!를 취소하지 못 했어요. 다시 시도해 주세요");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
