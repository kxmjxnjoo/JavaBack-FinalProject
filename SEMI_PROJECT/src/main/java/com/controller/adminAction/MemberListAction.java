package com.controller.adminAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.AdminDao;
import com.dto.AdminDto;
import com.dto.MemberDto;
import com.util.Paging;

public class MemberListAction implements Action {

	private int page;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/adminMemberList.jsp";
		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		if(adto == null) url = "spring.do?command=admin";
		else {
			
			url = "spring.do?command=memberList";
			Paging paging = null;
			String key = null;
			ArrayList<MemberDto> mdto = AdminDao.getInstance().MemberList(paging, key); 
			
			//page 
		int page = 1;
		if( request.getParameter("page") != null ) {
			page = Integer.parseInt(request.getParameter("page"));
			session.setAttribute("page", page);
		} else if(session.getAttribute("page") != null ) {
			page = (int)session.getAttribute("page");
		} page = 1;
			session.removeAttribute("page");
		}
		
		Paging paging = new Paging();
		paging.setPage(page);	
		
		AdminDao adao = AdminDao.getInstance();
		
		String key = "";
		if(request.getParameter("key") != null) {
			key = request.getParameter("key");
			session.setAttribute("key", key);
		} else if(session.getAttribute("key") != null) {
			key = (String)session.getAttribute("key");
		} else {
			session.removeAttribute("key");
			key = "";
		}
		
		int count = adao.searchAllMember("Member", "name", key);
		
		paging.setTotalCount(count);
		request.setAttribute("paging", paging);
		
		ArrayList<MemberDto> memberList = adao.MemberList(paging, key);
		request.setAttribute("memberList", memberList);
		request.setAttribute("key", key);
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}