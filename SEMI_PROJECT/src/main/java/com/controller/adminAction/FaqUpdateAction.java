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

public class FaqUpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "spring.do?command=faqDetail";
		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		if(adto==null) url = "spring.do?command=admin";
		else {
			FaqDto fdto = new FaqDto();
			ServletContext context = session.getServletContext();
			String path = context.getRealPath("");
			
			MultipartRequest multi = new MultipartRequest(
					request, path, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy() );
			
			fdto.setFaq_num(Integer.parseInt(multi.getParameter("faq_num")));
			fdto.setFaq_subject(multi.getParameter("faq_subject"));
			fdto.setFaq_content(multi.getParameter("faq_content"));
			
			FaqDao  fdao = FaqDao.getInstance();
			fdao.updateFaq(fdto);
			url = url + "&fdto=" + fdto.getFaq_num();
		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}
}
