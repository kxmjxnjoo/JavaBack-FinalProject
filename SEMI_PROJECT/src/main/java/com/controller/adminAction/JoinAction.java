package com.controller.adminAction;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.action.Action;
import com.dao.MemberDao;
import com.dto.MemberDto;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			MemberDao mdao = MemberDao.getInstance();
			MemberDto mdto = new MemberDto();
			
			mdto.setUserid(request.getParameter("userid"));
			mdto.setPassword(request.getParameter("password"));
			mdto.setName(request.getParameter("name"));
			mdto.setEmail(request.getParameter("email"));
			mdto.setPhone(request.getParameter("phone"));

			int result = mdao.insertMember(mdto);
			String message="";
			if(result==1) message="앞으로 스프링피드와 함께 하세요. 반갑습니다!";
			else message="회원가입이 실패했습니다. 다시 시도하세요";
			response.sendRedirect("spring.do?command=loginForm&message=" + URLEncoder.encode( message, "UTF-8" )  );
		}
	}