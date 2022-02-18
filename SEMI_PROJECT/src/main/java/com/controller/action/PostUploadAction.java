package com.controller.action;

import java.io.IOException;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PostDao;
import com.dto.MemberDto;
import com.dto.PostDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class PostUploadAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url ="";
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else {
		
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath("images");
		String userId = mdto.getUserid();
		
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		
		PostDto pdto = new PostDto();
		pdto.setAddress(multi.getParameter("post_address"));
		pdto.setContent(multi.getParameter("post_content"));
		pdto.setPost_img(multi.getFilesystemName("post_img"));
		pdto.setUserid(userId); //추후 수정 
		
		System.out.println("로그인 유저 " +userId);////////////////////////////////////////////////
		System.out.println("포스트 작성자 " + pdto.getUserid());//////////////////////////////////////////////
		
		PostDao pdao = PostDao.getInstance();
		pdao.uploadPost(pdto);
		
		int post_num = pdao.get_post_num(userId);
		url = "spring.do?command=postDetail&post_num=" + post_num;
		
		}
		response.sendRedirect(url);
	}
}
