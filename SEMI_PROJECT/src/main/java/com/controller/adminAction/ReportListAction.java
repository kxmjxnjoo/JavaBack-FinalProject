package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dto.AdminDto;

public class ReportListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "adminReportList.jsp";
		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		int page = 1;
		if(adto == null) url = "spring.do?command=admin";
		else { 
			session = request.getSession();
			session.setAttribute("adminLogin", adto);
			url = "spring.do?command=adminReportList";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}