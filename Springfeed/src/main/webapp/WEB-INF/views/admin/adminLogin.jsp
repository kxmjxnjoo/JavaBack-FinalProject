<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../admin/common/admin_submenu.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자 로그인</title>
<link rel="stylesheet" href="/css/admin/admin.css">
<script type="text/javascript">
 function workerCheck(){
	  if(document.frm.adminId.value==""){
	      	alert("아이디를 입력하세요.");
	      	return false;
	  }else if(document.frm.adminPwd.value==""){
	     	alert("비밀번호를 입력하세요.");
	      	return false;
	  }
	  return true;  
}
</script>
</head>
<body>
<article>
	<form name="frm" id="id_pwd" method="post" action="/admin/loginForm">
	<div class="loginForm">
		<div class="title">아이디</div>
			<div class="text_box">
			<input type ="text" name="adminId" class="input"></div>
		<div class="title">비밀번호</div>
			<div class="text_box">
			<input type="password" name="adminPwd" class="input"></div>
		</div>
		<div style="color:red;">${message}</div>
		<input type="submit" id="login" value="로그인" onClick="return workerCheck();">
	</form>
</article>
</body>
</html>