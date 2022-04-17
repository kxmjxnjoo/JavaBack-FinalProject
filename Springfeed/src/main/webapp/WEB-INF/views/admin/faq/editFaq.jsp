<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 수정</title>
</head>
<body>

	<%@ include file="../../admin/common/admin_submenu.jsp" %>
	
	<form action="/faq/edit" method="post" id="editfaqBox">
		<input type="hidden" value="editfaq">
		<input type="hidden" name="faq_num" value="${ fdto.FAQ_NUM }">
		
		<input type="text" name="faq_subject" placeholder="${ fdto.FAQ_SUBJECT }">
		<textarea name="faq_content"></textarea>
		
		<input type="submit" value="FAQ 수정">
		
	</form> 
	
	<%@ include file="../../admin/common/footer.jsp" %>
	
</body>
</html>