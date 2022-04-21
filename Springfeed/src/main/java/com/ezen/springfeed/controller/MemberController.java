package com.ezen.springfeed.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.springfeed.dto.MemberDto;
import com.ezen.springfeed.service.MemberService;
import com.ezen.springfeed.service.sendEmailService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@RestController
public class MemberController {

	@Autowired
	MemberService ms;
	
	
	
    //로그인 액션
    @RequestMapping(value="/login", method=RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public String login(@ModelAttribute("dto") @Valid MemberDto memberdto,
    		BindingResult result, HttpServletRequest request,
    		Model model) {
    	
    	String status = "";
    	System.out.println(memberdto.getUserid());
    	if(memberdto.getUserid() == null || memberdto.getUserid().equals("")) {
    		status = "emptyId"; //아이디 미입력
    	} else if(memberdto.getPassword() == null || memberdto.getPassword().equals("")) {
    		status = "emptyPassword"; //비밀번호 미입력
    	} else {
    		HashMap<String, Object> paramMap = new HashMap<>();
    		paramMap.put("userid", memberdto.getUserid());
    		paramMap.put("ref_cursor", null);
    		ms.getMember(paramMap);
    		
    		ArrayList<HashMap<String, Object>> list
    			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
    		if(list.size() == 0) {
    			status = "wrongId"; //존재하지 않는 아이디
    		} 
    		
    		else { HashMap<String, Object> mvo = list.get(0);
	    		if(mvo.get("PASSWORD") == null) {
	    			status = "PasswordError"; //DB password 에러
	    		}  else if (((String)mvo.get("USEYN")).equals("n") ) {
	    			status = "disableAccount";
	    			//비활성화 계정 confirm 필요 
	    			//model.addAttribute("messageConfirm", "비활성화된 계정입니다. 계정을 복구 할까요?");
	    		} else if (memberdto.getPassword().equals((String)mvo.get("PASSWORD"))) {
	    			status = "loginComplete"; //정상 로그인
	    			HttpSession session = request.getSession();
	    			session.setAttribute("loginUser", mvo);
	    		} else {
	    			status = "wrongPassword"; //잘못된 비밀번호  
	    		}
    		}
    	}
        return status;
    }

    //로그아웃
    @RequestMapping(value="/logout", produces = "application/json")
    public int logout(HttpServletRequest request) {
    	
    	int result = 0;
    	try {
    		HttpSession session = request.getSession();
            session.removeAttribute("loginUser");
            result = 1;
    	} catch (Exception e) {  e.printStackTrace(); }
        
        return result;
    }
    

    // 회원가입 폼
    @RequestMapping("/join/form")
    public String joinForm() {
        return "member/join";
    }
    
    
    //회원가입 시 중복, 유효성 체크 
    @RequestMapping(value="/join/idCheck", method=RequestMethod.POST)
    @ResponseBody
    public int idCheck(@ModelAttribute("dto") @Valid MemberDto memberdto,
    		BindingResult result) {

    	int cnt = 1;
    	
    	if(memberdto.getUserid() != null) {
        	String userid = memberdto.getUserid();
	    	HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("cnt", 0);
			paramMap.put("userid", userid);
			
	    	ms.idCheck(paramMap);
	    	
	    	
	    	if(result.getFieldError("userid") == null) 
	    		cnt = Integer.parseInt(paramMap.get("cnt").toString());
	    	
    	} else if (memberdto.getEmail() != null) {
    		if(result.getFieldError("email") == null) cnt = 0;
    	} else if (memberdto.getPhone() != null) {
    		if(result.getFieldError("phone") == null) cnt = 0;
    	} else if (memberdto.getName() != null) {
    		if(result.getFieldError("name") == null) cnt = 0;
    	} else if (memberdto.getPassword() != null) {
    		if(result.getFieldError("password") == null) cnt = 0;
    	}
    	
    	return cnt;
    }
    
    
    // 회원가입 액션
    @RequestMapping(value="/join", method=RequestMethod.POST, produces="application/json")
    public HashMap<String, Object> join(@ModelAttribute("dto") @Valid MemberDto memberdto,
    		BindingResult result, @RequestParam(value="reid", required=false) String reid,
    		HttpServletRequest request) {
    	
    	
    	HashMap<String, Object> resultMap = new HashMap<>();
    	String message = null;
    	int status = 0;
    	
    	if(result.getFieldError("phone")!= null) {
    		message = result.getFieldError("phone").getDefaultMessage();
         } else if(result.getFieldError("email")!= null) {
        	 message = result.getFieldError("email").getDefaultMessage();
         } else if(result.getFieldError("name")!= null) {
        	 message = result.getFieldError("name").getDefaultMessage();
         } else if(result.getFieldError("userid")!= null) {
        	 message = result.getFieldError("userid").getDefaultMessage();
         } else if(result.getFieldError("password")!= null) {
        	 message = result.getFieldError("password").getDefaultMessage();
         } else {
 			HashMap<String, Object> paramMap = new HashMap<>();
 			paramMap.put("userid",memberdto.getUserid());
 			paramMap.put("password",memberdto.getPassword());
 			paramMap.put("name",memberdto.getName());
 			paramMap.put("email",memberdto.getEmail());
 			paramMap.put("phone",memberdto.getPhone());
 			paramMap.put("status", status);
 			
 			ms.insertMember(paramMap);
 			status = Integer.parseInt((paramMap.get("status").toString()));
 			
 			if(status==1)
 				message = "회원가입이 완료되었어요. 로그인 후 이용해주세요.";
 			else
 				message = "회원가입에 오류가 발생했습니다. 다시 시도해주세요.";
 		}
    	
    	resultMap.put("message", message);
    	resultMap.put("status", status);
    	System.out.println(message + " " + status);
    	return resultMap;
    }

    // 팔로우 
//    @RequestMapping(value="/follow", produces = "application/json")
//    public int follow(HttpServletRequest request, 
//    		@RequestParam("userid") String userid, Model model, RedirectAttributes rttr) {
//    	
//    	int result = 0;
//    	
//    	HttpSession session = request.getSession();
//		HashMap<String, Object> loginUser = 
//				(HashMap<String, Object>) session.getAttribute("loginUser");
//		
//		if (loginUser == null) { 
//			result = 0;
//		} else {
//			
//			HashMap<String, Object> paramMap = new HashMap<String, Object>();
//			paramMap.put("follower", (String) loginUser.get("USERID"));
//			paramMap.put("followed", userid);
//			paramMap.put("result", 0);
//			
//			ms.insertFollow(paramMap);
//	
//			rttr.addFlashAttribute("message", userid+"님을 팔로우 했어요");
//			
//			String referer = request.getHeader("Referer");
//		    result = 1;
//		}
//    	
//        return result;
//    }
//
//    // 언팔로우
//    @RequestMapping("/unfollow")
//    public String unfollow(HttpServletRequest request, 
//    		@RequestParam("userid") String userid,
//    		Model model, RedirectAttributes rttr) {
//    	
//    	HttpSession session = request.getSession();
//		
//    	String url = "";
//		HashMap<String, Object> loginUser = 
//				(HashMap<String, Object>) session.getAttribute("loginUser");
//		
//		if (loginUser == null) { 
//			url = "member/login";
//		} else {
//			
//			String follower = (String)loginUser.get("USERID");
//			
//			System.out.println(userid);
//			System.out.println(follower);
//			
//			if(!follower.equals(userid)) {
//				HashMap<String, Object> paramMap = new HashMap<String, Object>();
//				paramMap.put("follower", follower);
//				paramMap.put("followed", userid);
//				paramMap.put("result", 0);
//				
//				ms.unfollow(paramMap);
//				
//				rttr.addFlashAttribute("message", userid + "님을 언팔로우 했습니다.");
//
//			}
//		}
//		String referer = request.getHeader("Referer");
//	    return "redirect:"+ referer;
//    }
    
		
//get Notification
    @RequestMapping(value="/user/notification",  produces="application/json")
    public ArrayList<HashMap<String, Object>> Notification(HttpServletRequest request, Model model) {
    	HttpSession session = request.getSession();
		
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null) { 
			return null; 
		} else {
			System.out.println(loginUser.get("USERID"));
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("userid", loginUser.get("USERID"));

			ms.getNotification(paramMap);
			
			ArrayList<HashMap<String, Object>> notiList 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			if (notiList.size() == 0) {
				return null; 
			} else {
				return notiList;
			} 
		}
    }
    
