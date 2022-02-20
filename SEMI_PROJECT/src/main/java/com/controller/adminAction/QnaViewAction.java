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

public class QnaViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "qnaView.jsp";
		
		// QnaDao 에 추가될 메서드 이름 getQna
		HttpSession session = request.getSession();
	    MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
	    if (mdto == null) {
	        url = "spring.do?command=login";
	    } else {	
	    	QnaDao qdao = QnaDao.getInstance();
	    	
	    	int qna_num = Integer.parseInt( request.getParameter("qna_num"));
			QnaDto qdto = qdao.getQna( qna_num );
	    	request.setAttribute("qnaDto", qdto);
	    }
		request.getRequestDispatcher(url).forward(request, response);
	}
}