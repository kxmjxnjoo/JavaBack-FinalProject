package com.controller.adminAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.AdminDao;
import com.dao.ReportDao;
import com.dto.AdminDto;
import com.dto.ReportDto;
import com.util.Paging;

public class ReportOrderAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "admin/report/adminReportOrderList.jsp";

		HttpSession session = request.getSession();
		AdminDto adto = (AdminDto)session.getAttribute("adminLogin");
		if(adto == null) url = "spring.do?command=admin";
		else {

			String searchKey = request.getParameter("key") == null ? "" : request.getParameter("key");
			int reportOrder = Integer.parseInt(request.getParameter("reportOrder"));
			System.out.println(reportOrder);
			
			ReportDao rdao = ReportDao.getInstance();
			
			Paging paging = new Paging();
			int page=1;  // 처음 게시판을 열었을때
			
			if( request.getParameter("page") != null)
				page = Integer.parseInt( request.getParameter("page") );
			paging.setPage(page);
			int count = 1;
			
			ArrayList<ReportDto> list = null;
			
			if (reportOrder == 1) {
				list = rdao.reportList(paging, searchKey);
				count = AdminDao.getInstance().getAllCount();
			} else if (reportOrder == 2) {
				list = rdao.reportListAsc(paging, searchKey);
				count = AdminDao.getInstance().getAllCount();
			} else if (reportOrder == 3) {
				list = rdao.reportList(paging, searchKey, "post");
				count = AdminDao.getInstance().getSort("post");
			} else if (reportOrder == 4) {
				list = rdao.reportList(paging, searchKey, "story");
				count = AdminDao.getInstance().getSort("story");
			} else if (reportOrder == 5) {
				list = rdao.reportList(paging, searchKey, "user");
				count = AdminDao.getInstance().getSort("user");
			}
			
			paging.setTotalCount(count);
			
			request.setAttribute("reportList", list);	
			request.setAttribute("reportOrder", reportOrder);	
			request.setAttribute("paging", paging);	
		}
		request.getRequestDispatcher(url).forward(request, response);
	}
}
