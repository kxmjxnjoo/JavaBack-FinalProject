

public class LoginFormAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HtttpSession session = request.getSession();
		String url = "login/login.jsp";
		
		// Check if user is logged in
		if(session.getAttribute("loginUser") == null) {
			url = "main.jsp";
		}

		request.getRequestDispatcher(url).forward(request, response);
	}
}
