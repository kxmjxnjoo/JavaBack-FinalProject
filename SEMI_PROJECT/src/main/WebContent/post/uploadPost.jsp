<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>포스트 작성</title>
<link href="../css/spring.css" rel="stylesheet"> 
<link href="../css/posting.css" rel="stylesheet"> 
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script type="text/javascript" language="javascript" defer="defer"> 
function setThumbnail(event) { 
	var reader = new FileReader(); 
	reader.onload = function(event) { 
		var img = document.createElement("img"); 
		img.setAttribute("src", event.target.result); 
		document.querySelector("div#image_container").appendChild(img); 
		document.querySelector("div#image_container").style.display = 'block';
	}; 
	
	reader.readAsDataURL(event.target.files[0]); 
}


function goBack(){
	window.history.back();
}

function reset(){
	document.querySelector("div#image_container").style.display = 'none';
	document.getElementById("image_container").style.display='none'
	document.getElementById("input-file").select();
	document.selection.clear();

}


function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) 
	field.value = field.value.substring(0, maxlimit);
	else 
	countfield.value = maxlimit - field.value.length;
}

function uploadCheck(){
	var theForm = document.frm;
	if( theForm.post_img.value=="") {
		alert('사진을 첨부해주세요'); 
		return;
	} else {
		theForm.action="spring.do?command=postUpload";
		theForm.submit();
	}
}

</script>

</head>
<body>
<%@ include file="/topnav/topnav.jsp" %>
<form name="frm" method="post" enctype="multipart/form-data">
	<div class="wrap">
		<div id="postingDiv" >
			<div id="postingTitle">
				<span class="material-icons" id="goBack" onclick="goBack();">west</span>
				<label id="newPost"> 새 게시물 </label>
				
			</div>
			<div id="clear"></div>
			<div id="postingContent">
				<div id="thumbnail">
					<label class="input-file-button" for="input-file" >
					  업로드
					</label>
					<div id ="image_container"></div>
					<input type="file" name="post_img" id="input-file"  onchange="setThumbnail(event);"/>
				</div>
				<div id="clear"></div>
				<div id="postingText">
					<textarea name="post_content"
					onKeyDown="textCounter(this.form.post_content,this.form.remLen,100);" 
					onKeyUp="textCounter(this.form.post_content,this.form.remLen,100);"
					placeholder="사진을 설명해주세요" rows="4" cols="50"></textarea>
				</div>
				<div id="adrress">
					<textarea name="post_address" rows="1" cols="50" 
					onKeyDown="textCounter(this.form.post_address,this.form.remLen,40);" 
					onKeyUp="textCounter(this.form.post_address,this.form.remLen,40);"
					placeholder="어디에 계신지 알려주세요"></textarea> 
				</div>
<!-- 등록/다시작성 -->
				
				<div id="submit-buttons">
					<label id="input-submit-button" for="input-submit">
					등록
					</label>
					<input type="button" name="input-submit" id="input-submit"  onclick="uploadCheck();">
					<label id="input-reset-button" for="input-reset" >
					다시 작성 
					</label>
					<input type="button" id="input-reset"  onclick="reset();">
				</div>
			</div>
		</div>
	</div>
</form>

<script type="text/javascript" language="javascript" defer="defer"> 
function setThumbnail(event) { 
	var reader = new FileReader(); 
	reader.onload = function(event) { 
		var img = document.createElement("img"); 
		img.setAttribute("src", event.target.result); 
		document.querySelector("div#image_container").appendChild(img); 
		document.querySelector("div#image_container").style.display = 'block';
	}; 
	
	reader.readAsDataURL(event.target.files[0]); 
}


function goBack(){
	window.history.back();
}

function reset(){
	document.querySelector("div#image_container").style.display = 'none';
	document.getElementById("image_container").style.display='none';
	document.getElementById("input-file").select();
	document.selection.clear();

}


function textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit) 
	field.value = field.value.substring(0, maxlimit);
	else 
	countfield.value = maxlimit - field.value.length;
}

function uploadCheck(){
	var theForm = document.frm;
	if( theForm.post_img.value=="") {
		alert('사진을 첨부해주세요'); 
		return;
	} else {
		theForm.action="spring.do?command=postUpload";
		theForm.submit();
	}
}

</script>
</body>
</html>