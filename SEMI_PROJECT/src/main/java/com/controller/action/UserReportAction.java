package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;
import com.dto.MemberDto;

public class UserReportAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/post/postReportDone.jsp";
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else {
		
			String loginUser = mdto.getUserid();
			String reportedUserid = request.getParameter("reportedUserid");
			String reason = request.getParameter("reportReson");
			if(reason.equals("1")) reason = "적합하지 않은 콘텐츠 게시";
			else if(reason.equals("2")) reason = "타인을 사칭하는 계정";
			else if(reason.equals("3")) reason = "만 14세 미만 계정";
			PostDao pdao = PostDao.getInstance();
			
			int result = pdao.insertReport(loginUser, reportedUserid, reason);
			String message = "";
			if (result==1) message = "유저를 신고했어요";
			else message = "유저를 신고하지 못했어요. 다시 시도해주세요.";
			
			System.out.println("message="+ message);
			request.setAttribute("reason", reason);
			request.setAttribute("message", message);
		}
			response.sendRedirect(url);
	}
}
