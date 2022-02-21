package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.MemberDto;

public class UserReportFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/post/userReport.jsp";
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginAdmin");
			if(mdto==null) url = "spring.do?command=login";
			else{
					
			String reportedUserid = request.getParameter("userid");
			request.setAttribute("reportedUserid", reportedUserid);
		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}
}
