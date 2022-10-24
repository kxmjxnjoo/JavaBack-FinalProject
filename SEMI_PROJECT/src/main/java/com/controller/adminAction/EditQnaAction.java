package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.QnaDao;
import com.dto.QnaDto;

public class EditQnaAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "spring.do?command=qnaList";
		HttpSession session = request.getSession();
		
		// Check if user is logged in
		if(session.getAttribute("adminLogin") == null) {
			url = "admin/adminLogin.jsp";
			request.setAttribute("message", "QNA를 수정하기 위해서 관리자로 로그인해 주세요");
		} else {
			// Get parameter
			int num = Integer.parseInt(request.getParameter("num"));
			String content = request.getParameter("reply");
			
			// Create qdto
			QnaDto qdto = new QnaDto();
			qdto.setQna_reply(content);
			qdto.setQna_num(num);
			
			// Insert reply into DB
			int result = QnaDao.getInstance().addReply(qdto);
			
			// Get result
			if(result == 1) {
				request.setAttribute("message", "QNA 답변을 등록했어요");
			} else {
				request.setAttribute("message", "QNA 답변을 동록하지 못 했어요. 다시 시도해 주세요");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	} 

}
