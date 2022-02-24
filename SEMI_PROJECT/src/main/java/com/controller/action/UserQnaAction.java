package com.controller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.QnaDao;
import com.dto.MemberDto;
import com.dto.QnaDto;

public class UserQnaAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "useradmin/qna.jsp";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		ArrayList<QnaDto> qnaList = QnaDao.getInstance().getAllQna();
		request.setAttribute("qnaList", qnaList);

		if(session.getAttribute("loginUser") != null) {
			String userid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
			
			ArrayList<QnaDto> userQnaList = QnaDao.getInstance().listQna(userid);
			request.setAttribute("userQnaList", userQnaList);
		} 
		
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
