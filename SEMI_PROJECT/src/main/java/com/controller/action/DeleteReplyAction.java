package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;

public class DeleteReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		//MeberDto mdto = (MemberDto) sessio.getAttribute("loginAdmin");
		//if(mdto==null) url = "spring.do?command=login";
		//else {
			int post_num = Integer.parseInt(request.getParameter("post_num"));
			int reply_num = Integer.parseInt(request.getParameter("reply_num"));
			String url = "spring.do?command=postDetail&post_num=" + post_num;
			PostDao.getInstance().deleteReply(reply_num);
			
		//}
		request.getRequestDispatcher(url).forward(request, response);
		
		
	}
}
