<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 추가</title>
<link rel="stylesheet" href="/admin/css/faqAddForm.css">
</head>
<body>
	<%@ include file="/admin/common/admin_submenu.jsp" %>
	
	<form action="spring.do" method="post" id="addFaqForm">
		<input type="hidden" name="command" value="addfaq">
		
		<input type="text" name="subject" placeholder="FAQ 제목">
		<textarea name="content" placeholder="FAQ 내용"></textarea>
		
		<input type="submit" value="FAQ 등록">
		
	</form> 
	
	<%@ include file="/admin/common/footer.jsp" %>

</body>
</html>