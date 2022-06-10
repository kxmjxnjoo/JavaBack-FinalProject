//package com.ezen.springfeed.tmp.controller;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.ezen.springfeed.faq.Faq;
//import com.ezen.springfeed.util.Paging;
//import com.ezen.springfeed.qna.Qna;
//import com.ezen.springfeed.admin.AdminService;
//import com.ezen.springfeed.tmp.service.FaqQnaService;
//import com.ezen.springfeed.tmp.service.UtilService;
//
//@RestController
//@CrossOrigin(origins= "*")
//public class FaqQnaController {
//
//	@Autowired
//	FaqQnaService fqs;
//
//	@Autowired
//	AdminService as;
//
//	@Autowired
//	UtilService us;
//
//
//	//임시로 report를 이쪽으로 옮겼습니다
//	@RequestMapping(value="/report", produces="application/json")
//	public Map<String, Object> Report(HttpServletRequest request,
//			@RequestParam("reportReson") String reason,
//			@RequestParam(value="story_num", required=false) String reportedStory_num,
//			@RequestParam(value="post_num", required=false) String reportedPost_num,
//			@RequestParam(value="userid", required=false) String reportedUserid ) {
//
//		String message = "";
//		int status = 0;
//		System.out.println(reportedPost_num);
//		System.out.println(reportedStory_num);
//		System.out.println(reportedUserid);
//
//		HttpSession session = request.getSession();
//		HashMap<String, Object> loginUser
//			= (HashMap<String, Object>) session.getAttribute("loginUser");
//
//		String url = "";
//		if(loginUser==null) {
//			message = "로그인 후 이용해주세요!";
//		}
//		else {
//			String reportReason = "";
//
//			HashMap<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("reporter", loginUser.get("USERID"));
//
//			if(reportedUserid == null || reportedUserid.equals("")) {
//				if(reason.equals("1")) reportReason = "스팸";
//				else if(reason.equals("2")) reportReason = "민감한 콘텐츠";
//				else if(reason.equals("3")) reportReason = "폭력적인 콘텐츠";
//				else if(reason.equals("4")) reportReason = "허위 사실 유포";
//				else if(reason.equals("5")) reportReason = "자살 또는 자해 요소";
//				else if(reason.equals("6")) reportReason = "불법 또는 규제 상품 판매";
//				else if(reason.equals("7")) reportReason = "섭식 장애";
//				else if(reason.equals("8")) reportReason = "혐오 발언 또는 상징";
//				else if(reason.equals("9")) reportReason = "지식재산권 침해";
//
//				paramMap.put("reason", reportReason);
//				paramMap.put("status", status);
//
//				if(reportedStory_num != null) { 	//스토리 신고
//					paramMap.put("reportType", "story");
//					paramMap.put("story_num", reportedStory_num);
//
//					us.addStoryReport(paramMap);
//				} else { 	//포스트 신고
//					paramMap.put("reportType", "post");
//					paramMap.put("post_num", reportedPost_num);
//
//					us.addPostReport(paramMap);
//				}
//			} else {
//				//유저 신고
//				if(reason.equals("1")) reportReason = "적합하지 않은 콘텐츠 게시";
//				else if(reason.equals("2")) reportReason = "타인을 사칭하는 계정";
//				else if(reason.equals("3")) reportReason = "만 14세 미만 계정";
//
//				paramMap.put("reported", reportedUserid);
//				paramMap.put("reason", reportReason);
//				paramMap.put("reportType", "user");
//				paramMap.put("status", status);
//
//				us.addUserReport(paramMap);
//			}
//
//			status = Integer.parseInt(paramMap.get("status").toString());
//			if(status == 0) message = "신고에 실패했어요. 다시 시도해주세요.";
//			//↑ 존재하지 않는 게시물, 유저인 경우도 포함
//			else message = "신고가 완료되었습니다.";
//		}
//		Map<String, Object> resultMap = new HashMap<>();
//		resultMap.put("status", status);
//		resultMap.put("message", message);
//	    return resultMap;
//	}
//
//	@RequestMapping("/admin/faqList")
//	public ModelAndView adminFaqList(HttpServletRequest request, Model model,
//									 @ModelAttribute("fdto") @Valid Faq faqdto, BindingResult result){
//		ModelAndView mav = new ModelAndView();
//		Faq fdto = new Faq();
//		Long faq_num = fdto.getNum();
//		HttpSession session = request.getSession();
//		HashMap<String,Object> loginAdmin
//			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
//		System.out.println("faqList Login");
//	    if (loginAdmin == null) {
//	    	mav.setViewName("admin/admingLogin");
//	    	return mav;
//	    } else {
//	    	int page = 1;
//			if(request.getParameter("first")!=null) {
//				session.removeAttribute("page");
//			}
//			if(request.getParameter("page")!=null) {
//				page = Integer.parseInt(request.getParameter("page"));
//				session.setAttribute("page", page);
//			} else if(session.getAttribute("page") != null) {
//				page = (Integer)session.getAttribute("page");
//			} else {
//				session.removeAttribute("page");
//			}
//			Paging paging = new Paging();
//			paging.setPage(page);
//			HashMap<String,Object> paramMap = new HashMap<>();
//			paramMap.put("faq_num", faq_num);
//			paramMap.put("cnt", 0);
//			System.out.println(paramMap);
//			as.getAllCount_f(paramMap);
//			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
//			paging.setTotalCount(cnt);
//			paging.paging();
//
//			System.out.println(paging.getStartNum());
//			System.out.println(paging.getEndNum());
//			paramMap.put("startNum", paging.getStartNum());
//			paramMap.put("endNum", paging.getEndNum());
//
//			paramMap.put("ref_cursor", null);
//			fqs.adminFaqList(paramMap);
//
//			ArrayList<HashMap<String,Object>> list
//				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
//
//	    	fqs.adminFaqList(paramMap);
//	    	mav.addObject("fdto", list);
//	    	System.out.println(list);
//	    	mav.setViewName("admin/faq/adminFaqList");
//	    }
//		return mav;
//	}
//
//	@RequestMapping("/admin/qnaList")
//	public ModelAndView qnaList(HttpServletRequest request, Model model,
//								@ModelAttribute("qdto") @Valid Qna qnadto, BindingResult result) {
//		System.out.println(qnadto);
//		ModelAndView mav = new ModelAndView();
//		Qna qdto = new Qna();
//		Long qna_num = qdto.getQnaNum();
//		HttpSession session = request.getSession();
//		HashMap<String, Object> loginAdmin
//			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
//		if(session.getAttribute("loginAdmin") == null) {
//			mav.setViewName("admin/adminLogin");
//		} else {
//			int page = 1;
//			if(request.getParameter("first")!=null) {
//				session.removeAttribute("page");
//			}
//			if(request.getParameter("page")!=null) {
//				page = Integer.parseInt(request.getParameter("page"));
//				session.setAttribute("page", page);
//			} else if(session.getAttribute("page") != null) {
//				page = (Integer)session.getAttribute("page");
//			} else {
//				session.removeAttribute("page");
//			}
//			Paging paging = new Paging();
//			paging.setPage(page);
//			HashMap<String,Object> paramMap = new HashMap<>();
//			paramMap.put("qna_num", qna_num);
//			paramMap.put("cnt", 0);
//			System.out.println(paramMap);
//			as.getAllCount_q(paramMap);
//			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
//			paging.setTotalCount(cnt);
//			paging.paging();
//
//			System.out.println(paging.getStartNum());
//			System.out.println(paging.getEndNum());
//			paramMap.put("startNum", paging.getStartNum());
//			paramMap.put("endNum", paging.getEndNum());
//			paramMap.put("ref_cursor", null);
//			fqs.adminQnaList(paramMap);
//
//			ArrayList<HashMap<String,Object>> list
//				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
//
//			mav.addObject("qdto", list);
//			mav.addObject("paging", paging);
//			mav.setViewName("admin/adminQna/adminQnaList");
//		}
//		return mav;
//	}
//
//	@RequestMapping("/qna/reply")
//	public String qnaReply(
//			@RequestParam("qna_num") int qna_num,
//			@RequestParam("qna_content") String qna_content,
//			@RequestParam("qna_reply") String qna_reply, HttpServletRequest request) {
//
//		//댓글 추가
//		HashMap<String, Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("qna_num", qna_num);
//		paramMap.put("qna_content", qna_content);
//		paramMap.put("qna_refply", qna_reply);
//
//		fqs.insertReply(paramMap);
//
//		return "redirect:/admin/qnaList" + qna_num;
//	}
//}