package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IdCheckFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameter
		String id = request.getParameter("id");
		request.setAttribute("checkid", id);
		String url = "member/idcheckForm.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);

	}

}
