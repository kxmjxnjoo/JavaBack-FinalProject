<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 생성</title>
<link href="css/spring.css" rel="stylesheet"> 
<link href="css/posting.css" rel="stylesheet"> 
<script type="text/javascript" language="javascript" defer="defer">
function goBack(){
	window.history.back();
}
</script>
</head>
<body style="display: flex;  flex-direction: column; ">
<%@ include file="/topnav/topnav.jsp" %>
<form name="frm" id="frm">
	<div class="button_wrap">
		<div class="icon_wrap">
			<div id="button">
				<a href='spring.do?command=postuploadForm'><img src="../images/postupload.png" height=300px width=230px></a>	
			</div>
			<div id="button" onclick="go_storyupload();">
				<a href='spring.do?command=storyUploadForm'><img src="../images/storyupload.png" height=300px width=230px></a>
			</div>
		</div>	
		<div id="clear"></div>
		<div id="temp"  onclick="goBack();"> 
			<label id="input-goBack-button" for="input-reset"> 뒤로가기 </label>
			<input type="button" id="input-reset"> 
		</div>
		
	</div>
</form>
<footer style="margin-top: 20px;">
<%@ include file="/footer.jsp" %>
</footer>
</body>
</html>