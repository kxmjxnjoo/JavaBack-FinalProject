package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberDao;
import com.dto.MemberDto;

public class FindIdAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameter
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		// Get Mdto from db
		MemberDto mdto = MemberDao.getInstance().getUserid(name, phone);
		
		// Return userid if mdto is not null
		if(mdto != null) {
			String userid = mdto.getUserid();
			request.setAttribute("message", "아이디는 " + userid + "에요");
		} else {
			request.setAttribute("message", "주신 정보로는 아이디를 찾지 못 했어요");
		}
		
		request.getRequestDispatcher("member/findidpwd.jsp").forward(request, response);
	}

}
