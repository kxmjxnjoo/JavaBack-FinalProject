package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.QnaDao;
import com.dto.AdminDto;

public class DeleteQnaAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/adminQnaList.jsp";
		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		if(adto == null) url = "spring.do?command=admin";
		else {
		
		String[] qseqArr = request.getParameterValues("qseq");
			
		QnaDao qdao = QnaDao.getInstance();
		
		for(String qseq : qseqArr)
			qdao.deleteQna(Integer.parseInt(qseq));

		}
		response.sendRedirect("spring.do?command=adminQaqList");
	}
}