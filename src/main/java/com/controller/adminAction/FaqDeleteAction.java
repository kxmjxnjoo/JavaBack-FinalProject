package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.FaqDao;
import com.dto.AdminDto;

public class FaqDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/faq/adminFaqList.jsp";
		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		if(adto == null) url = "spring.do?command=admin";
		else {
		
		String[] fseqArr = request.getParameterValues("fseq");
			
		FaqDao fdao = FaqDao.getInstance();
		
		for(String fseq : fseqArr)
			fdao.deleteFaq(Integer.parseInt(fseq));

		}
		response.sendRedirect("spring.do?command=adminFaqList");
	}
}