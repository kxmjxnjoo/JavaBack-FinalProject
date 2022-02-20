<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<nav id="sub_menu">
<!-- <h1>Admin Setting</h1>  -->
<ul>
	<li><img src ="images/Logo.png" width="60px" class="img"></li>
	<li><a href="spring.do?command=memberList">회원리스트</a></li>
	<li><a href="spring.do?command=adminFaqList">FAQ리스트</a></li>
	<li><a href="spring.do?command=qnaList">Q&amp;A리스트</a></li>
	<li><a href="spring.do?command=ReportList">신고리스트</a></li>
</ul>
</nav>