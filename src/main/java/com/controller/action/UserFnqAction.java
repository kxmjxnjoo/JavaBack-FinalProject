package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FaqDao;
import com.dto.FaqDto;

public class UserFnqAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get fnq list
		ArrayList<FaqDto> list = FaqDao.getInstance().listFaq();
		request.setAttribute("faqList", list);
		
		request.getRequestDispatcher("/useradmin/faq.jsp").forward(request, response);
	}

}
