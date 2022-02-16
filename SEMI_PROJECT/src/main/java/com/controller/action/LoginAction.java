package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberDao;
import com.dto.MemberDto;


public class LoginAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "login/userLogin.jsp";

		// Get Parameter
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");

		// Get MemberDto
		MemberDto mdto = MemberDao.getInstance().getMember(userid);

		// Check whether user exits
		if(mdto == null) {
			request.setAttribute("message", "아이디가 존재하지 않아요");
		// Check whether password matches
		} else if(!mdto.getPassword().equals(pwd)) {
			request.setAttribute("message", "비밀번호가 틀렸어요");
		} else if(mdto.getPassword().equals(pwd)) {
			request.setAttribute("message", "환영해요 " + mdto.getName() + "님!");
			url = "main.jsp";
		} else {
			request.setAttribute("message", "알 수 없는 이유로 로그인 할 수 없었어요. 다시 시도해 주세요");
		}

		request.getRequestDispatcher(url).forward(request, response);
	}
}
