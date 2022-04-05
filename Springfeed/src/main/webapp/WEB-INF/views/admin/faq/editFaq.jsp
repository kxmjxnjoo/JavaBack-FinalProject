<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 수정</title>
</head>
<body>

	<%@ include file="/admin/common/admin_submenu.jsp" %>
	
	<form action="/faq/edit/form" method="post" id="editfaqBox">
		<input type="hidden" name="command" value="editfaq">
		<input type="hidden" name="num" value="${ faq.FAQ_NUM }">
		
		<input type="text" name="subject" placeholder="${ faq.FAQ_SUBJECT }">
		<textarea name="content"></textarea>
		
		<input type="submit" value="FAQ 등록">
		
	</form> 
	
	<%@ include file="/admin/common/footer.jsp" %>
	
</body>
</html>