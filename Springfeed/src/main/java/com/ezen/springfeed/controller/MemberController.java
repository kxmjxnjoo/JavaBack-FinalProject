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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.springfeed.dto.MailDto;
import com.ezen.springfeed.dto.MemberDto;
import com.ezen.springfeed.service.MemberService;
import com.ezen.springfeed.service.sendEmailService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class MemberController {

	@Autowired
	MemberService ms;
	
	//로그인 폼으로
	@RequestMapping(value="/login/form")
	public String loginForm() {
		return "member/login";
	}
    
    //로그인 액션
    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@ModelAttribute("dto") @Valid MemberDto memberdto,
    		BindingResult result, HttpServletRequest request,
    		Model model) {
    	
    	String url = "member/login";
    	
    	System.out.println(memberdto.getUserid());
    	if(memberdto.getUserid() == null || memberdto.getUserid().equals("")) {
    		model.addAttribute("message", "아이디를 입력해주세요");
    	} else if(memberdto.getPassword() == null || memberdto.getPassword().equals("")) {
    		model.addAttribute("message", "비밀번호를 입력해주세요");
    	} else {
    		HashMap<String, Object> paramMap = new HashMap<>();
    		paramMap.put("userid", memberdto.getUserid());
    		paramMap.put("ref_cursor", null);
    		ms.getMember(paramMap);
    		
    		ArrayList<HashMap<String, Object>> list
    			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
    		if(list.size() == 0) {
    			model.addAttribute("message", "잘못된 사용자 아이디입니다. 다시 확인하세요.");
    			return "member/login";
    		} 
    		
    		HashMap<String, Object> mvo = list.get(0);
    		if(mvo.get("PASSWORD") == null) {
    			model.addAttribute("message", "로그인에 문제가 발생했어요:( QnA를 남겨주시면 빠르게 해결해드릴게요!");
    			//고객센터로 연결하는 버튼 모달 만들기

    		}  else if (((String)mvo.get("USEYN")).equals("n") ) {
    			model.addAttribute("messageConfirm", "비활성화된 계정입니다. 계정을 복구 할까요?");
    			model.addAttribute("sendUrl", "/user/activate?userid="+memberdto.getUserid());
    		} else if (memberdto.getPassword().equals((String)mvo.get("PASSWORD"))) {
    			HttpSession session = request.getSession();
    			session.setAttribute("loginUser", mvo);
    			url = "redirect:/";
    		} else {
    			model.addAttribute("message", "잘못된 비밀번호입니다. 다시 확인하세요.");
    		}
    	}
        return url;
    }

    //로그아웃
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");
        return "redirect:/";
    }

    
    
    //이용약관
    
    

    // 회원가입 폼
    @RequestMapping("/join/form")
    public String joinForm() {
        return "member/join";
    }
    
    
    //회원가입 시 중복, 유효성 체크 
    @RequestMapping(value="/join/id", method=RequestMethod.POST)
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
	    	
	    	System.out.println("확인 결과 : " + cnt );
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
    @RequestMapping(value="/join", method=RequestMethod.POST)
    public String join(@ModelAttribute("dto") @Valid MemberDto memberdto,
    		BindingResult result,
    		@RequestParam(value="reid", required=false) String reid,
    		HttpServletRequest request, Model model) {
    	
    	model.addAttribute("reid", reid);
    	String url = "member/join";
    	
    	if(result.getFieldError("phone")!= null) {
            model.addAttribute("message", result.getFieldError("phone").getDefaultMessage());
         } else if(result.getFieldError("email")!= null) {
            model.addAttribute("message", result.getFieldError("email").getDefaultMessage());
         } else if(result.getFieldError("name")!= null) {
            model.addAttribute("message", result.getFieldError("name").getDefaultMessage());
         } else if(result.getFieldError("userid")!= null) {
            model.addAttribute("message", result.getFieldError("userid").getDefaultMessage());
         } else if(result.getFieldError("password")!= null) {
            model.addAttribute("message", result.getFieldError("password").getDefaultMessage());
         } else {
 			
 			HashMap<String, Object> paramMap = new HashMap<>();
 			paramMap.put("userid",memberdto.getUserid());
 			paramMap.put("password",memberdto.getPassword());
 			paramMap.put("name",memberdto.getName());
 			paramMap.put("email",memberdto.getEmail());
 			paramMap.put("phone",memberdto.getPhone());
 			
 			ms.insertMember(paramMap);
 			
 			model.addAttribute("message", "회원가입이 완료되었어요:) 로그인 후 이용해주세요.");
 			url = "member/login";
 		}
    	
        return url;
    }

    // 팔로우 
    @RequestMapping("/follow")
    public String follow(HttpServletRequest request, 
    		@RequestParam("userid") String userid,
    		Model model, RedirectAttributes rttr) {
    	HttpSession session = request.getSession();
		
    	String url = "";
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null) { 
			url = "member/login";
		} else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("follower", (String) loginUser.get("USERID"));
			paramMap.put("followed", userid);
			paramMap.put("result", 0);
			
			ms.insertFollow(paramMap);
	
			rttr.addFlashAttribute("message", userid+"님을 팔로우 했어요");
			
			paramMap.put("notitype", 1);
			paramMap.put("notiresult", 0);
			
			String referer = request.getHeader("Referer");
		    return "redirect:"+ referer;
		}
    	
        return url;
    }

    // 언팔로우
    @RequestMapping("/unfollow")
    public String unfollow(HttpServletRequest request, 
    		@RequestParam("userid") String userid,
    		Model model, RedirectAttributes rttr) {
    	
    	HttpSession session = request.getSession();
		
    	String url = "";
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null) { 
			url = "member/login";
		} else {
			
			String follower = (String)loginUser.get("USERID");
			
			System.out.println(userid);
			System.out.println(follower);
			
			if(!follower.equals(userid)) {
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("follower", follower);
				paramMap.put("followed", userid);
				paramMap.put("result", 0);
				
				ms.unfollow(paramMap);
				
				rttr.addFlashAttribute("message", userid + "님을 언팔로우 했습니다.");

			}
		}
		String referer = request.getHeader("Referer");
	    return "redirect:"+ referer;
    }
    
		
    @RequestMapping("/user/notification")
    public ModelAndView Notification(HttpServletRequest request, Model model,
    		RedirectAttributes rttr) {
    	HttpSession session = request.getSession();
		
    	ModelAndView mav = new ModelAndView();
    	String url = "";
    	
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null) { 
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요!");
			url = "redirect:/login/form";
		} else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("userid", loginUser.get("USERID"));

			System.out.println(loginUser.get("USERID"));
			ms.getNotification(paramMap);
			
			ArrayList<HashMap<String, Object>> notiList 
				= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			if (notiList.size() == 0) {
				mav.addObject("noNotification", 1);
			} else {
				for(HashMap<String, Object> temp : notiList) {
//					LocalDate now = LocalDate.now();
//					LocalDate notiDate = ((timeStamp) temp.get("CREATE_DATE")).toLocalDate();
//					Period period = Period.between(now, notiDate);
//					long diff = Math.abs(period.getDays());
//					
//					if(diff == 0) {
//						temp.replace("CREATE_DATE", "오늘");
//					} else if(diff < 30) {
//						temp.replace("CREATE_DATE", diff+"일 전");
//					} else if(diff < 365) {
//						temp.replace("CREATE_DATE", Math.abs(diff / 30) + "달 전");
//					} else {
//						temp.replace("CREATE_DATE", "오래 전");
//					}
					
				}
			} 

			mav.addObject("notiList", notiList);
			
			mav.setViewName("noti/noti");	
		}
		return mav;
    }
    
    @RequestMapping("/api/notiCount")
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
    		
    		notiCount = Integer.parseInt(paramMap.get("notiCount").toString());
    	}
    	
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
    	
    	String url = "redirect:/user/edit/form";
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
     			
     			url = "redirect:/post?userid="+ userid;
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
			return "member/login";
		} else {
			
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("userid", loginUser.get("USERID"));
			
			session.removeAttribute("loginUser");
			ms.deleteAcount(paramMap);
			model.addAttribute("message", "계정 비활성화가 완료되었습니다. 다음에 다시 만나요!");
			
			return  "member/login";
		}
    }
    
    //비활성화 해제
    @RequestMapping("/user/activate")
    public String activationAccount(HttpServletRequest request, Model model,
    		@RequestParam("userid") String userid) {
        HttpSession session = request.getSession();
        
        ms.activateAccount(userid);
        model.addAttribute("message", "계정을 복구했어요! 로그인 후 이용해주세요:)");
        
        return "member/login";
        
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
				
				String referer = request.getHeader("Referer");
			    return "redirect:"+ referer;
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
		    return "redirect:"+ referer;
		}
    }

    
    //아이디 비밀번호 찾기 폼
    @RequestMapping("/find/form")
    public String findIdPw() {
    	return "member/findIdPwd";
    }

    //비밀번호 찾기
    @ResponseBody 
    @GetMapping("/find/pw")
    public Map<String, Boolean> pwdFind(@ModelAttribute("dto") @Valid MemberDto memberdto,
          Model model){
       Map<String, Boolean> resultMap = new HashMap<>();
       boolean pwFindCheck = false;
       ms.userEmailCheck(memberdto.getEmail(), memberdto.getName(), pwFindCheck);
       
       System.out.println(pwFindCheck);
       if(pwFindCheck == true) 
          resultMap.put("check", pwFindCheck);
       else 
          model.addAttribute("message", "해당하는 회원을 찾을 수 없습니다.");
       return resultMap;
    }
    
    @Autowired
    sendEmailService ses;
    
    //이메일 전송
    @ResponseBody
    @PostMapping("/find/sendEmail")
    public void sendEamil(String email, String name) {
       MailDto dto = ses.createMailAndChangePassword(email, name);
        ses.mailSend(dto);
    
    }
  
    //아이디 찾기 액션
    @RequestMapping("/find/id")
    public String findId(Model model, HttpServletRequest request, 
    		@RequestParam("name") String name,
    		@RequestParam("phone") String phone) {
    	
    	HashMap<String,Object> paramMap = new HashMap<>();
    	paramMap.put("name", name);
    	paramMap.put("phone", phone);
    	paramMap.put("userid", null);
    	
    	ms.findId(paramMap);
    	
    	String userid = (String) paramMap.get("userid");
    	
    	if(userid == null || userid.equals("")) {
    		model.addAttribute("message", "같은 정보의 회원을 찾을 수 없습니다.");
    		return "member/findIdPwd";
    	} else {
    		model.addAttribute("userid", userid);
    		model.addAttribute("messageConfirm", 
    				"회원님의 아이디는 " + userid + " 입니다. 로그인 페이지로 이동할까요?" );
    		model.addAttribute("sendUrl", "/login/form");
    		return "member/findIdPwd";
    	}    	
    	
    }
    
    
    //비공개 계정 설정
    @RequestMapping("/user/private")
    public String privateAccount (HttpServletRequest request, Model model, RedirectAttributes rttr) {
	    HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session .getAttribute("loginUser");
		
		if(loginUser == null) {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요!");
			return "redirect:/login/form";
		} else {
			String userid = (String) loginUser.get("USERID");
			ms.privateAccount(userid);
			
			rttr.addFlashAttribute("message", "계정이 비공개로 설정되었어요!");
			return "redirect:/";
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
			return "redirect:/";
		}
    }
    
    
}
