package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberDao;
import com.dto.MemberDto;

public class FindPwdAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/findidpwdForm.jsp";
		// Hard coding value of certnum
		String certNum = "1111";
		
		// Get Parameter
		String userid = request.getParameter("userid");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String certNumInput = request.getParameter("certnum");
		
		// Check certnum
		if(!certNum.equals(certNumInput)) {
			request.setAttribute("message", "인증번호가 일치하지 않아요");
		} else {
			MemberDto mdto = MemberDao.getInstance().getMember(userid);
			if(mdto == null) {
				request.setAttribute("message", "일치하는 아이디가 없어요");
			} else if(!mdto.getName().equals(name)) {
				request.setAttribute("message", "이름이 일치하지 않아요");
			} else if(!mdto.getPhone().equals(phone)) {
				request.setAttribute("message", "전화번호가 일치하지 않아요");
			} else {
				url = "member/loginForm.jsp";
				request.setAttribute("message", "비밀번호는 " + mdto.getPassword() + "이에요");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
