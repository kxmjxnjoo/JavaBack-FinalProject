package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditProfileFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "member/editProfile.jsp";
		// Check if member is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
