<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" href="/topnav/topnav.css">
<link rel="stylesheet" href="/css/common.css">

<div id="topnav">
	<div id="logo">
		<img src="/images/springLogoWhite.png">
	</div>
	
	<div id="searchBar">
		<input type="text" placeholder="Search">
		<i class="material-icons">search</i>
	</div>

	<div id="icons">
		<i class="material-icons" onclick="location.href='spring.do?command=main'">home</i>
		<i class="material-icons">send</i>
		<i class="material-icons">add_box</i>
		<i class="material-icons">explore</i>
		<i class="material-icons" onclick="location.href='spring.do?command=notification'">favorite_border</i>
		<img id="userIcon" src="<c:choose>
			<c:when test="${ loginUser.img != null }">${ loginUser.img }</c:when>
			<c:otherwise>/images/tempUserIcon.png</c:otherwise>
		</c:choose>" onclick="location.href='spring.do?command=userpage&userid=${ loginUser.userid}'">
	</div>

</div>

<script type="text/javascript">
	let logo = document.getElementById("logo")
	logo.addEventListener("click", function() {
		location.href = "spring.do?command=main"
	})
	
	if("${ message }" != "") {
		alert("${ message }")
		${ message = "" }
	}
</script>