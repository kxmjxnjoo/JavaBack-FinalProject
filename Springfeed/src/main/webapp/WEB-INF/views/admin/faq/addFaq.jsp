<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ 추가</title>
<link rel="stylesheet" href="../../css/admin/faqAddForm.css">
</head>
<body>
	<%@ include file="../../admin/common/admin_submenu.jsp" %>
	
	<form name="frm" action="/faq/add/form" method="post" id="addFaqForm">
		
		<input type="text" name="faq_subject" placeholder="FAQ 제목">
		<textarea name="faq_content" placeholder="FAQ 내용"></textarea>
		
		<input type="submit" value="FAQ 등록" >
		
	</form> 
	
	<%@ include file="../../admin/common/footer.jsp" %>

</body>
</html>