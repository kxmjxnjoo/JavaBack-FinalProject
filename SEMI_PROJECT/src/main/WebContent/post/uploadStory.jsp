<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스토리 작성</title>
<link href="../css/spring.css" rel="stylesheet"> 
<link href="../css/posting.css" rel="stylesheet"> 
<link href="../css/story.css" rel="stylesheet"> 
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>
<body>
<%@ include file="/topnav/topnav.jsp" %>

<form name="frm" method="post" enctype="multipart/form-data">
	<div class="wrap">
		<div id="postingStory" >
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
				<select name="fontColor" id="fontColor" class="select-css">
					<option> 스토리에 표시될 폰트 색을 선택해주세요 </option>
					<option value = "white"> 흰색 </option>
					<option value = "black"> 검정 </option>
					<option value = "purple"> 보라 </option>
					<option value = "yellow"> 노랑 </option>
					<option value = "orange"> 주황 </option>
					<option value = "pink"> 분홍 </option>
					<option value = "green"> 초록 </option>
					<option value = "#e59999"> 스프링 </option>
				</select>
				<div id="postingText">
					<textarea name="post_content"
					onKeyDown="textCounter(this.form.post_content,this.form.remLen,100);" 
					onKeyUp="textCounter(this.form.post_content,this.form.remLen,100);"
					placeholder="사진을 설명해주세요" rows="4" cols="50"></textarea>
				</div>
				<div id="clear"></div>
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
	}; 
	
	reader.readAsDataURL(event.target.files[0]); 
}


function goBack(){
	window.history.back();
}

function reset(){
	document.querySelector("div#image_container").innerHTML = '';
	document.querySelector("div#image_container").appendChild(img);
	
	document.getElementById("input-file").select();
	document.selection.clear();
	/* var remove = document.getElementById("image_container");
	remove.removeChild(remove.childNodes[0]); */
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
		theForm.action="spring.do?command=storyUpload";
		theForm.submit();
	}
}

</script>
</body>
</html>