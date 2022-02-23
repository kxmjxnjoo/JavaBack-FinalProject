package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BookmarkDao;
import com.dto.BookmarkDto;
import com.dto.MemberDto;

public class DeleteBookmarkAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "main.jsp";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			request.setAttribute("message", "포스트 저장을 삭제하실려면 먼저 로그인해 주세요");
			url = "member/loginForm.jsp";
		} else {
			// Get parameter
			int num = Integer.parseInt(request.getParameter("num"));
			String userid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
			
			// Check if this bookmark belongs to loginUser
			BookmarkDto bdto = BookmarkDao.getInstance().getBookmarkByNum(num);
			
			if(!bdto.getUserid().equals(userid)) {
				request.setAttribute("message", userid + "님이 저장하신 포스트가 아니에요");
			} else {
				// Delete from db 
				int result = BookmarkDao.getInstance().deleteBookmark(num);
				
				// Get result
				if(result == 1) {
					request.setAttribute("message", "포스트 저장을 삭제했어요");
				} else {
					request.setAttribute("message", "포스트 저장을 삭제하지 못 했어요. 다시 시도해 주세요");
				}
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
