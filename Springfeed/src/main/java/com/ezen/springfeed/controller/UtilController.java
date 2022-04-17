package com.ezen.springfeed.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ezen.springfeed.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ezen.springfeed.service.UtilService;
import org.springframework.web.util.HtmlUtils;

@Controller
public class UtilController {

	@Autowired
	UtilService us;

	
	@RequestMapping("/report/form") 
	public String ReportForm(HttpServletRequest request, Model model, 
			@RequestParam(value="story_num", required=false) String story_num,
			@RequestParam(value="post_num", required=false) String post_num,
			@RequestParam(value="userid", required=false) String userid ){
		
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		
		String url = "";
		if(loginUser==null) {
			model.addAttribute("message", "로그인 후 이용해주세요!");
			url = "member/login";
		}
		else {
			if(userid == null || userid.equals("")) {
				url = "post/report";
				if(story_num != null) 
					model.addAttribute("story_num", story_num);
				else
					model.addAttribute("post_num", post_num);
			} else {
				model.addAttribute("reported", userid);
				url = "post/reportUser";
			}
		}
		return url;
	}
	
	@RequestMapping("/report")
	public String Report(HttpServletRequest request, Model model, 
			@RequestParam("reportReson") String reason,
			@RequestParam(value="story_num", required=false) String reportedStory_num,
			@RequestParam(value="post", required=false) String reportedPost_num,
			@RequestParam(value="userid", required=false) String reportedUserid ) {
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		
		String url = "";
		if(loginUser==null) {
			model.addAttribute("message", "로그인 후 이용해주세요!");
			url = "member/login";
		}
		else {
			String reportReason = "";
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("reporter", loginUser.get("USERID"));
			
			if(reportedUserid == null || reportedUserid.equals("")) {
				if(reason.equals("1")) reportReason = "스팸";
				else if(reason.equals("2")) reportReason = "민감한 콘텐츠";
				else if(reason.equals("3")) reportReason = "폭력적인 콘텐츠";
				else if(reason.equals("4")) reportReason = "허위 사실 유포";
				else if(reason.equals("5")) reportReason = "자살 또는 자해 요소";
				else if(reason.equals("6")) reportReason = "불법 또는 규제 상품 판매";
				else if(reason.equals("7")) reportReason = "섭식 장애";
				else if(reason.equals("8")) reportReason = "혐오 발언 또는 상징";
				else if(reason.equals("9")) reportReason = "지식재산권 침해";
				
				if(reportedStory_num != null) {
					//스토리 신고

					paramMap.put("reportType", "story");
					paramMap.put("reason", reportReason);
					paramMap.put("story_num", reportedStory_num);
					
					System.out.println(loginUser.get("USERID"));
					System.out.println(reportReason);
					System.out.println(reportedStory_num);

					us.addStoryReport(paramMap);
					
				} else {
					//포스트 신고
					paramMap.put("reportType", "post");
					paramMap.put("reason", reportReason);
					paramMap.put("post_num", reportedPost_num);
					
					us.addPostReport(paramMap);
				}
			} else {
				//유저 신고
				if(reason.equals("1")) reportReason = "적합하지 않은 콘텐츠 게시";
				else if(reason.equals("2")) reportReason = "타인을 사칭하는 계정";
				else if(reason.equals("3")) reportReason = "만 14세 미만 계정";
				
				paramMap.put("reported", reportedUserid);
				paramMap.put("reason", reportReason);
				paramMap.put("reportType", "user");
				
				us.addUserReport(paramMap);
				
			}
			
		}
		
	    return "post/reportDone";
	}
	
	
	
	
	
	
	
	
	
	
	
	

	// Message
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public MessageDto greeting(MessageDto message) throws Exception {
		Thread.sleep(1000);
		MessageDto mdto = new MessageDto();
		mdto.setContent("안녕 나 메세지야 " + HtmlUtils.htmlEscape(message.getMessageFrom()));
		mdto.setMessageFrom("jinkpark");
		mdto.setMessageTo("jinkpark");
		return mdto;
	}
}