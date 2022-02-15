package com.controller;


import com.controller.action.Action;
import com.controller.action.AddReplyAction;
import com.controller.action.CheckLikeAction;
import com.controller.action.CheckReplyLikeAction;
import com.controller.action.CheckStoryLikeAction;
import com.controller.action.DeletePostAction;
import com.controller.action.DeleteStoryAction;
import com.controller.action.EditPostAction;
import com.controller.action.EditPostFormAction;
import com.controller.action.EditStoryAction;
import com.controller.action.EditStoryFormAction;
import com.controller.action.FollowArrowsAction;
import com.controller.action.LikePostAction;
import com.controller.action.MainAction;
import com.controller.action.MessageAction;
import com.controller.action.NotificationAction;
import com.controller.action.PostDetailAction;
import com.controller.action.PostReportAction;
import com.controller.action.PostUploadAction;
import com.controller.action.PostUploadFormAction;
import com.controller.action.ReportFormAction;
import com.controller.action.SearchAction;
import com.controller.action.StoryDetailAction;
import com.controller.action.StoryUploadAction;
import com.controller.action.StoryUploadFormAction;
import com.controller.action.UnfollowAction;
import com.controller.action.UnlikePostAction;
import com.controller.action.UserPageAction;

public class ActionFactory {

	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; } 
	
	public Action getAction(String command) {
		
	
		Action ac = null;
		System.out.println(command);
		 if(command.equals("postuploadForm")) ac = new PostUploadFormAction();
		 if(command.equals("postUpload")) ac = new PostUploadAction();
		 if(command.equals("postDetail")) ac = new PostDetailAction();
		 if(command.equals("addReply")) ac = new AddReplyAction();
		 if(command.equals("checkLike")) ac = new CheckLikeAction();
		 if(command.equals("checkReplyLike")) ac = new CheckReplyLikeAction();
		 if(command.equals("editPost")) ac = new EditPostAction();
		 if(command.equals("editPostForm")) ac = new EditPostFormAction();
		 if(command.equals("deletePost")) ac = new DeletePostAction();
		 if(command.equals("reportForm")) ac = new ReportFormAction();
		 if(command.equals("postReport")) ac = new PostReportAction();
		 if(command.equals("storyUploadForm")) ac = new StoryUploadFormAction();
		 if(command.equals("storyUpload")) ac = new StoryUploadAction();
		 if(command.equals("storyDetail")) ac = new StoryDetailAction();
		 if(command.equals("checkStoryLike")) ac = new CheckStoryLikeAction();
		 if(command.equals("followArrows")) ac = new FollowArrowsAction();
		 if(command.equals("deleteStory")) ac = new DeleteStoryAction();
		 if(command.equals("editStoryForm")) ac = new EditStoryFormAction();
		 if(command.equals("editStory")) ac = new EditStoryAction();
		 
		if(command.equals("main"))				ac = new MainAction();
		else if(command.equals("userpage"))		ac = new UserPageAction();
		//else if(command.equals("follow"))		ac = new FollowAction();
		else if(command.equals("unfollow"))		ac = new UnfollowAction();
		
		else if(command.equals("likepost"))		ac = new LikePostAction();
		else if(command.equals("unlikepost"))	ac = new UnlikePostAction();
		
		else if(command.equals("addreply"))		ac = new AddReplyAction();
		
		else if(command.equals("notification"))	ac = new NotificationAction();
		
		else if(command.equals("message"))		ac = new MessageAction();
		
		else if(command.equals("search"))		ac = new SearchAction();
		 
		 return ac;
	}
}
