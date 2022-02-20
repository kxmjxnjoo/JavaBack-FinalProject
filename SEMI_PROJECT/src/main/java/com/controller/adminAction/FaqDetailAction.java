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

public class FaqDetailAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/FaqDetail.jsp";
		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		if(adto==null) {
			url = "spring.do?command=admin";
		}else {
			int faq_num=Integer.parseInt(null);
			FaqDao fdao = FaqDao.getInstance();
			FaqDto fdto = fdao.getFaqDetail( faq_num );
	    	request.setAttribute("FaqDto", fdto);
	    }
		request.getRequestDispatcher(url).forward(request, response);
	}
}