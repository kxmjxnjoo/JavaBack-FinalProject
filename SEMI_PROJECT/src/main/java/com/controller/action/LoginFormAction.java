package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "member/loginForm.jsp";
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") != null) {
			url = "main.jsp";
		}

		request.getRequestDispatcher(url).forward(request, response);
	}
}
