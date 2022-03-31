<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../resources/static/css/admin/admin.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<meta charset="UTF-8">
<nav id="sub_menu">
<!-- <h1>Admin Setting</h1>  -->
<ul>
	<li><img src ="../resources/static/images/Logo.png" width="63px" class="img" onclick="/admin'"></li>
	<li><a href="/admin/memberList">회원리스트</a></li>
	<li><a href="/admin/faqList">FAQ리스트</a></li>
	<li><a href="/admin/qnaList">Q&amp;A리스트</a></li>
	<li><a href="/admin/faqList">신고리스트</a></li>
</ul>
</nav>

<script type="text/javascript">
	if("${ message }" != "") {
		alert("${ message }")
		${ message = "" }
	}
</script>
