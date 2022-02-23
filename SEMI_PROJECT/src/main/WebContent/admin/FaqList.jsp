<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/faqList.css">
</head>
<body>

<div>
	<form id="content" action="post" name="adminLogin">
	<img src="images/Logo.png" width="80" id="Logo">
	<h1>안녕하세요! 무슨 문제가 생기셨나요?</h1>
	<div id="search"><input type="text" name="search" 
	placeholder=" 문제를 설명해주세요"><img src="images/search.png" width="25" ></div>
	</form>
</div>
<br><br><br><br>

<form>
  	<h3 id=faq_subject>도움말 주제 찾아보기</h3>
    <div id="menu">
        <div id="menu1" class="menuall">&nbsp; 본인 계정 및 설정 관리
        	<img src="images/down.png" width="27" class="down">
        	</div>
	        <div id="sub1"  class="suball">
		        <a href="">회원가입은 어떻게 하나요?</a><br>
		        <a href="">계정을 비활성화 하고 싶어요</a><br>
		        <a href="">탈퇴하고 싶어요</a>
	        </div>
        <div id="menu2" class="menuall">&nbsp; 게시물과 댓글 관련
        <img src="images/down.png" width="27" class="down"></div>
	        <div id="sub2"  class="suball">
				<a href="">인기글에 뜨는 기준이 어떻게 되나요</a>
	 			 <a href=""></a></div>
        <div id="menu3" class="menuall">&nbsp; 개인정보 정책
        <img src="images/down.png" width="27" class="down"></div>
	        <div id="sub3"  class="suball">
	     		 <a href="">핸드폰 번호를 꼭 입력해야 하나요</a><br>
	       		 <a href="">비밀번호가 자꾸 틀렸다고 나옵니다</a></div>
        <div id="menu4" class="menuall">&nbsp; 사용자 신고 관련
        <img src="images/down.png" width="27" class="down" ></div>
	        <div id="sub4"  class="suball">
	       		 <a href="">이상한 게시물,사람을 신고하고 싶어요</a>
	        </div>
    </div>
</form><br><br><br>

<div id="qna_box">
 <form>
  <h1>도움이 더 필요하신가요?</h1>
	<p style="text-align:center">1:1 문의를 남겨주시면 자세하게 답변드리도록 하겠습니다<br><br>
	<a href="spring.do?command=qnaWriteForm">
	Q&amp;A 문의작성<img src="images/Qna.png" width="50"></a>
 </form>
</div><br>

<%@ include file="footer.jsp"%>
</body>
</html>
  
  <!-- <article>
    <h3 id=subject>도움말 주제 찾아보기</h3>
    <section class="selected">
    	<role="helper" jslog="track:click, impression" id="parent">
    	본인 계정 및 설정 관리	
    	<div class="overflow">
    		<div class="children" role="list" aria-hidden="false" style="margin-top:0 px;">
    			<div class="child" role="listitem">본인 및 계정관리  box
    				<a class="link" href="" jslog="track:click, impression">회원가입은 어떻게 하나요?</a></div>
    			<div class="child" role="listitem"></div>
    				<a class="link" href="" jslog="track:click, impression"></a> 
    			<div class="child" role="listitem"></div>
	    			<a class="link" href="" jslog="track:click, impression"></a> 
    			<div class="child" role="listitem"></div>
    				<a class="link" href="" jslog="track:click, impression"></a> 
    			<div class="child" role="listitem"></div>
    				<a class="link" href="" jslog="track:click, impression"></a> 
    			<div class="child" role="listitem"></div>
    		</div>
    	</div>
    </section>
    </role>
   </article>
 -->

