package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.FaqDao;

public class DeleteFaqAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "spring.do?command=adminFaqList";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		if(session.getAttribute("adminLogin") == null) {
			url = "/admin/adminLogin.jsp";
			request.setAttribute("message", "FAQ를 삭제하기 위해서는 먼저 로그인 해 주세요");
		} else {
			// Get faq num
			int num = Integer.parseInt(request.getParameter("num"));
			
			// Delete faq from DB
			int result = FaqDao.getInstance().deleteFaq(num);
			
			// Get result
			if(result == 1) {
				request.setAttribute("message", num + "번 FAQ를 삭제했어요!");
			} else {
				request.setAttribute("message", "FAQ를 삭제하지 못 했어요. 다시 시도해 주세요");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
