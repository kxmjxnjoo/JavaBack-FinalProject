package com.controller;


import com.controller.action.Action;
import com.controller.action.AddReplyAction;
import com.controller.action.CheckLikeAction;
import com.controller.action.CheckReplyLikeAction;
import com.controller.action.CheckStoryLikeAction;
import com.controller.action.DeletePostAction;
import com.controller.action.DeleteReplyAction;
import com.controller.action.DeleteStoryAction;
import com.controller.action.EditPostAction;
import com.controller.action.EditPostFormAction;
import com.controller.action.EditStoryAction;
import com.controller.action.EditStoryFormAction;
import com.controller.action.ExploreAction;
import com.controller.action.FollowAction;
import com.controller.action.FollowArrowsAction;
import com.controller.action.JoinAction;
import com.controller.action.JoinFormAction;
import com.controller.action.LikePostAction;
import com.controller.action.LoginAction;
import com.controller.action.LoginFormAction;
import com.controller.action.LogoutAction;
import com.controller.action.MainAction;
import com.controller.action.MessageAction;
import com.controller.action.NotificationAction;
import com.controller.action.PostDetailAction;
import com.controller.action.PostReportAction;
import com.controller.action.PostUploadAction;
import com.controller.action.PostUploadFormAction;
import com.controller.action.ReportFormAction;
import com.controller.action.SearchAction;
import com.controller.action.SelectPostAction;
import com.controller.action.StoryDetailAction;
import com.controller.action.StoryDetailfromMainAction;
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
		if(command.equals("postuploadForm")) 			ac = new PostUploadFormAction();
		else if(command.equals("postUpload")) 			ac = new PostUploadAction();
		else if(command.equals("postDetail")) 			ac = new PostDetailAction();
		else if(command.equals("addReply")) 			ac = new AddReplyAction();
		else if(command.equals("checkLike")) 			ac = new CheckLikeAction();
		else if(command.equals("checkReplyLike")) 		ac = new CheckReplyLikeAction();
		else if(command.equals("editPost")) 			ac = new EditPostAction();
		else if(command.equals("editPostForm")) 		ac = new EditPostFormAction();
		else if(command.equals("deletePost")) 			ac = new DeletePostAction();
		else if(command.equals("reportForm")) 			ac = new ReportFormAction();
		else if(command.equals("postReport")) 			ac = new PostReportAction();
		
		else if(command.equals("storyUploadForm")) 		ac = new StoryUploadFormAction();
		else if(command.equals("storyUpload")) 			ac = new StoryUploadAction();
		
		else if(command.equals("storyDetail")) 			ac = new StoryDetailAction();
		
		else if(command.equals("checkStoryLike")) 		ac = new CheckStoryLikeAction();
		else if(command.equals("followArrows")) 		ac = new FollowArrowsAction();
		else if(command.equals("deleteStory")) 			ac = new DeleteStoryAction();
		else if(command.equals("editStoryForm")) 		ac = new EditStoryFormAction();
		else if(command.equals("editStory")) 			ac = new EditStoryAction();
		else if(command.equals("deleteReply")) 			ac = new DeleteReplyAction();
		else if(command.equals("selectPost")) 			ac = new SelectPostAction();
		 
		else if(command.equals("main"))					ac = new MainAction();
		else if(command.equals("userpage"))				ac = new UserPageAction();

		else if(command.equals("follow"))				ac = new FollowAction();
		else if(command.equals("unfollow"))				ac = new UnfollowAction();
		
		else if(command.equals("likepost"))				ac = new LikePostAction();
		else if(command.equals("unlikepost"))			ac = new UnlikePostAction();
		
		else if(command.equals("addreply"))				ac = new AddReplyAction();
		
		else if(command.equals("notification"))			ac = new NotificationAction();
		
		else if(command.equals("message"))				ac = new MessageAction();
		
		else if(command.equals("search"))				ac = new SearchAction();
		
		else if(command.equals("explore"))				ac = new ExploreAction();

		// Related to member
		else if(command.equals("loginform"))			ac = new LoginFormAction();
		else if(command.equals("login"))				ac = new LoginAction();
		else if(command.equals("logout"))				ac = new LogoutAction();
		else if(command.equals("joinform"))				ac = new JoinFormAction();
		else if(command.equals("join"))					ac = new JoinAction();
		 
		return ac;
	}
}
