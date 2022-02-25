package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;

public class AdminFaqFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/admin/faq/faqWriteForm.jsp";
		HttpSession session = request.getSession();
		
		if(session.getAttribute("adminLogin") == null) {
			url = "spring.do?command=admin";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}