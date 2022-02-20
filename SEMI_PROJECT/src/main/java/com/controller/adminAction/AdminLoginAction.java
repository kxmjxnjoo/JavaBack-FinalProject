package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.AdminDao;
import com.dto.AdminDto;

public class AdminLoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "spring.do?command=adminLogin";
		HttpSession session = request.getSession();
		String adminid = request.getParameter("adminid");
	    String password = request.getParameter("password");
	    
	    AdminDao adao = AdminDao.getInstance();
	    AdminDto adto = adao.adminCheck(adminid);
	    if( adto == null) request.setAttribute("message", "없는 아이디입니다");
	    else if( adto.getPassword() == null) request.setAttribute("message", "비밀번호를 입력하세요");
	    else if( !adto.getPassword().equals(password) ) request.setAttribute("message", "비밀번호가 틀렸습니다");
	    else {
	    	url = "spring.do?command=memberList";
	    	System.out.println("로그인 완");
	    	session.setAttribute("adminLogin", adto);
	    }
	    response.sendRedirect(url);
	}
}