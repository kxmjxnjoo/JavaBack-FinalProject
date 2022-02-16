package com.dto;

public class FollowViewDto {
	private String following;
	private String follower;
	
	private String followingImg;
	private String followingName;
	
	
	public String getFollowing() {
		return following;
	}
	public void setFollowing(String following) {
		this.following = following;
	}
	public String getFollower() {
		return follower;
	}
	public void setFollower(String follower) {
		this.follower = follower;
	}
	public String getFollowingImg() {
		return followingImg;
	}
	public void setFollowingImg(String followingImg) {
		this.followingImg = followingImg;
	}
	public String getFollowingName() {
		return followingName;
	}
	public void setFollowingName(String followingName) {
		this.followingName = followingName;
	}
}
