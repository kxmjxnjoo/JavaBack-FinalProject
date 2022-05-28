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
	
	<form action="spring.do" method="post" id="editfaqBox">
		<input type="hidden" name="command" value="editfaq">
		<input type="hidden" name="num" value="${ faq.faq_num }">
		
		<input type="text" name="subject" placeholder="${ faq.faq_subject }">
		<textarea name="content"></textarea>
		
		<input type="submit" value="FAQ 등록">
		
	</form> 
	
	<%@ include file="/admin/common/footer.jsp" %>
	
</body>
</html>