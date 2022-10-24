package com.dto;

import java.sql.Date;

public class PostViewDto {
	private int postNum;
	private String postImg;
	private String content;
	private String address;
	private String userid;
	private String userImg;
	private int likes;
	private Date createDate;
	private int isLikedByUser;
	private int likeCount;
	private int replyCount;
	
	private int isSaved;
	
	public int getIsSaved() {
		return isSaved;
	}
	public void setIsSaved(int isSaved) {
		this.isSaved = isSaved;
	}

	
	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getIsLikedByUser() {
		return isLikedByUser;
	}

	public void setIsLikedByUser(int isLikedByUser) {
		this.isLikedByUser = isLikedByUser;
	}

	public int getPostNum() {
		return postNum;
	}

	public void setPostNum(int postNum) {
		this.postNum = postNum;
	}

	public String getPostImg() {
		return postImg;
	}

	public void setPostImg(String img) {
		this.postImg = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserImg() {
		return userImg;
	}

	public void setUserImg(String userImg) {
		this.userImg = userImg;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}	
}
