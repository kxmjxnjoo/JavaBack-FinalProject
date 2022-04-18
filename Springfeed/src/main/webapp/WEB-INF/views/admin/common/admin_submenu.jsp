<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/admin/admin.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

<meta charset="UTF-8">
<nav id="sub_menu">
<!-- <h1>Admin Setting</h1>  -->
<!-- <div class="row">
	<div class="col-sm-2">
		<a href="/admin"><img src ="/images/Logo.png" width="63px" class="img"></a>
	</div>
	<div class="col-sm-2">
		<a href="/admin/memberList">회원리스트</a>
	</div>
	<div class="col-sm-2">
		<a href="/admin/faqList">FAQ리스트</a>
	</div>
	<div class="col-sm-2">
		<a href="/admin/qnaList">Q&amp;A리스트</a>
	</div>
	<div class="col-sm-2">
		<a href="/admin/reportList">신고리스트</a>
	</div>
</div> -->
 <ul>
	<li><a href="/admin"><img src ="/images/Logo.png" width="63px" class="img"></a></li>
	<li><a href="/admin/memberList">회원리스트</a></li>
	<li><a href="/admin/faqList">FAQ리스트</a></li>
	<li><a href="/admin/qnaList">Q&amp;A리스트</a></li>
	<li><a href="/admin/reportList">신고리스트</a></li>
</ul> 
</nav>

<script type="text/javascript">
	if("${ message }" != "") {
		alert("${ message }")
		${ message = "" }
	}
</script>