    @ResponseBody
    @RequestMapping("/api/noti/count")
    public int notiCount(HttpServletRequest request) {
    	
    	int notiCount = 0;
    	
    	HttpSession session = request.getSession();
    	HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
    	
    	if(loginUser == null) {
    		notiCount = 0;
    	} else {
    		HashMap<String, Object> paramMap = new HashMap<>();
    		paramMap.put("userid", loginUser.get("USERID"));
    		paramMap.put("notiCount", 0);
    		
    		ms.getNotiCount(paramMap);
    		
    		notiCount = Integer.parseInt(String.valueOf(paramMap.get("notiCount")));
    	}
    	
    	System.out.println(loginUser.get("USERID"));
    	System.out.println(notiCount);
    	
    	return notiCount;
    }
    
   
    @RequestMapping("/user/edit/form")
    public String editUserForm(Model model, HttpServletRequest request,
    		RedirectAttributes rttr) {
    	MemberDto dto = new MemberDto();
    	HttpSession session = request.getSession();
    	
    	HashMap<String, Object> loginUser 
    		= (HashMap<String, Object>) session .getAttribute("loginUser");
    	
    	if(loginUser == null) {
    		rttr.addFlashAttribute("message", "로그인 후 이용해주세요!");
    		return "redirect:/login/form"; 
    	} else {
	    	dto.setUserid((String) loginUser.get("USERID"));
	    	dto.setPassword((String) loginUser.get("PASSWORD"));
	    	dto.setName((String) loginUser.get("NAME"));
	    	dto.setEmail((String) loginUser.get("EMAIL"));
	    	dto.setPhone((String) loginUser.get("PHONE"));
	    	dto.setImg((String) loginUser.get("IMG"));
	    	dto.setIntroduce((String) loginUser.get("INTRODUCE"));
	    	dto.setUseyn((String) loginUser.get("USEYN"));
	    	System.out.println(loginUser.get("USEYN"));
	    	
	    	model.addAttribute("dto", dto);
    	}
    	return "member/editProfile";
    }
    
