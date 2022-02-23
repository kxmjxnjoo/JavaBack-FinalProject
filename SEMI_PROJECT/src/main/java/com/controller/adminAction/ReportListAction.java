package com.controller.adminAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.ReportDao;
import com.dto.AdminDto;
import com.dto.ReportDto;
import com.util.Paging;

public class ReportListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "admin/adminReportList.jsp";
		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		if(adto == null) url = "spring.do?command=admin";
		else {
			url = "admin/adminReportList.jsp";

			ReportDao rdao = ReportDao.getInstance();
			
			Paging paging = new Paging();
			int page=1;  // 처음 게시판을 열었을때
			
			if( request.getParameter("page") != null)
				page = Integer.parseInt( request.getParameter("page") );
			paging.setPage(page);
			
			int count = 1;
			paging.setTotalCount(count);
			
			String report_num = "";
			ArrayList<ReportDto> list = rdao.reportList(Integer.parseInt(report_num));
			
			request.setAttribute("reportList", list);	
			request.setAttribute("paging", paging);	
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}