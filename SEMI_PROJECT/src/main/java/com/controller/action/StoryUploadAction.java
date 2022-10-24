package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StoryDao;
import com.dto.MemberDto;
import com.dto.StoryDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class StoryUploadAction implements Action {

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

		StoryDto sdto = new StoryDto();
		sdto.setContent(multi.getParameter("post_content"));
		sdto.setStory_img(multi.getFilesystemName("post_img"));
		sdto.setFontColor(multi.getParameter("fontColor"));
		sdto.setUserid(userId); 
		
		StoryDao sdao = StoryDao.getInstance();
		sdao.uploadStory(sdto);
		
		int story_num = sdao.get_story_num(mdto.getUserid());
		url = "spring.do?command=storyDetail&story_num=" + story_num;
		
		}
		response.sendRedirect(url);
	}
}
