package com.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
import com.dto.MemberDto;

public class EditProfileAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String url = "member/editProfile.jsp";
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "member/loginForm.jsp";
			session.setAttribute("message", "로그인 해 주세요!");
		} else {
			// Get loginUser mdto
			MemberDto loginUser = (MemberDto) (session.getAttribute("loginUser"));
			
			// Get Parameter			
			String pwd = request.getParameter("pwd");
			if(pwd.equals(""))			pwd = loginUser.getPassword();

			String name = request.getParameter("name");
			if(name.equals(""))			name = loginUser.getName();

			String email = request.getParameter("email");
			if(email.equals(""))		email = loginUser.getEmail();

			String phone = request.getParameter("phone");
			if(phone.equals(""))		phone = loginUser.getPhone();

			String introduce = request.getParameter("introduce");
			if(introduce.equals(""))	introduce = loginUser.getIntroduce();

			// Create MemberDto
			MemberDto mdto = new MemberDto();
			mdto.setPassword(pwd);
			mdto.setName(name);
			mdto.setEmail(email);
			mdto.setPhone(phone);
			mdto.setIntroduce(introduce);
			
			// Insert into db
			int result = MemberDao.getInstance().updateMember(mdto, loginUser.getUserid());
			
			// Get result
			if(result == 1) {
				request.setAttribute("message", "수정에 성공했어요");
			} else {
				request.setAttribute("message", "수정에 실패했어요. 다시 시도해 주세요");
			}
		}
		
		request.getRequestDispatcher(url).forward(request, response);
	}
}
