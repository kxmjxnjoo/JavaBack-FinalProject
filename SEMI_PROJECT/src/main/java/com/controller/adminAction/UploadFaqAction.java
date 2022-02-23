package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.FaqDao;
import com.dto.AdminDto;
import com.dto.FaqDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class UploadFaqAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "spring.do?command=faqWriteForm";
		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		if(adto==null) url = "spring.do?command=admin";
		else {
			
			FaqDto fdto = new FaqDto();
			fdto.setFaq_subject(request.getParameter("faq_subject"));
			fdto.setFaq_content(request.getParameter("faq_content"));
			fdto.setFaq_num(Integer.parseInt(request.getParameter("faq_num")));
			
			FaqDao  fdao = FaqDao.getInstance();
			fdao.updateFaq(fdto);
		}
		response.sendRedirect(url);//.forward(request, response);
	}
}