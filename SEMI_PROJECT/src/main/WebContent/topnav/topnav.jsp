<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet" href="/topnav/topnav.css">
<link rel="stylesheet" href="/css/common.css">

<div id="topnav">
	<div id="logo" onclick="location.href='spring.do?command=main'">
		<img src="/images/springLogoWhite.png">
	</div>
	
	<form action="spring.do">
		<div id="searchBar">
			<input type="hidden" name="command" value="search">
			<input type="text" placeholder="검색" name="searchfor">
			<i class="material-icons" onclick="goSearchIcon();">search</i>
			
			<input type="submit" onclick="goSearch();">
		</div>
	</form>

	<div id="icons">
		<i class="material-icons" onclick="location.href='spring.do?command=main'">home</i>
		<i class="material-icons" onclick="location.href='spring.do?command=message'">send</i>
		<i class="material-icons" onclick="location.href='spring.do?command=selectPost'">add_box</i>
		<i class="material-icons" onclick="location.href='spring.do?command=explore'">explore</i>
		<i class="material-icons" onclick="location.href='spring.do?command=notification'">favorite_border</i>
		
		<img id="userIcon" src="/images/${ loginUser != null ? loginUser.img : "tmpUserIcon.png" }" onclick="userIcon()">
		
	</div>
	
	<script type="text/javascript">
		function userIcon() {
			if("${ loginUser == null }") {
				if(confirm("로그인 하시겠어요?")) {
					location.href = "spring.do?command=loginform"
				}
			} else {
				location.href= "spring.do?command=userpage&userid=${ loginUser.userid}"
			}
		}
	</script>	

</div>

<script type="text/javascript">

	if("${ message }" != "") {
		alert("${ message }")
		${ message = "" }
	}
	
	function goSearch() {
		let searchBar = document.querySelector("#searchBar input[type=text]")
		let submitBtn = document.querySelector("#searchBar input[type=submit]")
		
		
		if(searchBar.value == "") {
			if(confirm("검색 내용을 입력하지 않으시면 모든 유저를 검색해요. 괜찮으신가요?")) {
				submitBtn.preventDefault()
				
				location.href='spring.do?command=search'
				return true
			}
			return false
		} else {
			submitBtn.preventDefault()
			
			location.href = 'spring.do?command=search&searchfor=' + searchBar.value
		}
	}	
	
	function goSearchIcon() {
		let searchBar = document.querySelector("#searchBar input[type=text]")
		
		
		if(searchBar.value == "") {
			if(confirm("검색 내용을 입력하지 않으시면 모든 유저를 검색해요. 괜찮으신가요?")) {
				
				location.href='spring.do?command=search'
			}
		} else {			
			location.href = 'spring.do?command=search&searchfor=' + searchBar.value
		}
	}
</script>