package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.action.Action;

public class JoinFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String url = "join.jsp";
		    RequestDispatcher dispatcher=request.getRequestDispatcher(url);
		    dispatcher.forward(request, response);
		}
}
