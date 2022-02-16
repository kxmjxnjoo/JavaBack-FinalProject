<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 게시물</title>
<link href="css/spring.css" rel="stylesheet"> 
<script type="text/javascript" language="javascript" defer="defer">
function setThumbnail(event){
	var reader = new FileReader();
	reader.onload = function(event){
		var img = document.createElement("img");
		img.setAttribute("src", event.target.result);
		img.setAttribute("class", "col-lg-6");
		document.querySelector("div#image_container").appendChild(img).style.height='150px'.widht='150px';
	};
	reader.readAsDataURL(event.target.files[0]);
}

function editCheck(story_num){
	var theForm = document.frm;
	theForm.action="spring.do?command=editStory&story_num=" + story_num;
	theForm.submit();
}
	

</script>
</head>
<body>
<form name="frm" method="post" enctype="multipart/form-data">
	<table id="posting">
		<tr><td><div id="clear"></div></td></tr>
		<tr style='background-color:#f2f2f2;'>
			<th><a href="#"> 뒤로 </a>  </th>
			<th>새 스토리</th>
			<th> <input type="submit" value="등록하기" onclick="editCheck(${story_num});"> </th>
		</tr>
		<tr><td><div id="clear"></div></td></tr>
		<tr>
			<th>
				<div id="big_userprofile">
					<img src="../images/${loginUser.img}" width="80px", height="80px">
				</div>
			</th>
			<td>
				<textarea name="story_content" rows="4" cols="50">${StoryDto.content}</textarea>
			</td>
			<td>
				<div id="container_wrap">
					<div id="image_container"></div>
				</div>
			</td>
		</tr>
		<tr>
			<th class="td_caption" colspan="8">
				<input type="file" name="story_img" onchange="setThumbnail(event);">
				<input type="hidden" name="oldPicture" value="${StoryDto.story_img}">
			</th>
		</tr>
	</table>
</form>
</body>
</html>