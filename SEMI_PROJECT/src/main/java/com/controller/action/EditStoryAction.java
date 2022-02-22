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

public class EditStoryAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int story_num = Integer.parseInt(request.getParameter("story_num"));
		String url = "spring.do?command=storyDetail&story_num=" + story_num;
		System.out.println(story_num);
		HttpSession session = request.getSession();
		MemberDto mdto = (MemberDto) session.getAttribute("loginUser");
		if(mdto==null) url = "spring.do?command=login";
		else {
		ServletContext context = session.getServletContext();
		String uploadFilePath = context.getRealPath("images");
		String userId = mdto.getUserid();
		
		MultipartRequest multi = new MultipartRequest(request, uploadFilePath, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
		StoryDto sdto = new StoryDto();
		sdto.setContent(multi.getParameter("story_content"));
		sdto.setStory_num(story_num);
		
		if(multi.getFilesystemName("story_img")==null) sdto.setStory_img(multi.getParameter("oldPicture"));
		else sdto.setStory_img(multi.getFilesystemName("story_img"));
		
		if(multi.getParameter("fontColor")==null) sdto.setFontColor(multi.getParameter("oldFontColor"));
		else sdto.setFontColor(multi.getParameter("fontColor"));
		
		StoryDao.getInstance().editPost(sdto);
		
		request.setAttribute("StoryDto", sdto);
		}
		response.sendRedirect(url);
	}
}
