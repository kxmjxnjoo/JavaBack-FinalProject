package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.FaqDao;
import com.dto.FaqDto;

public class AddFaqAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/faq/adminFaqList.jsp";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		if(session.getAttribute("adminLogin") == null) {
			url = "/admin/adminLogin.jsp";
		} else {
			// Get parameter
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			// Create Fdto
			FaqDto fdto = new FaqDto();
			fdto.setFaq_content(content);
			fdto.setFaq_subject(subject);
			
			// Insert into db
			int result = FaqDao.getInstance().uploadFaq(fdto);
			
			if(result == 0) {
				request.setAttribute("message", "FAQ를 추기했어요");
			} else {
				request.setAttribute("message", "FAQ를 추가하지 못 했어요. 다시 시도해 주세요");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
