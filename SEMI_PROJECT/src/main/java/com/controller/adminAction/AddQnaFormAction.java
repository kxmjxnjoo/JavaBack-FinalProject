package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;

public class AddQnaFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/useradmin/addQna.jsp";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "/member/loginForm.jsp";
			request.setAttribute("message", "QNA를 등록하기 위해서는 로그인 해 주세요");
		} 
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
