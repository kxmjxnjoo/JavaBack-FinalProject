package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.QnaDao;
import com.dto.MemberDto;
import com.dto.QnaDto;

public class QnaWriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "spring.do?command=qnaWriteForm";
		
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");    
	    
	    if (mdto == null) {
	    	url = "spring.do?command=loginForm";
	    }else{
	    	QnaDto qdto = new QnaDto();
	    	qdto.setQna_subject(request.getParameter("subject"));
	    	qdto.setQna_content(request.getParameter("content"));
	    	qdto.setQna_id( mdto.getUserid() );
	    	
	    	QnaDao qdao = QnaDao.getInstance();
	    	qdao.uploadQna(qdto);
	    }
	    response.sendRedirect(url);
	}
}