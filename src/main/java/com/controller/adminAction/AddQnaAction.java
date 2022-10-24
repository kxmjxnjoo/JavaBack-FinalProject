package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.QnaDao;
import com.dto.MemberDto;
import com.dto.QnaDto;

public class AddQnaAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "spring.do?command=userqna";
		HttpSession session = request.getSession();
		
		// Check if user is logged in 
		if(session.getAttribute("loginUser") == null) {
			request.setAttribute("message", "QNA를 등록하기 위해서는 로그인 해 주세요!");
			url = "/member/loginForm.jsp";
		} else {
			// Get parameter
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String userid = ((MemberDto) session.getAttribute("loginUser")).getUserid();
			
			// Create qdto
			QnaDto qdto = new QnaDto();
			qdto.setQna_subject(subject);
			qdto.setQna_content(content);
			qdto.setUsername(userid);
			
			// Insert qdto into db
			int result = QnaDao.getInstance().uploadQna(qdto);
			
			// Get result
			if(result == 1) {
				request.setAttribute("message", "QNA를 등록했어요! 최대한 빨리 답장해 드릴게요");
			} else {
				request.setAttribute("message", "QNA를 등록되지 못 했어요. 다시 시도해 주세요");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
