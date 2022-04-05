<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/css/spring.css" rel="stylesheet"> 
<link href="/css/story.css" rel="stylesheet"> 
<link rel="stylesheet" href="/css/admin/admin.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<script src="/js/follow.js"></script>

</head>
<body>
<div class="wrap" style="position:absolute">
	
		
		<div id=storyArea>

			<div class=story_content>
<!-- 스토리 이미지 -->
				<img id="story_img" src="../images/${StoryDto.STORY_IMG}" > 
<!-- 글 작성자 프로필 -->
				<div id="story_user" onClick="/admin/report/story?{StoryDto.USERID}'">
					<div id="userprofile" onClick="location.href='#'"> <!-- 클릭 시 유저 프로필로 이동하도록 function 추가 -->
						<img class="userImg" width=50px height=50px src="../images/${ PostDto.USER_IMG == null ? "tmpUserIcon.png" : PostDto.user_img }">
					</div> 
					<div id="userid"><b> ${StoryDto.USERID}</b></div>
					<span id="story_date"><fmt:formatDate value="${StoryDto.CREATE_DATE}"/></span>
				</div>
				
				<div id="buttons" onclick="setting();">
					<span class="material-icons" onClick="openSetting();"> more_vert </span>
				</div>
				
<!-- 작성한 글 내용 -->
			<c:choose>
				<c:when test="empty ${fontColor}">
					<div id="story_content">  <h2>  ${StoryDto.CONTENT}  </h2>  </div>
				</c:when>
				<c:otherwise>
					<div id="story_content" style="color:${StoryDto.FONTCOLOR}"> <h2>  ${StoryDto.CONTENT}  </h2>  </div> 
				</c:otherwise>
			</c:choose> 
<!-- 좋아요 버튼 -->
				<div id="reaction">
					<span class="material-icons" style="color:${likeColor}" onclick="story_like(${story_num});"> favorite_border </span>
				</div>
			</div>
			</div>  
	<div id="handleReportCheck">
		<input type="button" value="신고 처리하기" onclick="handleReport(${story_num}, ${report_num});"> 
	</div>

		</div>
		
<script type="text/javascript">
function handleReport(story_num, report_num){
	let result = confirm("신고 받은 게시물을 삭제하시겠습니까?");
	if(result) {
		location.href = "/handleReport&story_num="+story_num+"&report_num="+report_num;
	}
}
</script>
</body>
</html>