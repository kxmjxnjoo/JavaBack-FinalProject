package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.FaqDao;
import com.dto.FaqDto;

public class EditFaqFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/faq/editFaq.jsp";
		HttpSession session = request.getSession();
				
		// Check if user is logged in
		if(session.getAttribute("adminLogin") == null) {
			url = "/admin/adminLogin.jsp";
		} else {
			// Get faq num
			int num = Integer.parseInt(request.getParameter("num"));
			FaqDto faq = FaqDao.getInstance().getFaqDetail(num);
			request.setAttribute("faq", faq);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
