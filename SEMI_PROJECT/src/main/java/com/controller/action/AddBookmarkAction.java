package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BookmarkDao;
import com.dto.BookmarkDto;
import com.dto.MemberDto;

public class AddBookmarkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "main.jsp";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			request.setAttribute("message", "포스트를 저장하기 위해서는 로그인 해 주세요");
			url = "member/loginForm.jsp";
		} else {
			// Get parameter
			int postNum = Integer.parseInt(request.getParameter("postnum"));
			String userid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
			
			// Create dbto
			BookmarkDto bdto = new BookmarkDto();
			bdto.setPostNum(postNum);
			bdto.setUserid(userid);
			
			// Insert into db
			int result = BookmarkDao.getInstance().insertBookmark(bdto);
			
			// Get result
			if(result == 1) {
				request.setAttribute("message", "포스트를 추가했어요");
			} else {
				request.setAttribute("message", "포스트 추가에 실패했어요. 다시 시도해 주세요");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
