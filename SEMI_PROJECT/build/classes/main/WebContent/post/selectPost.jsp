<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/spring.css" rel="stylesheet"> 
<script src="script/spring.js"></script>
</head>
<body>
<form name="frm">
	<div class="button_wrap">
		<div class="icon_wrap">
			<div class="button">
				<a href='spring.do?command=postuploadForm'><img src="../images/postupload.png" height=300px width=230px></a>	
			</div>
			<div class="button" onclick="go_storyupload();">
				<a href='spring.do?command=storyUploadForm'><img src="../images/storyupload.png" height=300px width=230px></a>
			</div>
		</div>	
	</div>
</form>
</body>
</html>