    @Autowired
    ServletContext context;
    
    @RequestMapping("/uploadImg")
	@ResponseBody
	public Map<String, Object> fileup(Model model, HttpServletRequest request,
			RedirectAttributes rttr) {
		
    	System.out.println("/uploadImg access");
		String savePath = context.getRealPath("/images");
		HashMap<String,Object> resultMap = new HashMap<>();
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request, savePath, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy()
			);
			resultMap.put("STATUS", 1);
			System.out.println(multi.getFilesystemName("fileName"));
			resultMap.put("FILENAME", multi.getFilesystemName("fileName"));
		}catch (IOException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
    
    @RequestMapping("/user/edit")
    public String userEdit(@ModelAttribute("dto") @Valid MemberDto memberdto,
    		BindingResult result, HttpServletRequest request, Model model,
    		@RequestParam("oldPicture") String oldPicture, RedirectAttributes rttr) {

    	HttpSession session = request.getSession();
    	HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session .getAttribute("loginUser");
    	String userid = (String)loginUser.get("USERID");
    	
    	String url = "redirect:http://localhost:3000/user/edit";
    	if(result.getFieldError("password")!= null) {
             rttr.addFlashAttribute("message", result.getFieldError("password").getDefaultMessage());
         } else if(result.getFieldError("name")!= null) {
        	 rttr.addFlashAttribute("message", result.getFieldError("name").getDefaultMessage());
          } else if(result.getFieldError("email")!= null) {
        	 rttr.addFlashAttribute("message", result.getFieldError("email").getDefaultMessage());
         }  else if(result.getFieldError("phone")!= null) {
        	 rttr.addFlashAttribute("message", result.getFieldError("phone").getDefaultMessage());
         } else {
        	 	HashMap<String, Object> resultMap = (HashMap<String, Object>) rttr.getAttribute("resultMap");
        		HashMap<String, Object> paramMap = new HashMap<>();
        		paramMap.put("USERID", userid);
     			paramMap.put("PASSWORD",memberdto.getPassword());
     			paramMap.put("NAME",memberdto.getName());
     			paramMap.put("EMAIL",memberdto.getEmail());
     			paramMap.put("PHONE",memberdto.getPhone());
     			paramMap.put("INTRODUCE",memberdto.getIntroduce());
     			if(memberdto.getImg()==null || memberdto.getImg().equals(""))
     				paramMap.put("IMG", oldPicture);
     			else
     				paramMap.put("IMG", memberdto.getImg());
     			
     			ms.userEdit(paramMap);
     			
     			session.setAttribute("loginUser", paramMap);
     			
     			url = "redirect:http://localhost:3000/user/page/"+ userid;
         }
    	return url;
    }
    
    @RequestMapping("/deleteAcount")
    public String deleteAcount(HttpServletRequest request, Model model) {
    	
    	HttpSession session = request.getSession();
    	HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(loginUser == null) {
			model.addAttribute("로그인 후 이용해주세요!");
			return "redirect:http://localhost:3000/";
		} else {
			
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("userid", loginUser.get("USERID"));
			
			session.removeAttribute("loginUser");
			ms.deleteAcount(paramMap);
			model.addAttribute("message", "계정 비활성화가 완료되었습니다. 다음에 다시 만나요!");
			
			return  "redirect:http://localhost:3000/";
		}
    }
    
    //비활성화 해제
    @RequestMapping("/user/activate")
    public String activationAccount(HttpServletRequest request, Model model,
    		@RequestParam("userid") String userid) {
        HttpSession session = request.getSession();
        
        ms.activateAccount(userid);
        model.addAttribute("message", "계정을 복구했어요! 로그인 후 이용해주세요:)");
        
        return "redirect:http://localhost:3000/";
        
    }
    
    //블락
    @RequestMapping("/block")
    public String block(HttpServletRequest request, Model model, 
    		RedirectAttributes rttr,
    		@RequestParam("userid") String userid) {
    	HttpSession session = request.getSession();
    	HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session .getAttribute("loginUser");
		
		if(loginUser == null) {
			model.addAttribute("로그인 해주세요.");
			return "member/login";
		} else {
			HashMap<String, Object> paramMap = new HashMap<>();
    		paramMap.put("userid", userid);
    		paramMap.put("ref_cursor", null);
    		
    		System.out.println();
    		ms.getMember(paramMap);
    		
			ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			if(list.size() == 0) {
				rttr.addFlashAttribute("message", "존재하지 않는 회원입니다. ");
				return "redirect:/";
			} else {
				ms.blockMember((String) loginUser.get("USERID"), userid);
				rttr.addFlashAttribute("message", userid+"님을 차단했어요.");
				rttr.addFlashAttribute("blocked", "y");
				
				String referer = request.getHeader("Referer");
			    return "redirect:http://localhost:3000/"+ referer;
			}
		}
    }
    
    
    //차단 해제
    @RequestMapping("/unblock")
    public String unblock(HttpServletRequest request, Model model, 
    		RedirectAttributes rttr, @RequestParam("userid") String userid) {
    	HttpSession session = request.getSession();
    	HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session .getAttribute("loginUser");
		
		if(loginUser == null) {
			model.addAttribute("로그인 해주세요.");
			return "member/login";
		} else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userid", loginUser.get("USERID"));
			paramMap.put("blocked", userid);
			paramMap.put("ref_cursor", null);
			
			ms.blockCheck(paramMap);
			
			ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			if(list.size() == 0) {
				rttr.addFlashAttribute("message", "차단 목록에서 " + userid +"님을 찾을 수 없습니다.");
			} else {
				ms.unblockMember((String) loginUser.get("USERID"), userid);
				rttr.addFlashAttribute("message", "차단을 해제했어요.");
			}
			String referer = request.getHeader("Referer");
		    return "redirect:http://localhost:3000/"+ referer;
		}
    }

    
    //아이디 비밀번호 찾기 폼
    @RequestMapping("/find/form")
    public String findIdPw() {
    	return "member/findIdPwd";
    }
    
    @Autowired sendEmailService sms;

    //비밀번호 찾기
    @ResponseBody 
    @RequestMapping(value="/find/pw", method=RequestMethod.POST)
    public Map<String, Object> pwdFind(@ModelAttribute("dto") @Valid MemberDto memberdto,
    		BindingResult result, HttpServletRequest request,
          Model model){
       Map<String, Object> resultMap = new HashMap<>();
       String userid = memberdto.getUserid();
       
       HashMap<String,Object> paramMap = new HashMap<>();
	   paramMap.put("userid", userid);
	   paramMap.put("phone", memberdto.getPhone());
	   paramMap.put("email", memberdto.getEmail());
	   paramMap.put("cnt", null);
	   	
	   ms.findPw(paramMap);
	   
	   int cnt = Integer.parseInt(paramMap.get("cnt").toString());
	   
	   if(cnt == 1) {
		   String tempPassword = sms.getTempPassword();
		   ms.changePw(userid, tempPassword);
		   resultMap.put("cnt", 1);
		   resultMap.put("message", userid + "님의 임시 비밀번호는 " + tempPassword + "입니다. 로그인 후 비밀번호를 변경해주세요!");
		   
	   } else {
		   resultMap.put("cnt", 0);
		   resultMap.put("message", "같은 정보의 회원을 찾을 수 없습니다.");
	   }
  
       return resultMap;
    }
   
  
    //아이디 찾기 액션
    @RequestMapping(value="/find/id", method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> findId(Model model, HttpServletRequest request, RedirectAttributes rttr,
    		@RequestParam("name") String name,
    		@RequestParam("phone") String phone) {
    	
    	HashMap<String,Object> paramMap = new HashMap<>();
    	paramMap.put("name", name);
    	paramMap.put("phone", phone);
    	paramMap.put("userid", null);
    	
    	ms.findId(paramMap);
    	
    	String userid = (String) paramMap.get("userid");
    	
    	HashMap<String, Object> result = new HashMap<>();
    	
    	if(userid == null || userid.equals("")) {
    		result.put("cnt", 0);
    		result.put("message", "같은 정보의 회원을 찾을 수 없습니다.");
    	} else {
    		result.put("cnt", 1);
    		result.put("message", "회원님의 아이디는 " + userid + " 입니다. 로그인 페이지로 이동할까요?");
    	}    	
    	return result;
    }
    
    
    //비공개 계정 설정
    @RequestMapping("/user/private")
    public String privateAccount (HttpServletRequest request, Model model, RedirectAttributes rttr) {
	    HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session .getAttribute("loginUser");
		
		if(loginUser == null) {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요!");
			return "redirect:http://localhost:3000/";
		} else {
			String userid = (String) loginUser.get("USERID");
			ms.privateAccount(userid);
			rttr.addFlashAttribute("message", "계정이 비공개로 설정되었어요!");
			loginUser.replace("USEYN", "p");
			
			String referer = request.getHeader("Referer");
			
			System.out.println("private access");
			System.out.println("referer : " + referer);
			
		    return "redirect:http://localhost:3000/"+ referer;
		}
    }
    
    //비공개 계정 -> 공개 계정으로 전환
    @RequestMapping("/user/public")
    public String PublicAccount (HttpServletRequest request, Model model, RedirectAttributes rttr) {
	    HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session .getAttribute("loginUser");
		
		if(loginUser == null) {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요!");
			return "redirect:/login/form";
		} else {
			String userid = (String) loginUser.get("USERID");
			ms.PublicAccount(userid);
			
			rttr.addFlashAttribute("message", "계정 비공개를 해제 했어요!");
			
			loginUser.replace("USEYN", "y");
			
			String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
		}
    }
    
    
}
