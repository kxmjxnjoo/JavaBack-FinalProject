package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.action.Action;

public class AdminAction implements Action {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/adminLogin.jsp";
		request.getRequestDispatcher(url).forward(request, response);
	}
}
