package com.controller.adminAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.action.Action;
import com.dao.PostDao;
import com.dto.PostDto;
import com.dto.ReplyDto;

public class PostReportCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/postReportCheck.jsp";
		int post_num = Integer.parseInt(request.getParameter("post_num"));
		int report_num  = Integer.parseInt(request.getParameter("report_num"));
		PostDao pdao = PostDao.getInstance();
		PostDto pdto = pdao.getPost(post_num);
		ArrayList<ReplyDto> rdto = pdao.getReply(post_num);
		
		request.setAttribute("PostDto", pdto);
		request.setAttribute("post_num", post_num);
		request.setAttribute("ReplyDto", rdto);
		request.setAttribute("report_num", report_num);
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
