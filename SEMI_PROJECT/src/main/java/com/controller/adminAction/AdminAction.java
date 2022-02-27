package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;

public class AdminAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "spring.do?command=memberList";
		HttpSession session = request.getSession();
		
		// Check if adminUser is logged in
		if(session.getAttribute("adminLogin") == null) {
			url = "/admin/adminLogin.jsp"; 
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}