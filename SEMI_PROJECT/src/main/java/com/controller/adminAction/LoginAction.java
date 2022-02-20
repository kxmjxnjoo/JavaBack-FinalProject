package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.MemberDao;
import com.dto.MemberDto;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/login.jsp";
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");
		
		MemberDao mdao = MemberDao.getInstance();
	    MemberDto mdto = mdao.getMember(id);
		
		if( mdto == null ) {
	    	request.setAttribute("message", "아이디가 없습니다.");
	    } else if( mdto.getPassword() == null ) {
	    	request.setAttribute("message", "회원정보 오류. 관리자에게 문의하세요");
	    } else if( !mdto.getPassword().equals(pwd) ) {
	    	request.setAttribute("message", "비밀번호가 틀렸습니다.");
	    }else if(mdto.getPassword().equals(pwd)&& !mdto.getUseyn().equals("y")) {
	    	request.setAttribute("message", "탈퇴했거나 휴면계정입니다, 고객센터에 문의하세요");
	    } else if( mdto.getPassword().equals(pwd) ) {
	    	HttpSession session=request.getSession();
	    	session.setAttribute("loginUser", mdto);
	    	url="shop.do?command=index";
	    } else {
	    	request.setAttribute("message", "로그인에 실패습니다. 관리자에게 문의하세요");
	    }
	    
		RequestDispatcher dp=request.getRequestDispatcher(url);
		dp.forward(request, response);
	}
}