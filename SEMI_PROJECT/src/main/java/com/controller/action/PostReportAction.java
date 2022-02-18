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
		int post_num = Integer.parseInt(request.getParameter("post_num"));
		String url = "/post/postReportDone.jsp";
		HttpSession session = request.getSession();
		//MeberDto mdto = (MemberDto) sessio.getAttribute("loginUser");
		//if(mdto==null) url = "spring.do?command=login";
		//else {
			//테스트용 코드 ///////////////////////////////////////////////////////
			String loginUser = "jojo"; //mdto.getUserid() 로 수정
			//여기까지 /////////////////////////////////////////////////////////
					
			
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
			
			int result = pdao.insertReport(loginUser, reported, post_num, reason);
			String message = "";
			if (result==1) message = "포스트를 신고했어요";
			else message = "포스트를 신고하지 못했어요. 다시 시도해주세요.";
			
			System.out.println("message="+ message);
			System.out.println("post_num="+post_num);
			System.out.println("post_num="+pdto.getUserid());
			request.setAttribute("post_num", post_num);
			request.setAttribute("PostDto", pdto);
			request.setAttribute("reason", reason);
			request.setAttribute("message", message);

			response.sendRedirect(url);
	}
}
