<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">

<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="/css/topnav.css">
<link rel="stylesheet" href="/css/common.css">

<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>


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
		
	  	<c:choose>
			<c:when test="${ loginUser != null }">
				<i class="material-icons" onclick="location.href='spring.do?command=message'">send</i>
				<i class="material-icons" onclick="location.href='spring.do?command=selectPost'">add_box</i>
				<i class="material-icons" onclick="location.href='spring.do?command=explore'">explore</i>
				<i class="material-icons" onclick="location.href='spring.do?command=notification'">favorite_border</i>
				
				<img id="userIcon" src="/images/${ loginUser != null ? (loginUser.img == null || loginUser.img.equals("") ? "tmpUserIcon.png" : loginUser.img ) : "tmpUserIcon.png" }" 
				onclick="userIcon('${loginUser.userid}');">
				
			</c:when>
			
			<c:otherwise>
				<i class="material-icons" onclick="goLogin()">send</i>
				<i class="material-icons" onclick="goLogin()">add_box</i>
				<i class="material-icons" onclick="goLogin()">explore</i>
				<i class="material-icons" onclick="goLogin()">favorite_border</i>
				
			
				<img id="userIcon" src="/images/tmpUserIcon.png" 
				onclick="goLogin();">
			</c:otherwise>
		</c:choose> 
		 
		
		 
	</div>
	
<script type="text/javascript">
	function goLogin() {
		alert("로그인해주세요")
		if(confirm("로그인 할까요?")) {
			location.href="spring.do?command=loginform"
		}
	}


	function userIcon(userid) {
		location.href= "spring.do?command=userpage&userid=" + userid;
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