package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.QnaDao;
import com.dto.QnaDto;

public class QnaViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/admin/qna/qnaView.jsp";
		
		HttpSession session = request.getSession();

		if(session.getAttribute("adminLogin") == null) {
			request.setAttribute("message", "QNA를 수정하기 위해 관리자로 로그인 해 주세요");
			url = "/admin/adminLogin.jsp";
		} else {
	    	QnaDao qdao = QnaDao.getInstance();
	    	int qna_num = Integer.parseInt( request.getParameter("num"));
	    	
			QnaDto qdto = qdao.getQna(qna_num);
			
	    	request.setAttribute("qna", qdto);
		}
		
	    	
	    request.getRequestDispatcher(url).forward(request, response);
	}
}