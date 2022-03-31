<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../topnav/topnav.jsp" %> 
<link rel="stylesheet" href="../resources/static/css/admin/faqDetail.css">
<script src="../resources/static/script/script.js"></script>
</head>
<body>
	<form class="table" name="frm" method="post" action="spring.do?command=qnaWrite">
<h1 id="info" style="text-align:center;">궁금하신게 있다면 질문해주세요</h1>
	<br><br>
	<table>
		<tr><th width="80">작성자</th><td>${loginUser.userid}<input type="hidden"
			name="userid" value="${loginUser.userid}"></td></tr>
		<tr><th>이메일</th><td>${loginUser.eamil}</td></tr>
		<tr><th>제목</th><td><input type="text" class="text_box" size="70" name="title"></td></tr>
		<tr><th>내용</th><td><textarea cols="70" rows="15"  class="text_box" name="content"></textarea>
			</td></tr>
	</table>
</form><br>
	<form class="btns">
	<input type="submit" class="btn" value="작성" class="submit" onClick="/admin/addQna'">
	<input type="reset" class="btn" value="취소" class="reset">
	<input type="button" class="btn" value="FAQ로 돌아가기" class="submit" onClick="/admin/faqList'">
	</form>
</body>
</html>