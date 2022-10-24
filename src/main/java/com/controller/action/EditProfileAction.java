package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
import com.dto.MemberDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class EditProfileAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "spring.do?command=userpage";
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
			session.setAttribute("message", "로그인 해 주세요!");
		} else {
			// Get loginUser mdto
			MemberDto loginUser = (MemberDto) (session.getAttribute("loginUser"));
			
			// multi file
			ServletContext context = session.getServletContext();
			String uploadFilePath = context.getRealPath("images");
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			
			// Get Parameter			
			String pwd = multi.getParameter("pwd");
			if(pwd.equals(""))			pwd = loginUser.getPassword();

			String name = multi.getParameter("name");
			if(name.equals(""))			name = loginUser.getName();

			String email = multi.getParameter("email");
			if(email.equals(""))		email = loginUser.getEmail();

			String phone = multi.getParameter("phone");
			if(phone.equals(""))		phone = loginUser.getPhone();

			String introduce = multi.getParameter("introduce");
			if(introduce.equals(""))	introduce = loginUser.getIntroduce();

			String img = "";
			if(multi.getFilesystemName("user_img")==null) img = multi.getParameter("oldPicture");
			else img = multi.getFilesystemName("user_img");
			
			// Create MemberDto
			MemberDto mdto = new MemberDto();
			mdto.setPassword(pwd);
			mdto.setName(name);
			mdto.setEmail(email);
			mdto.setPhone(phone);
			mdto.setIntroduce(introduce);
			mdto.setImg(img);
			
			// Edit loginUser 
			loginUser.setPassword(pwd);
			loginUser.setName(name);
			loginUser.setEmail(email);
			loginUser.setPhone(phone);
			loginUser.setIntroduce(introduce);
			loginUser.setImg(img);
			
			// Insert into db
			int result = MemberDao.getInstance().updateMember(mdto, loginUser.getUserid());
			
			// Get result
			if(result == 1) {
				request.setAttribute("message", "수정에 성공했어요");
				request.setAttribute("userid", loginUser.getUserid());
			} else {
				request.setAttribute("message", "수정에 실패했어요. 다시 시도해 주세요");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
