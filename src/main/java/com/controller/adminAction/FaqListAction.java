package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dto.MemberDto;

public class FaqListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/faq/faqList.jsp";
		
		HttpSession session = request.getSession();
	    MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
	    if (mdto == null) {
	    	url = "spring.do?command=main";
	    } else {
	    	url = "spring.do?command='faqList";
	    }
	    request.getRequestDispatcher(url).forward(request, response);
	 }
}