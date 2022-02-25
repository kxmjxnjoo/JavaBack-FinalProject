<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/admin/css/admin.css">
<meta charset="UTF-8">
<nav id="sub_menu">
<!-- <h1>Admin Setting</h1>  -->
<ul>
	<li><img src ="admin/images/Logo.png" width="63px" class="img" onclick="location.href='spring.do?command=loginform'"></li>
	<li><a href="spring.do?command=memberList">회원리스트</a></li>
	<li><a href="spring.do?command=adminFaqList">FAQ리스트</a></li>
	<li><a href="spring.do?command=qnaList">Q&amp;A리스트</a></li>
	<li><a href="spring.do?command=ReportList&reportOrder=0">신고리스트</a></li>
</ul>
</nav>

<script type="text/javascript">
	if("${ message }" != "") {
		alert("${ message }")
		${ message = "" }
	}
</script>
