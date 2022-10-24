package com.controller.adminAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.AdminDao;
import com.dto.MemberDto;
import com.util.Paging;

public class MemberListAction implements Action {


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/member/adminMemberList.jsp";
		HttpSession session = request.getSession();
		
		if(session.getAttribute("adminLogin") == null) {
			url = "admin/adminLogin.jsp";
		} else {
			String searchKey = request.getParameter("key") == null ? "" : request.getParameter("key");

			AdminDao adao = AdminDao.getInstance();
			
			Paging paging = new Paging();
			int page=1;  // 처음 게시판을 열었을때
			
			if( request.getParameter("page") != null)
				page = Integer.parseInt( request.getParameter("page") );
			paging.setPage(page);
			
			ArrayList<MemberDto> list = adao.MemberList(paging, searchKey);
			
			int count = 1;
			paging.setTotalCount(count);
			
			request.setAttribute("memberList" , list);
			request.setAttribute("paging", paging);	
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}