package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.QnaDao;

public class DeleteQnaAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "admin/qna/adminQnaList.jsp";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		if(session.getAttribute("adminLogin") == null) {
			url = "admin/adminLogin.jsp";
			request.setAttribute("message", "QNA를 지우기 위해서는 관리자로 로그인해 주세요");
		} else {
			// Get Parameter
			int num = Integer.parseInt(request.getParameter("num"));
			
			// Delete qna from DB
			int result = QnaDao.getInstance().deleteQna(num);
			
			// Get result
			if(result == 1) {
				request.setAttribute("message", "QNA를 지웠어요!");
			} else {
				request.setAttribute("message", "QNA를 지우지 못 했어요. 다시 시도해 주세요");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}