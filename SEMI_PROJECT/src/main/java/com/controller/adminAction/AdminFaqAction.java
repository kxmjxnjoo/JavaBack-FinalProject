package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dto.AdminDto;

public class AdminFaqAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/admin/faq/faqList.jsp";
		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		if(adto==null) {
			url = "spring.do?command=admin";
		}else {
			url = "admin/FaqList.jsp";
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}