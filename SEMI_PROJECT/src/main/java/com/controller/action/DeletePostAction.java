package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;

public class DeletePostAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "spring.do?command=main";
		HttpSession session = request.getSession();
		//MeberDto mdto = (MemberDto) sessio.getAttribute("loginAdmin");
		//if(mdto==null) url = "spring.do?command=login";
		//else {
			int post_num = Integer.parseInt(request.getParameter("post_num"));
			PostDao.getInstance().deletePost(post_num);
			
			request.setAttribute("deletedPost", 1); //모달 추가용 어트리부트
		//}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
