package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberDao;
import com.dto.MemberDto;

public class IdCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameter
		String id = request.getParameter("id");
		request.setAttribute("checkid", id);
		String url = "member/idcheckForm.jsp";
		
		// Get mdto
		MemberDto mdto = MemberDao.getInstance().getMember(id);
		
		// Check whether mdto is null
		if(mdto == null) {
			request.setAttribute("message", "사용하실 수 있는 아이디에요");
		} else {
			request.setAttribute("message", "이미 있는 아이디에요");
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
