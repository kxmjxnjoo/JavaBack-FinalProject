package com.controller.adminAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.action.Action;
import com.dao.QnaDao;
import com.dto.QnaDto;

public class commentQnaAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		QnaDto qdto = new QnaDto();
		
		int qna_num = Integer.parseInt( request.getParameter("qna_num") );
		// 아이디, 내용, 게시물 번호를 rdto 에 저장
		qdto.setQna_id(request.getParameter("qna_id"));
		qdto.setQna_content(request.getParameter("comment"));
		qdto.setQna_num( qna_num );
		
		QnaDao qdao = QnaDao.getInstance();
		qdao.addComment(qdto);
		
		response.sendRedirect("srping.do?command=qnaView="+qna_num);
	}
}