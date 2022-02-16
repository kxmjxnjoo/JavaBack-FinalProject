<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 수정</title>
<link href="css/spring.css" rel="stylesheet"> 
<script type="text/javascript" language="javascript" defer="defer">

var changePicCheck = 0;
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

function EditCheck(){
	var theForm = document.frm;
	theForm.action="spring.do?command=editPost&post_num="+${post_num};
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
			<th>새 게시물</th>
			<th> <input type="submit" value="수정하기" onclick="EditCheck();"> </th>
		</tr>
		<tr><td><div id="clear"></div></td></tr>
		<tr>
			<th>
				<div id="big_userprofile">
					<c:choose>
						<c:when test="${empty PostDto.user_img}">  <!-- 클릭 시 유저 프로필로 이동하도록 function 추가 -->
							<img src="../images/noProfile.png" width="80px", height="80px">
						</c:when>
						<c:otherwise>
							<img src="../images/${PostDto.user_img}" width="80px", height="80px">
						</c:otherwise>
					</c:choose>
				</div>
			</th>
			<td>
				<textarea name="post_content" rows="4" cols="50">${PostDto.content}</textarea>
			</td>
			<td>
				<div id="container_wrap">
					<div id="image_container"> </div>
				</div>
			</td>
		</tr>
		<tr>
			<th class="td_caption" colspan="8">
				<input type="file" name="post_img" onchange="setThumbnail(event);">
				<input type="hidden" name="oldPicture" value="${PostDto.post_img}">
				<!-- input type="file"은 default값 설정이 불가능해서 원래 이미지 미리보기를 어떻게 
				구현해야 좋을지 잘 모르겠음 -->
			</th>
		</tr>
		<tr><td><div id="clear"></div></td></tr>
		<tr>
			<th>위치</th>
			<td> <textarea name="post_address" rows="1" cols="50">${PostDto.address}</textarea> </td>
			<td colspan="3"></td>
		</tr>
		<tr>
			<th colspan="5"></th>
		</tr>
	</table>
</form>
</body>
</html>