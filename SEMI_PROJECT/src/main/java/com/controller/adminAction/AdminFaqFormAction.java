package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.FaqDao;
import com.dto.AdminDto;
import com.dto.FaqDto;

public class AdminFaqFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/FaqList.jsp";
		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		
		if(adto==null) {
			url = "spring.do?command=admin";
		}else {
			FaqDto fdto = new FaqDto();
			
			int faq_num = Integer.parseInt( request.getParameter("faq_num") );
	    	fdto.setFaq_subject(request.getParameter("faq_subject"));
	    	fdto.setFaq_content(request.getParameter("faq_content"));
	    	
	    	FaqDao fdao = FaqDao.getInstance();
	    	fdao.uploadFaq(fdto);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}