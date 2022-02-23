package com.controller.action;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.NotificationViewDao;
import com.dto.MemberDto;
import com.dto.NotificationViewDto;

public class NotificationAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/noti/notification.jsp";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
		} else {
			// Get userid
			String userid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
		
			// Get noti
			ArrayList<NotificationViewDto> notiList = NotificationViewDao.getInstance().getNotification(userid);
			System.out.println("list.size : " +  notiList.size());
			System.out.println(notiList.get(0).getUserFrom());
			
			if(notiList != null) {
				
				for(NotificationViewDto ndto : notiList) {
					LocalDate now = LocalDate.now();
					LocalDate notiDate = ndto.getCreateDate().toLocalDate();
					Period period = Period.between(now, notiDate);
					long diff = Math.abs(period.getDays());
					
					if(diff == 0) {
						ndto.setDatePassed("오늘");
					} else if(diff < 30) {
						ndto.setDatePassed(diff + "일전");
					} else if(diff < 365) {
						ndto.setDatePassed(Math.abs(diff / 30) + "달전");
					} else {
						ndto.setDatePassed("오래전");
					}
				}
			}

			
			// Send list
			request.setAttribute("notiList", notiList);
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
