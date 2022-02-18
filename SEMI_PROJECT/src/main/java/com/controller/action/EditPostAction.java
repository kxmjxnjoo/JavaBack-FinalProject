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

public class EditPostAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int post_num = Integer.parseInt(request.getParameter("post_num"));
		String url = "spring.do?command=postDetail&post_num=" + post_num;
		HttpSession session = request.getSession();
		//MeberDto mdto = (MemberDto) sessio.getAttribute("loginAdmin");
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
		pdto.setPostNum(post_num);
		if(multi.getFilesystemName("post_img")==null) pdto.setPost_img(multi.getParameter("oldPicture"));
		else pdto.setPost_img(multi.getFilesystemName("post_img"));
		
		PostDao pdao = PostDao.getInstance();
		pdao.editPost(pdto);
		
		request.setAttribute("PostDto", pdto);
		//}
		response.sendRedirect(url);
	}
}
