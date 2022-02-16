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
		//MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		//if(mdto==null) url = "spring.do?command=login";
		//else {
		
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath("images");
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		String userId = mdto.getUserid();
		
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		
		PostDto pdto = new PostDto();
		pdto.setAddress(multi.getParameter("post_address"));
		pdto.setContent(multi.getParameter("post_content"));
		pdto.setPost_img(multi.getFilesystemName("post_img"));
		pdto.setUserid(userId); //추후 수정 
		
		PostDao pdao = PostDao.getInstance();
		pdao.uploadPost(pdto);
		
		int post_num = pdao.get_post_num(mdto.getUserid()); //추후 session에 저장된 유저아이디 사용
		url = "spring.do?command=postDetail&post_num=" + post_num;
		
		
		//}
		response.sendRedirect(url);
	}
}
