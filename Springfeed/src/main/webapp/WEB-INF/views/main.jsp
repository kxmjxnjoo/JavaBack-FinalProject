<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링피드</title>
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/mainPopup.css">
</head>
<body>
	<%@ include file="./common/topnav.jsp" %>
	
	<div id="mainContent">
		<div id="story">
		
		<c:forEach items="${ followingList }" var="follow">
			<c:if test="${ follow.following == loginUser.userid }">
				<div class="storyBubble">
					<div class="storyBubbleContent">
						<div id="myStory" onclick="location.href='spring.do?command=storyCheck&userid=${follow.following}'">
							<img src="/images/
								<c:choose>
									<c:when test="${ follow.followingImg == null }">
										tmpUserIcon.png
									</c:when>
									<c:otherwise>
										${ follow.followingImg }
									</c:otherwise>
									
	
								</c:choose>
							">
						</div>
						<h3>${ follow.following }</h3>
					</div>
				</div>
			</c:if>
		</c:forEach>
		
		
		<c:forEach items="${ followingList }" var="follow">
			<c:choose>
				<c:when test="${ follow.following == loginUser.userid }">
				
				</c:when>
				
				<c:when test="${ follow.isStoryPresent == 0 }">
					
				</c:when>
				
				<c:otherwise>
					<div class="storyBubble" onclick="location.href='spring.do?command=storyDetail&userid=${follow.following}'">
						<div class="storyBubbleContent">
							<img src="/images/
								<c:choose>
									<c:when test="${ follow.followingImg == null }">
										tmpUserIcon.png
									</c:when>
									<c:otherwise>
										${ follow.followingImg }
									</c:otherwise>
								</c:choose>
							">
							
							<h3>${ follow.following }</h3>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</c:forEach>
			
		</div>
		
		<div id="postFeed">
		<c:choose>
			<c:when test="${ postList == null || postList.size() == 0 }">
				<h1 id="noPostMessage">팔로어 분들의 포스트가 없어요!<br> ${ loginUser.userid }님이 올려보시는건 어떠세요?</h1>
				<button id="noPostButton" onclick="location.href='/post/form'">포스트 올리기</button>
				
			</c:when>
			
			<c:otherwise>
				<c:forEach var="post" items="${ postList }">
					<div class="post">
					
						<div class="postBar">
							<div class="postBarUserInfo" onclick="location.href='spring.do?command=userpage&userid=${ post.userid }'">
								<img src="../images/
									<c:choose>
										<c:when test="${ post.userImg == null }">
											tmpUserIcon.png
										</c:when>
										<c:otherwise>
											${ post.userImg }
										</c:otherwise>
									</c:choose>
								">
								<div class="postBarInfo">
									<h3>${ post.userid }</h3>
									<h4>${ post.address }</h4>
								</div>
							</div>
							
							<button onclick="openPopup(${ post.postNum }, '${ post.userid }')"><i class="material-icons">more_horiz</i></button>
						</div>
						
						<div class="postImg">
							<img src="../images/${ post.postImg }">
							<div class="postImgNavigationBar">
							
							</div>
						</div>
						
						<div class="postIcons">
							<i class="material-icons" 
							<c:choose>
								<c:when test="${ post.isLikedByUser == 1 }">
									onclick="location.href='spring.do?command=unlikepost&postnum=${ post.postNum }'"
									style="color: red;"
								</c:when>
								<c:otherwise>
									onclick="location.href='spring.do?command=likepost&postnum=${ post.postNum }'"
								</c:otherwise>
							</c:choose>
							>favorite_border</i>
							<i class="material-icons" onclick="location.href='spring.do?command=postDetail&post_num=${ post.postNum }'">chat_bubble_outline</i>
							
							<i class="material-icons" onclick="location.href='spring.do?command=message&messagewith=${ post.userid }'">send</i>
							
							<c:choose>
								<c:when test="${ post.isSaved == 1 }">
									<i class="material-icons" onclick="location.href='spring.do?command=deletebookmark&postnum=${ post.postNum }'">bookmark</i>
								</c:when>
								<c:otherwise>
									<i class="material-icons" onclick="location.href='spring.do?command=addbookmark&postnum=${ post.postNum }'">bookmark_border</i>
								</c:otherwise>
							</c:choose>
							
						</div>
						
							<div class="postInfo">
								<h3 class="postLike">${ post.likes } likes</h3>
								<div class="postContent">
									<h3><b>${ post.userid }</b></h3>
									<p>${ post.content }</p>
								</div>
								
								<div class="postComment">
								
								</div>
								
								<form>
									<input type="hidden" name="command" value="addreply">
									<input type="hidden" name="post_num" value="${ post.postNum }">
									<div class="addComment" id="comment${ post.postNum }">
										<input type="text" placeholder="댓글을 추가해 주세요..." name="reply_content">
										<input type="submit" value="추가" onclick="return addReply(${ post.postNum })">
								</div>
							</form>
							
						</div>
					</div> 		
				</c:forEach>
			</c:otherwise>
			
		</c:choose>
		</div>
		
	</div>
	
	<div id="subBar">
		<div id="myProfile" onclick="location.href='spring.do?command=userpage&userid=${ loginUser.userid }'">
			<img src="../images/${ loginUser.img == null ? "tmpUserIcon.png" : loginUser.img }">
			
			<div id="myProfileInfo">
				<h3>${ loginUser.userid }</h3>
				<h4>${ loginUser.name }</h4>
			</div>
		</div>
		
		<div id="followProfile">
			<h3 id="followMessage">내가 팔로우 한 사람</h3>
			
			<c:forEach var="follow" items="${ followingList }">
				<c:choose>
					<c:when test="${ follow.following == loginUser.userid }">
					
					</c:when>
					<c:otherwise>
						<div class="follower">
							<div class="followerInfoWrapper" onclick="location.href='spring.do?command=userpage&userid=${ follow.following }'">
								<img class="followerImg" src="../images/${ follow.followingImg == null ? "tmpUserIcon.png" : follow.followingImg }">
								
								<div class="followerInfo">
									<h3>${ follow.following }</h3>
									<h4>${ follow.followingName }</h4>
								</div>
							</div>
							
							<input type="button" value="언팔로우" onclick="unfollow('${ follow.following }')">
						</div>
					</c:otherwise>	
				</c:choose>			
			
			</c:forEach>
			
		</div>
	</div>
	
	<div id="popupWindow" style="display: none;">
		<div id="popupBox">
			<button onclick="goReport();">신고</button>
			<hr>
			<button onclick="unfollowPopup()">팔로우 취소</button>
			<hr>
			<button onclick="goPost();">게시물로 이동</button>
			<hr>
			<button onclick="openPopup()">취소</button>
		</div>
	</div>
	
	<%@ include file="./common/footer.jsp" %>
	
	<script type="text/javascript">
		let tmpPostnum = 0
		let tmpUserid = ""
	
		function openPopup(postnum, userid) {
			let popup = document.getElementById("popupWindow")
			if(popup.style.display == "none") {
				popup.style.display = ""
				tmpPostnum = postnum;
				tmpUserid = userid
			} else {
				popup.style.display = "none"
				postnum = 0
				tmpUserid = ""
			}
		}
		
		function unfollowPopup() {
			if(confirm(tmpUserid + "님을 언팔로우 할까요?")) {
				location.href = "spring.do?command=unfollow&userid=" + tmpUserid
			}
		}
		
		function addReply(postNum) {
			let reply = document.querySelector("#comment" + postNum + " input:nth-child(1)")
			
			if(reply.value == "") {
				alert("댓글에 내용이 없어요")
				return false
			} else {
				return true
			}
		}
		
		function goReport(){
			var url="spring.do?command=reportForm&post_num=" + tmpPostnum;
			var _width = '400';
			var _height = '500';
			var _left = Math.ceil((window.screen.width - _width)/2); 
			var _top = Math.ceil((window.screen.width - _height)/2); 
			var opt = "toolbar=no, menubar=no, resizable=no, fullscreen=yes, location=no, " +
				"width="+_width+", height="+_height+", left="+_left;
			window.open(url, "reportPost", opt);
		}
		
		function goPost(){
			location.href = "spring.do?command=postDetail&post_num=" + tmpPostnum;
		}
		
		
	</script>
	<script src="/js/follow.js"></script>
	
</body>
</html>