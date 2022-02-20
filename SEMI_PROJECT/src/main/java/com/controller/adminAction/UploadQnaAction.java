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

public class UploadQnaAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "qnaView.jsp";
		int qseq = Integer.parseInt( request.getParameter("qseq") );
		
		// QnaDao 에 추가될 메서드 이름 getQna
		HttpSession session = request.getSession();
	    MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
	    if (mdto == null) {
	        url = "shop.do?command=loginForm";
	    } else {	
	    	QnaDao qdao = QnaDao.getInstance();
	    	QnaDto qna_id = null;
			QnaDto qdto = qdao.uploadQna(qna_id);
			request.setAttribute("qnaDto", qdto);
	    }
		request.getRequestDispatcher(url).forward(request, response);
	}
}