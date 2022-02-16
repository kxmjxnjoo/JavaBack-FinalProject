package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;
import com.dto.PostDto;

public class PostReportAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "reportDone.jsp";
		HttpSession session = request.getSession();
		//MeberDto mdto = (MemberDto) sessio.getAttribute("loginUser");
		//if(mdto==null) url = "spring.do?command=login";
		//else {
			//테스트용 코드 ///////////////////////////////////////////////////////
			String loginUser = "jojo"; //mdto.getUserid() 로 수정
			//여기까지 /////////////////////////////////////////////////////////
					
			int post_num = Integer.parseInt(request.getParameter("post_num"));
			String reason = request.getParameter("reportReson");
			if(reason.equals("1")) reason = "스팸";
			else if(reason.equals("2")) reason = "민감한 콘텐츠";
			else if(reason.equals("3")) reason = "폭력적인 콘텐츠";
			else if(reason.equals("4")) reason = "허위 사실 유포";
			else if(reason.equals("5")) reason = "자살 또는 자해 요소";
			else if(reason.equals("6")) reason = "불법 또는 규제 상품 판매";
			else if(reason.equals("7")) reason = "섭식 장애";
			else if(reason.equals("8")) reason = "혐오 발언 또는 상징";
			else if(reason.equals("9")) reason = "지식재산권 침해";
			PostDao pdao = PostDao.getInstance();
			PostDto pdto = pdao.getPost(post_num);
			String reported = pdto.getUserid();
			pdao.insertReport(loginUser, reported, post_num, reason);
			
			request.setAttribute("PostDto", pdto);
			request.setAttribute("reason", reason);
			
		//}
		response.sendRedirect(url);
	}
}
