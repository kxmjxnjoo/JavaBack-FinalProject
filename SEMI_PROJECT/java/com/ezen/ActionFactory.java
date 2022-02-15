package com.ezen;

import com.ezen.action.Action;
import com.ezen.action.AddReplyAction;
import com.ezen.action.FollowAction;
import com.ezen.action.LikePostAction;
import com.ezen.action.MainAction;
import com.ezen.action.NotificationAction;
import com.ezen.action.UnfollowAction;
import com.ezen.action.UnlikePostAction;
import com.ezen.action.UserPageAction;

public class ActionFactory {
	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; }
	
	public Action getAction(String command) {
		Action ac = null;
		
		if(command.equals("main"))				ac = new MainAction();
		else if(command.equals("userpage"))		ac = new UserPageAction();
		else if(command.equals("follow"))		ac = new FollowAction();
		else if(command.equals("unfollow"))		ac = new UnfollowAction();
		
		else if(command.equals("likepost"))		ac = new LikePostAction();
		else if(command.equals("unlikepost"))	ac = new UnlikePostAction();
		
		else if(command.equals("addreply"))		ac = new AddReplyAction();
		
		else if(command.equals("notification"))	ac = new NotificationAction();
		
		return ac;
	}
}
