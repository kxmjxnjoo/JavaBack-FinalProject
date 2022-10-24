package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;
import com.dto.MemberDto;
import com.dto.PostDto;

public class ReportFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/post/report.jsp";
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else{
			if(request.getParameter("story_num") == null && request.getParameter("userid") == null) {
				int post_num = Integer.parseInt(request.getParameter("post_num"));
				request.setAttribute("post_num", post_num);
			} else if(request.getParameter("post_num") == null && request.getParameter("userid") == null){
				int story_num = Integer.parseInt(request.getParameter("story_num"));
				request.setAttribute("story_num", story_num);
			} else {
				String userid = request.getParameter("userid");
				request.setAttribute("userid", userid);
				url = "/post/userReport.jsp";
			}
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
