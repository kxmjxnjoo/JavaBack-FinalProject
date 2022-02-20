package com.controller.adminAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.QnaDao;
import com.dto.MemberDto;
import com.dto.QnaDto;

public class QnaListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "qnaList.jsp";
		
		HttpSession session = request.getSession();
	    MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
	    if (mdto == null) {
	    	url = "shop.do?command=loginForm";
	    } else {
	    	QnaDao qdao = QnaDao.getInstance();
	    	ArrayList<QnaDto> list = qdao.listQna( mdto.getUserid() );
	    	request.setAttribute("qnaList", list);
	    }
	    request.getRequestDispatcher(url).forward(request, response);
	}
}