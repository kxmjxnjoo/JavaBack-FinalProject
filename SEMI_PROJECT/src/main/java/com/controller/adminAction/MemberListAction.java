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


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String url = "admin/adminMemberList.jsp";
		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		if(adto == null) url = "spring.do?command=admin";
		else {

			AdminDao adao = AdminDao.getInstance();
			
			Paging paging = new Paging();
			int page=1;  // 처음 게시판을 열었을때
			
			if( request.getParameter("page") != null)
				page = Integer.parseInt( request.getParameter("page") );
			paging.setPage(page);
			
			ArrayList<MemberDto> list = adao.MemberList( paging, "key");
			int count = 0;
			// setTotalCount 메서드 안에는 pagein() 메서드 호출명령이 같이 들어있음
			paging.setTotalCount(count);  // 레코드 총갯수 세팅 + 각 멤버변수 값 계산
			
			/*
			 * for( MemberDto mdto : list) { int cnt = mdao.getReplycnt( bdto.getNum() );
			 * bdto.setReplycnt(cnt); }
			 */
			
			request.setAttribute("MemberList" , list);
			request.setAttribute("paging", paging);	
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}