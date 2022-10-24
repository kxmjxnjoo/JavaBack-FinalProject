package com.controller.adminAction;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.action.Action;
import com.dao.FollowDao;
import com.dao.MemberDao;
import com.dao.MemberViewDao;
import com.dao.PostDao;
import com.dao.PostViewDao;
import com.dto.MemberDto;
import com.dto.MemberViewDto;
import com.dto.PostDto;
import com.dto.PostViewDto;
import com.dto.ReplyDto;

public class UserReportCheckAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "/admin/report/userReportCheck.jsp";
		String userid = request.getParameter("userid");
		int report_num  = Integer.parseInt(request.getParameter("report_num"));
		
		MemberViewDto user = MemberViewDao.getInstance().getMember(userid);
		request.setAttribute("user", user);
		
		ArrayList<PostViewDto> postList = PostViewDao.getInstance().getAllPostById(userid);
		
		if(postList != null) {
			for(int i=0; i<postList.size(); i++) {
				int post_num = postList.get(i).getPostNum();
				PostDao pdao = PostDao.getInstance();
				postList.get(i).setLikeCount(pdao.likeCount(post_num));
				postList.get(i).setReplyCount(pdao.replyCount(post_num));
			}
			request.setAttribute("posts", postList);
		}
		
		request.setAttribute("report_num", report_num);
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
