function follow(userid) {
	if(confirm(userid + "님을 팔로우 할까요?")) {
		location.href = "spring.do?command=follow&userid=" + userid
	}
}


function unfollow(userid) {
	if(confirm(userid + "님을 언팔로우 할까요?")) {
		location.href = "spring.do?command=unfollow&userid=" + userid
	}
}