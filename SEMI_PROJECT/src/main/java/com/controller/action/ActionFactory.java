package com.controller.action;


import com.controller.adminAction.AdminAction;


import com.controller.adminAction.AdminFaqFormAction;
import com.controller.adminAction.AdminFaqListAction;
import com.controller.adminAction.AdminLoginAction;
import com.controller.adminAction.DeleteQnaAction;
import com.controller.adminAction.FaqDeleteAction;
import com.controller.adminAction.FaqDetailAction;
import com.controller.adminAction.FaqListAction;
import com.controller.adminAction.MemberListAction;
import com.controller.adminAction.MemberSearchAction;
import com.controller.adminAction.QnaListAction;
import com.controller.adminAction.QnaViewAction;
import com.controller.adminAction.QnaWriteFormAction;
import com.controller.adminAction.ReportListAction;
import com.controller.adminAction.UploadFaqAction;
import com.controller.adminAction.UploadQnaAction;
import com.controller.adminAction.commentQnaAction;

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
		
		else if(command.equals("editPostForm")) 		ac = new EditPostFormAction();
		else if(command.equals("editPost")) 			ac = new EditPostAction();
		else if(command.equals("editStoryForm")) 		ac = new EditStoryFormAction();
		else if(command.equals("editStory")) 			ac = new EditStoryAction();
		
		else if(command.equals("deletePost")) 			ac = new DeletePostAction();
		
		else if(command.equals("reportForm")) 			ac = new ReportFormAction();
		else if(command.equals("postReport")) 			ac = new PostReportAction();
		else if(command.equals("userReport")) 			ac = new UserReportAction();
		
		else if(command.equals("storyUploadForm")) 		ac = new StoryUploadFormAction();
		else if(command.equals("storyUpload")) 			ac = new StoryUploadAction();
		
		else if(command.equals("storyDetail")) 			ac = new StoryDetailAction();
		
		else if(command.equals("checkStoryLike")) 		ac = new CheckStoryLikeAction();
		else if(command.equals("followArrows")) 		ac = new FollowArrowsAction();
		
		else if(command.equals("deleteStory")) 			ac = new DeleteStoryAction();
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
		 
		
		
		
		else if( command.equals("admin")) ac = new AdminAction();
		else if( command.equals("adminLogin")) ac = new AdminLoginAction();
		
		else if( command.equals("memberList")) ac = new MemberListAction();
		else if( command.equals("memberSearch")) ac = new MemberSearchAction();
		
		else if( command.equals("faqList")) ac = new FaqListAction();
		else if( command.equals("faqDetail")) ac = new FaqDetailAction();
		else if( command.equals("uploadFaq")) ac = new UploadFaqAction();
		else if( command.equals("faqDelete")) ac = new FaqDeleteAction();
		else if( command.equals("adminFaqList")) ac = new AdminFaqListAction();
		else if( command.equals("adminFaqForm")) ac = new AdminFaqFormAction();
		
		else if( command.equals("qnaWriteForm")) ac = new QnaWriteFormAction();
		else if( command.equals("uploadQna")) ac = new UploadQnaAction();
		else if( command.equals("deleteQna")) ac = new DeleteQnaAction();
		else if( command.equals("qnaList")) ac = new QnaListAction();
		else if( command.equals("qnaView")) ac = new QnaViewAction();
		else if( command.equals("commentQna")) ac = new commentQnaAction();
		
		else if( command.equals("reportList")) ac = new ReportListAction();
		//else if( command.equals("reportUserCheck")) ac = new RedportUserCheckAction();
		
		return ac;
	}
}
