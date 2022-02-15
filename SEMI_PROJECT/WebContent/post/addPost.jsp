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

function uploadCheck(){
	var theForm = document.frm;
	if( theForm.post_img.value=="") {
		alert('사진을 첨부해주세요'); 
	} else {
		theForm.action="spring.do?command=postUpload";
		theForm.submit();
	}
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
			<th> <input type="submit" value="등록하기" onclick="uploadCheck();"> </th>
		</tr>
		<tr><td><div id="clear"></div></td></tr>
		<tr>
			<th>
				<div id="big_userprofile">
					<!-- 유저 프로필로 수정 -->
					<img src="../images/2.jpg" width="80px", height="80px">
				</div>
			</th>
			<td>
				<textarea name="post_content" placeholder="사진을 설명해주세요" rows="4" cols="50"></textarea>
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
			</th>
		</tr>
		<tr><td><div id="clear"></div></td></tr>
		<tr>
			<th>위치</th>
			<td> <textarea name="post_address" rows="1" cols="50" placeholder="어디에 계신지 알려주세요"></textarea> </td>
			<td colspan="3"></td>
		</tr>
		<tr>
			<th colspan="5"></th>
		</tr>
	</table>
</form>
</body>
</html>