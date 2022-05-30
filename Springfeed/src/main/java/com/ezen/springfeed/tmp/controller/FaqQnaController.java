package com.ezen.springfeed.tmp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.springfeed.tmp.dto.FaqDto;
import com.ezen.springfeed.tmp.dto.Paging;
import com.ezen.springfeed.tmp.dto.QnaDto;
import com.ezen.springfeed.tmp.service.AdminService;
import com.ezen.springfeed.tmp.service.FaqQnaService;
import com.ezen.springfeed.tmp.service.UtilService;

@RestController
@CrossOrigin(origins= "*")
public class FaqQnaController {

	@Autowired
	FaqQnaService fqs;
	
	@Autowired
	AdminService as;
	
	@Autowired
	UtilService us;
	

	//임시로 report를 이쪽으로 옮겼습니다
	@RequestMapping(value="/report", produces="application/json")
	public Map<String, Object> Report(HttpServletRequest request, 
			@RequestParam("reportReson") String reason,
			@RequestParam(value="story_num", required=false) String reportedStory_num,
			@RequestParam(value="post_num", required=false) String reportedPost_num,
			@RequestParam(value="userid", required=false) String reportedUserid ) {
		
		String message = "";
		int status = 0;
		System.out.println(reportedPost_num);
		System.out.println(reportedStory_num);
		System.out.println(reportedUserid);
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		
		String url = "";
		if(loginUser==null) {
			message = "로그인 후 이용해주세요!";
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
				
				paramMap.put("reason", reportReason);
				paramMap.put("status", status);
				
				if(reportedStory_num != null) { 	//스토리 신고
					paramMap.put("reportType", "story");
					paramMap.put("story_num", reportedStory_num);
					
					us.addStoryReport(paramMap);
				} else { 	//포스트 신고
					paramMap.put("reportType", "post");
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
				paramMap.put("status", status);
				
				us.addUserReport(paramMap);
			}
			
			status = Integer.parseInt(paramMap.get("status").toString());
			if(status == 0) message = "신고에 실패했어요. 다시 시도해주세요."; 
			//↑ 존재하지 않는 게시물, 유저인 경우도 포함 
			else message = "신고가 완료되었습니다.";
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", status);
		resultMap.put("message", message);
	    return resultMap;
	}
	
	@RequestMapping(value="/qna", produces = "application/json")
	public Map<String, Object> userQna(HttpServletRequest request) {
		
		Map<String, Object> resultMap = new HashMap<>();
		String message = "";
		int status = 0;
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
	
		
		if(loginUser==null) {
			message = "로그인 후 이용해주세요!"; //비회원 qna남기는 페이지를 따로 만들어야할지??
		}
		else {
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("userid", loginUser.get("USERID"));
			paramMap.put("ref_cursor", null);
			
			fqs.getQnaList(paramMap);

			paramMap.put("ref_cursor2", null);
			
			fqs.getAllQna(paramMap);
			
			ArrayList<HashMap<String, Object>> userQnaList 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			ArrayList<HashMap<String, Object>> AllQnaList 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor2");
			
			status = 1;
			resultMap.put("userQnaList", userQnaList);
			resultMap.put("qnaList", AllQnaList);
		}
		resultMap.put("status", status);
		resultMap.put("message", message);
		return resultMap;
	}

	
	@RequestMapping(value="/qna/add", method=RequestMethod.POST, produces = "application/json")
	public Map<String, Object> addQna(@ModelAttribute("qdto") @Valid QnaDto qnadto,
			BindingResult result, HttpServletRequest request) {
		
		Map<String, Object> resultMap = new HashMap<>();
		String message = "";
		int status = 0;
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if(loginUser==null) {
			message = "로그인 후 이용해주세요!";
		}
		else {

			System.out.println(qnadto.getQna_subject());
	    	System.out.println(qnadto.getQna_content());
	    	
	    	HashMap<String,Object> paramMap = new HashMap<>();
	    	paramMap.put("status", 0);
	    	paramMap.put("userid", loginUser.get("USERID"));
	    	paramMap.put("subject", qnadto.getQna_subject());
	    	paramMap.put("content", qnadto.getQna_content());
	    	
	    	fqs.addQna(paramMap);

	    	status = Integer.parseInt(paramMap.get("status").toString());
	    	
	    	if(status == 1) message = "QnA 등록이 완료되었어요. 빠르게 답변해드릴게요!";
	    	else message = "QnA 등록에 실패했어요:( 다시 시도해주세요.";
		}
		resultMap.put("message", message);
		resultMap.put("status", status);
		return resultMap;
	}

	@RequestMapping(value="/qna/delete", produces = "application/json")
	public Map<String, Object> deleteQna(@RequestParam("qna_num") int qna_num, HttpServletRequest request) {
		
		int status = 0;
		String message = "";
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if(loginUser==null) {
			message = "로그인 후 이용해주세요!";
		}
		else {
			HashMap<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("qnanum", qna_num);
			paramMap.put("userid", loginUser.get("USERID"));
			paramMap.put("status", status);
			fqs.deleteQna(paramMap);
			
			status = Integer.parseInt(paramMap.get("status").toString());
			
			if(status == 1) message = "QnA가 삭제되었어요.";
			else if(status == -1) message = "다른 사용자가 작성한 글은 삭제할 수 없어요:(";
			else if(status == -2) message = "존재하지 않는 QnA 입니다.";
			else message = "QnA 삭제에 실패했어요. 다시 시도해주세요.";
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("message", message);
		resultMap.put("status", status);
		return resultMap;
	}

	@RequestMapping(value="/faq", produces="application/json")
	public Map<String, Object> faqList() {
		
		ModelAndView mav = new ModelAndView();
		HashMap<String, Object> paramMap = new HashMap<>();
		paramMap.put("ref_cursor", null);
		
		fqs.faqList(paramMap);
		
		ArrayList<HashMap<String, Object>> list
        = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("faqList", list);
		return resultMap;
	}
	
	

//	@RequestMapping("/qna/view") 
//	public ModelAndView viewQna(HttpServletRequest request, RedirectAttributes rttr,
//			@RequestParam("qna_num") String qna_num) {
//		ModelAndView mav = new ModelAndView();
//		
//		HttpSession session = request.getSession();
//		HashMap<String,Object> loginUser
//			= (HashMap<String, Object>) session.getAttribute("loginUser");
//		if (loginUser == null) {
//			rttr.addFlashAttribute("message", "로그인 후 이용해주세요!");
//			mav.setViewName("redirect:/login/form");
//		} else {
//	    	HashMap<String, Object> paramMap = new HashMap<>();
//	    	paramMap.put("qna_num", qna_num);
//	    	paramMap.put("ref_cursor", null);
//	    	
//	    	fqs.getQna(paramMap);
//	    	
//	    	ArrayList<HashMap<String,Object>> list 
//	    		= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
//	    	
//	    	if(list.size() == 0) {
//	    		rttr.addFlashAttribute("message", "존재하지 않는 QNA 게시물 입니다.");
//	    		mav.setViewName("redirect:/admin/qnaList");
//	    	} else {
//	    		HashMap<String,Object> resultMap = list.get(0);
//	    		
//	    		mav.addObject("qna", resultMap);
//	    		mav.setViewName("userFaqQna/qnaView");
//	    	}
//	    }
//		return mav;
//	}
//	
//	
//	@RequestMapping("/qna/edit")
//	public ModelAndView editQna(HttpServletRequest request, Model model) {
//		ModelAndView mav = new ModelAndView();
//		HttpSession session = request.getSession();
//		HashMap<String,Object> loginUser
//			= (HashMap<String, Object>) session.getAttribute("loginUser");
//		HashMap<String,Object> paramMap = new HashMap();
//		if (loginUser == null) mav.setViewName("member/login");
//	    else {
//	    	fqs.qnaEdit(paramMap);
//	    	mav.setViewName("redirect:/qna/edit");
//	    }
//		return mav;
//	}
	
	
	
	//----------------------이하 admin 영역 ----------------------------------
	
	
	
	@RequestMapping("/admin/faqList")
	public ModelAndView adminFaqList(HttpServletRequest request, Model model,  
			@ModelAttribute("fdto") @Valid FaqDto faqdto, BindingResult result){
		ModelAndView mav = new ModelAndView();
		FaqDto fdto = new FaqDto();
		int faq_num = fdto.getFaq_num();
		HttpSession session = request.getSession();
		HashMap<String,Object> loginAdmin
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		System.out.println("faqList Login");
	    if (loginAdmin == null) {
	    	mav.setViewName("admin/admingLogin");
	    	return mav;
	    } else {
	    	int page = 1;
			if(request.getParameter("first")!=null) {
				session.removeAttribute("page");
			}
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			} else {
				session.removeAttribute("page");
			}
			Paging paging = new Paging();
			paging.setPage(page);
			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("faq_num", faq_num);
			paramMap.put("cnt", 0);
			System.out.println(paramMap);
			as.getAllCount_f(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
			paging.setTotalCount(cnt);
			paging.paging();
			
			System.out.println(paging.getStartNum());
			System.out.println(paging.getEndNum());
			paramMap.put("startNum", paging.getStartNum());
			paramMap.put("endNum", paging.getEndNum());
			
			paramMap.put("ref_cursor", null);
			fqs.adminFaqList(paramMap);
			
			ArrayList<HashMap<String,Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
	    	fqs.adminFaqList(paramMap);
	    	mav.addObject("fdto", list);
	    	System.out.println(list);
	    	mav.setViewName("admin/faq/adminFaqList");
	    }
		return mav;
	}
	

	@RequestMapping("/admin/qnaList")
	public ModelAndView qnaList(HttpServletRequest request, Model model,  
			@ModelAttribute("qdto") @Valid QnaDto qnadto, BindingResult result) {
		System.out.println(qnadto);
		ModelAndView mav = new ModelAndView();
		QnaDto qdto = new QnaDto();
		int qna_num = qdto.getQna_num();
		HttpSession session = request.getSession();
		HashMap<String, Object> loginAdmin 
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		if(session.getAttribute("loginAdmin") == null) {
			mav.setViewName("admin/adminLogin");
		} else {
			int page = 1;
			if(request.getParameter("first")!=null) {
				session.removeAttribute("page");
			}
			if(request.getParameter("page")!=null) {
				page = Integer.parseInt(request.getParameter("page"));
				session.setAttribute("page", page);
			} else if(session.getAttribute("page") != null) {
				page = (Integer)session.getAttribute("page");
			} else {
				session.removeAttribute("page");
			}
			Paging paging = new Paging();
			paging.setPage(page);
			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("qna_num", qna_num);
			paramMap.put("cnt", 0);
			System.out.println(paramMap);
			as.getAllCount_q(paramMap);
			int cnt = Integer.parseInt(paramMap.get("cnt").toString());
			paging.setTotalCount(cnt);
			paging.paging();
			
			System.out.println(paging.getStartNum());
			System.out.println(paging.getEndNum());
			paramMap.put("startNum", paging.getStartNum());
			paramMap.put("endNum", paging.getEndNum());
			paramMap.put("ref_cursor", null);
			fqs.adminQnaList(paramMap);
			
			ArrayList<HashMap<String,Object>> list
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			mav.addObject("qdto", list);
			mav.addObject("paging", paging);
			mav.setViewName("admin/adminQna/adminQnaList");
		}
		return mav;
	}
	

	@RequestMapping("/faq/add/form ")
	public ModelAndView addFaq(HttpServletRequest request, Model model) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String,Object> loginAdmin
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		if (loginAdmin == null) {
			mav.setViewName("admin/admingLogin");
		} else {
			mav.setViewName("admin/faq/addFaq");
		}
		return mav;
	}
	
	@RequestMapping(value="/faq/add",  method=RequestMethod.POST)
	public ModelAndView addFaqForm( @ModelAttribute("fdto") @Valid FaqDto faqdto,
			BindingResult result, HttpServletRequest request) {
		
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String,Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		 if (loginUser == null) mav.setViewName("admin/admingLogin"); 
		 else {
	    	System.out.println("A");
	    	if(result.getFieldError("faq_subject") != null) {
	    		mav.addObject("message", "제목을 입력하세요");
	    		mav.setViewName("faq/addFaq");
	    		return mav;
	    	} else if(result.getFieldError("faq_content") != null) {
    		System.out.println("B");
	    		mav.addObject("message", "내용을 입력하세요");
	    		mav.setViewName("faq/addFaq");
	    		return mav;
	    	}
	    	
	    	HashMap<String,Object> paramMap = new HashMap<>();
	    	paramMap.put("id", loginUser.get("USERID"));
	    	paramMap.put("faq_subject", faqdto.getFaq_subject());
	    	paramMap.put("faq_content", faqdto.getFaq_content());
	    	fqs.addFaq(paramMap);
	    	mav.setViewName("redirect:/admin/faqList");
	    }
		return mav;
	}
	
	
	
	@RequestMapping(value="/faq/edit", method=RequestMethod.POST)
	public ModelAndView faqEdit(HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		HashMap<String,Object> loginAdmin
			= (HashMap<String, Object>) session.getAttribute("loginAdmin");
		HashMap<String,Object> paramMap = new HashMap();
		if (loginAdmin == null) mav.setViewName("admin/admingLogin");
	    else {
	    	mav.setViewName("admin/faq/editFaq");
	    	fqs.faqEdit(paramMap);
	    }
	
		ArrayList<HashMap<String,Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
		
    	fqs.adminFaqList(paramMap);
    	mav.addObject("fdto", list);
    	System.out.println(list);
    	mav.setViewName("admin/faq/adminFaqList");
    	return mav;
	}
	
	
	
	@RequestMapping("/faq/delete")
	public String deleteFaq(	@RequestParam("faqnum") FaqDto faq_num) {
			
		HashMap<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("faqnum", faq_num);
		fqs.deleteFaq(paramMap);
			
		return "redirect:/admin/faqList";
	}
	
	
	@RequestMapping("/qna/detail")
	public String qnaView(HttpServletRequest request, Model model, 
			@ModelAttribute("qdto") @Valid QnaDto qnadto, BindingResult result) {
		
		ModelAndView mav = new ModelAndView();
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser
			= (HashMap<String, Object>) session.getAttribute("loginUser");
		if(loginUser==null) {
			model.addAttribute("message", "로그인 후 이용해주세요!");
			 mav.setViewName("member/login");
		}	else {
			
			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("qna_num", qnadto.getQna_num());
			paramMap.put("ref_cursor1", null);
			paramMap.put("ref_cursor2", null);
			
			fqs.qnaDetail(paramMap);
			
			ArrayList<HashMap<String,Object>> list1
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");

			ArrayList<HashMap<String,Object>> list2
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor1");
			
			mav.addObject("qdto", list1);
			mav.addObject("reply", list2);
			mav.setViewName("redirect:/admin/qnaList");
			
		}
		return "mav";
	}
	
	
	
	@RequestMapping("/qna/reply")
	public String qnaReply(
			@RequestParam("qna_num") int qna_num,
			@RequestParam("qna_content") String qna_content,
			@RequestParam("qna_reply") String qna_reply, HttpServletRequest request) {
		
		//댓글 추가
		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("qna_num", qna_num);
		paramMap.put("qna_content", qna_content);
		paramMap.put("qna_refply", qna_reply);
		
		fqs.insertReply(paramMap);
		
		return "redirect:/admin/qnaList" + qna_num;
	}
	
	
}