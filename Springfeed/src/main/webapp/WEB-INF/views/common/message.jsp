<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta charset="UTF-8">


<!-- topnav가 없는 곳에서만 이용해주세요 -->


<style type="text/css">
	#message{
		width:100%; height: 40px;
		background: black; opacity: 0.6; color: white;
		line-height: 40px; text-align: center;
		position: relative; top: -50px; transition: 1s;
	}
</style>

<script type="text/javascript">
	function disappear() {
		document.getElementById("message").style.webkitTransform='translateY(-40px)';
	}
	
	function appear() {
		document.getElementById("message").style.webkitTransform='translateY(40px)';
	}
	
	if("${ message }" != "") {
		setTimeout(appear, 100); 
		setTimeout(disappear, 3000); 
	}
	
	
	if("${ messageConfirm }" != "") {
		let result = confirm("${ messageConfirm }")
		if(result) {
			location.href="${sendUrl}"
		}
		
	}
</script>


<div class="message" id="message">
	${ message }
</div>


