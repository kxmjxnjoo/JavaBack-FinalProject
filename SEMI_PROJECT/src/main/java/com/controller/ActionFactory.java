package com.controller;


import com.controller.action.Action;
import com.controller.action.AddBookmarkAction;
import com.controller.action.AddMessageAction;
import com.controller.action.AddReplyAction;
import com.controller.action.CheckLikeAction;
import com.controller.action.CheckReplyLikeAction;
import com.controller.action.CheckStoryLikeAction;
import com.controller.action.DeleteBookmarkAction;
import com.controller.action.DeletePostAction;
import com.controller.action.DeleteReplyAction;
import com.controller.action.DeleteStoryAction;
import com.controller.action.EditPostAction;
import com.controller.action.EditPostFormAction;
import com.controller.action.EditProfileAction;
import com.controller.action.EditProfileFormAction;
import com.controller.action.EditStoryAction;
import com.controller.action.EditStoryFormAction;
import com.controller.action.ExploreAction;
import com.controller.action.FindIdAction;
import com.controller.action.FindIdpwdForm;
import com.controller.action.FindPwdAction;
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
import com.controller.action.StoryCheckAction;
import com.controller.action.StoryDetailAction;
import com.controller.action.StoryUploadAction;
import com.controller.action.StoryUploadFormAction;
import com.controller.action.UnfollowAction;
import com.controller.action.UnlikePostAction;
import com.controller.action.UserFnqAction;
import com.controller.action.UserPageAction;
import com.controller.action.UserQnaAction;
import com.controller.action.WithdrawAction;
import com.controller.adminAction.AddFaqAction;
import com.controller.adminAction.AddFaqFormAction;
import com.controller.adminAction.AdminAction;
import com.controller.adminAction.AdminFaqListAction;
import com.controller.adminAction.AdminLoginAction;
import com.controller.adminAction.DeleteFaqAction;
import com.controller.adminAction.EditFaqAction;
import com.controller.adminAction.EditFaqFormAction;
import com.controller.adminAction.HandleReportAction;
import com.controller.adminAction.MemberListAction;
import com.controller.adminAction.MemberSearchAction;
import com.controller.adminAction.PostReportCheckAction;
import com.controller.adminAction.QnaListAction;
import com.controller.adminAction.QnaViewAction;
import com.controller.adminAction.QnaWriteFormAction;
import com.controller.adminAction.ReportListAction;
import com.controller.adminAction.ReportOrderAction;
import com.controller.adminAction.StoryReportCheckAction;
import com.controller.adminAction.UploadQnaAction;
import com.controller.adminAction.UserReportCheckAction;
import com.controller.adminAction.commentQnaAction;

public class ActionFactory {

	private ActionFactory() {}
	private static ActionFactory itc = new ActionFactory();
	public static ActionFactory getInstance() { return itc; } 
	
	public Action getAction(String command) {
		
	
		Action ac = null;
		System.out.println(command);
	// USER
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
		
		else if(command.equals("storyCheck")) 			ac = new StoryCheckAction();
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
		
		// Related to message
		else if(command.equals("message"))				ac = new MessageAction();
		else if(command.equals("sendmessage"))			ac = new AddMessageAction();
		
		else if(command.equals("search"))				ac = new SearchAction();
		
		else if(command.equals("explore"))				ac = new ExploreAction();
		// Related to member
		else if(command.equals("loginform"))			ac = new LoginFormAction();
		else if(command.equals("login"))				ac = new LoginAction();
		else if(command.equals("findidpwdform"))		ac = new FindIdpwdForm();
		else if(command.equals("findid"))				ac = new FindIdAction();
		else if(command.equals("findpwd"))				ac = new FindPwdAction();
		else if(command.equals("logout"))				ac = new LogoutAction();
		else if(command.equals("joinform"))				ac = new JoinFormAction();
		else if(command.equals("join"))					ac = new JoinAction();
		else if(command.equals("editprofileform"))		ac = new EditProfileFormAction();
		else if(command.equals("editprofile"))			ac = new EditProfileAction();
		else if(command.equals("withdraw"))				ac = new WithdrawAction();
		// Related to bookmark
		else if(command.equals("addbookmark"))			ac = new AddBookmarkAction();
		else if(command.equals("deletebookmark"))		ac = new DeleteBookmarkAction();
		// Related to useradmin
		else if(command.equals("userqna"))				ac = new UserQnaAction();
		else if(command.equals("userfnq"))				ac = new UserFnqAction();
		

	// ADMIN
		// Related to login
		else if( command.equals("admin")) 				ac = new AdminAction();
		else if( command.equals("adminLogin")) 			ac = new AdminLoginAction();
		//else if( command.equals("adminMain")) ac = new AdminMainAction(); //jsp를 생각 못함..............
		
		// Related to member list
		else if( command.equals("memberList")) 			ac = new MemberListAction();
		else if( command.equals("memberSearch")) 		ac = new MemberSearchAction();
		
		// Related to faq
		else if( command.equals("adminFaqList")) 		ac = new AdminFaqListAction();
		
		else if(command.equals("addfaqform"))			ac = new AddFaqFormAction();
		else if(command.equals("addfaq"))				ac = new AddFaqAction();
		else if(command.equals("editfaqform"))			ac = new EditFaqFormAction();
		else if(command.equals("editfaq"))				ac = new EditFaqAction();
		else if(command.equals("deletefaq"))			ac = new DeleteFaqAction();
		
		//else if( command.equals("adminUpdateFaq")) ac = new AdminUpdateFaqAction();
		//else if( command.equals("adminDeleteFaq")) ac = new AdminDeleteFaqAction();
		
		// Related to Qna
		else if( command.equals("qnaWriteForm")) 		ac = new QnaWriteFormAction();
		else if( command.equals("uploadQna")) 			ac = new UploadQnaAction();
		//else if( command.equals("updateQna")) ac = new UpdateQnaAction();
		//else if( command.equals("deleteQna")) ac = new DeleteQnaAction();
		else if( command.equals("qnaList")) 			ac = new QnaListAction();
		else if( command.equals("qnaView")) 			ac = new QnaViewAction();
		else if( command.equals("commentQna"))			ac = new commentQnaAction();
		
		// Related to report
		else if( command.equals("ReportList")) ac = new ReportListAction();
		else if( command.equals("reportOrder")) ac = new ReportOrderAction();
		else if( command.equals("postReportCheck")) ac = new PostReportCheckAction();
		else if( command.equals("storyReportCheck")) ac = new StoryReportCheckAction();
		else if( command.equals("userReportCheck")) ac = new UserReportCheckAction();
		else if( command.equals("handleReport")) ac = new HandleReportAction();
		//else if( command.equals("reportUser")) ac = new ReportUserAction();
		//else if( command.equals("reportUserCheck")) ac = new RedportUserCheckAction();
		
		return ac;
	}
}
