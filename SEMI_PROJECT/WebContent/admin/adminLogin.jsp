<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="admin_submenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="css/admin.css">
<!-- <script type="text/javascript">
 function workerCheck(){
	  if(document.frm.adminid.value==""){
	      	alert("아이디를 입력하세요.");
	      	return false;
	  }else if(document.frm.password.value==""){
	     	alert("비밀번호를 입력하세요.");
	      	return false;
	  }
	  return true;  
} 
</script> -->
</head>
<body>
<article>
	<form name="frm" method="post" action="spring.do" id="id_pwd" >
	<div class="loginForm">
		<div class="title">아이디</div>
			<input type="hidden" name="command" value="adminLogin">
			<div class="text_box">
			<input type ="text" name="adminid"></div>
		<div class="title">비밀번호</div>
			<div class="text_box">
			<input type ="text" name="password"></div>
		</div>
		<input type="submit" id="login" value="로그인" onClick="location.href='spring.do?command=adminMain'">
		 <div style="color:red">${message}</div>
	</form>
</article>
</body>
</html>
