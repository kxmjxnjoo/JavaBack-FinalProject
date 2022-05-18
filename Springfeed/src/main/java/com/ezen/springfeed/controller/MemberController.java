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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.springfeed.dto.MemberDto;
import com.ezen.springfeed.service.MemberService;
import com.ezen.springfeed.service.sendEmailService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@RestController
@CrossOrigin(origins= "*")
public class MemberController {

	@Autowired
	MemberService ms;
	
    //로그인 액션
    @RequestMapping(value="/login", method=RequestMethod.POST, produces = "application/json")
    public String login(HttpServletRequest request, @RequestBody HashMap<String, Object> memberdto) {

		Object userid = memberdto.get("userid");
		Object password = memberdto.get("password");
    	
    	String status = "";
    	if(userid == null || userid.equals("")) {
    		status = "emptyId"; //아이디 미입력
    	} else if(password == null || password.equals("")) {
    		status = "emptyPassword"; //비밀번호 미입력
    	} else {
    		HashMap<String, Object> paramMap = new HashMap<>();
    		paramMap.put("userid", (String) userid);
    		paramMap.put("ref_cursor", null);
    		ms.getMember(paramMap); //아이디로 사용자 검색
    		
    		ArrayList<HashMap<String, Object>> list
    			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
    		if(list.size() == 0) {
    			status = "wrongId"; //존재하지 않는 아이디
    		} else {
				HashMap<String, Object> mvo = list.get(0);

	    		if(mvo.get("PASSWORD") == null) {
	    			status = "PasswordError"; //DB password 에러
	    		}  else if (mvo.get("USEYN").equals("n") ) {
	    			status = "disableAccount";
	    			//비활성화 계정 confirm 필요 
	    		} else if ( password.equals((String)mvo.get("PASSWORD")) ) {
	    			status = "loginComplete"; //정상 로그인

					System.out.println(mvo);
					request.getSession().setAttribute("loginUser", mvo);
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
            session.removeAttribute("loginUser");	//세션에서 로그인 정보 삭제
            result = 1;
    	} catch (Exception e) {  e.printStackTrace(); }
        return result;
    }
    
    //회원가입 시 중복, 유효성 체크 
    @RequestMapping(value="/join/idCheck", method=RequestMethod.POST)
    @ResponseBody
    public int idCheck(@ModelAttribute("dto") @Valid MemberDto memberdto,
    		BindingResult result) {

    	int cnt = 1;	//중복/유효성 테스트 통과시 1, 통과 못할 시 0
    	if(memberdto.getUserid() != null) {		// 아이디 중복 검사
        	String userid = memberdto.getUserid();
	    	HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("cnt", 0);
			paramMap.put("userid", userid);
	    	ms.idCheck(paramMap);	//userid로 사용자 검색
	
	    	if(result.getFieldError("userid") == null) 
	    		cnt = Integer.parseInt(paramMap.get("cnt").toString()); 
	    		//select count(*) from member where userid=? 결과가 리턴
    	} else if (memberdto.getEmail() != null) {
    		if(result.getFieldError("email") == null) cnt = 0;	//validation error 발생 시 0을 리턴
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
    	
    	//validation 에러 체크
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
 			status = Integer.parseInt((paramMap.get("status").toString()));	//DB insert 오류 시 0을 리턴
 			
 			if(status==1)
 				message = "회원가입이 완료되었어요. 로그인 후 이용해주세요.";
 			else
 				message = "회원가입에 오류가 발생했습니다. 다시 시도해주세요.";
 		}
    	
    	resultMap.put("message", message);
    	resultMap.put("status", status);
    	return resultMap;
    }

    //알림 가지고 오기
    @RequestMapping(value="/user/notification",  produces="application/json")
    public ModelAndView Notification(HttpServletRequest request, Model model,
			 RedirectAttributes rttr) {
		HttpSession session = request.getSession();
		
		ModelAndView mav = new ModelAndView();
		String url = "";
		
		HashMap<String, Object> loginUser =
		(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null) {
			rttr.addFlashAttribute("message", "로그인 후 이용해주세요!");
			mav.setViewName("redirect:/login/form");
		} else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("ref_cursor", null);
			paramMap.put("userid", loginUser.get("USERID"));
			
			ms.getNotification(paramMap);
			ArrayList<HashMap<String, Object>> notiList
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			if (notiList.size() == 0) {	mav.addObject("noNotification", 1); } 
			else {
				for(HashMap<String, Object> temp : notiList);
			}
		mav.addObject("notiList", notiList);
		mav.setViewName("noti/noti");
		}
		return mav;
	}
    
    
    // 알림 숫자 체크
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
    	
    	return notiCount;
    }
    
    //수정폼으로 이동 시, 비밀번호를 제외한 정보를 보냄
    @RequestMapping(value="/user/edit/form", produces="application/json")
    public MemberDto editUserForm(Model model, HttpServletRequest request) {
    	MemberDto dto = new MemberDto();
    	HttpSession session = request.getSession();
    	
    	HashMap<String, Object> loginUser 
    		= (HashMap<String, Object>) session .getAttribute("loginUser");
    	
    	if(loginUser == null) {
    		return null; 
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
	    	
	    	return dto;
    	}
    }
    
    //이미지 업로드
    @Autowired
    ServletContext context;
    
    @RequestMapping("/uploadImg")
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
    
    //사용자 정보 수정
    @RequestMapping(value="/user/edit", produces="application/json")
    public Map<String, Object> userEdit(@ModelAttribute("dto") @Valid MemberDto memberdto,
    		BindingResult result, HttpServletRequest request, @RequestParam("oldPicture") String oldPicture) {

    	HttpSession session = request.getSession();
    	HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session .getAttribute("loginUser");
    	String userid = (String)loginUser.get("USERID");
    	int status = 0;
    	String message = "";
    	Map<String, Object> resultMap = new HashMap<>();
    	
    	//Validation
    	if(result.getFieldError("password")!= null) {
    		message = result.getFieldError("password").getDefaultMessage();
         } else if(result.getFieldError("name")!= null) {
        	message = result.getFieldError("name").getDefaultMessage();
          } else if(result.getFieldError("email")!= null) {
        	message = result.getFieldError("email").getDefaultMessage();
         }  else if(result.getFieldError("phone")!= null) {
        	message = result.getFieldError("phone").getDefaultMessage();
         } else {
        		HashMap<String, Object> paramMap = new HashMap<>();
        		paramMap.put("status", status);	//성공 1, 실패 0
        		paramMap.put("USERID", userid);
     			paramMap.put("PASSWORD",memberdto.getPassword());
     			paramMap.put("NAME",memberdto.getName());
     			paramMap.put("EMAIL",memberdto.getEmail());
     			paramMap.put("PHONE",memberdto.getPhone());
     			paramMap.put("INTRODUCE",memberdto.getIntroduce());
     			if(memberdto.getImg()==null || memberdto.getImg().equals(""))	//새로 업로드된 사진이 없을 때
     				paramMap.put("IMG", oldPicture);	//기존의 사진을 그대로 유지
     			else
     				paramMap.put("IMG", memberdto.getImg());
     			
     			ms.userEdit(paramMap);
     			
     			status = Integer.parseInt(paramMap.get("status").toString());
     			if(status == 1) message = "회원 정보 수정이 완료되었어요!";
     			else message = "회원 정보 수정에 실패했어요. 다시 시도해주세요.";
     			session.setAttribute("loginUser", paramMap);
         }
    	resultMap.put("message", message);
    	resultMap.put("status", status); 
    	return resultMap;
    }
    
    //계정 비활성화 
    @RequestMapping("/deleteAcount")
    public int deleteAcount(HttpServletRequest request, Model model) {
    	
    	HttpSession session = request.getSession();
    	HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session.getAttribute("loginUser");
		
    	int status = 0;
    	
		if(loginUser == null) {
			//model.addAttribute("로그인 후 이용해주세요!");
		} else {
	
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("userid", loginUser.get("USERID"));
			paramMap.put("status", status);
			
			session.removeAttribute("loginUser");
			
			ms.deleteAcount(paramMap); //member 테이블의 useyn 필드를 n으로 변경
			//model.addAttribute("message", "계정 비활성화가 완료되었습니다. 다음에 다시 만나요!");

			status = Integer.parseInt(paramMap.get("status").toString());
			session.removeAttribute("loginUser");	//로그아웃 처리
		}
    	return status; //성공 1, 실패 0
    }
    
    //비활성화 해제
    @RequestMapping(value="/user/activate", produces="application/json")
    public int activationAccount(@RequestParam("userid") String userid) {
        int status = 0;
        
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", userid);
        paramMap.put("status", status);
        
        ms.activateAccount(paramMap); //member 테이블의 useyn 필드를 y로 변경
        
        status = Integer.parseInt(paramMap.get("status").toString());
        System.out.println("activate account status : " + status); //성공 1, 실패 0
        
        return status;
    }
    
    
    //블락
    @RequestMapping(value="/block", produces="application/json")
    public Map<String, Object> block(HttpServletRequest request, @RequestParam("userid") String userid) {
    	HttpSession session = request.getSession();
    	HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session .getAttribute("loginUser");	
		
    	int status = 0;
    	String message = "";
    	
		if(loginUser == null) {	 //로그인 확인
			message = "로그인 해주세요.";
		} else {
			HashMap<String, Object> paramMap = new HashMap<>();
    		paramMap.put("userid", userid);
    		paramMap.put("ref_cursor", null);
    		
    		ms.getMember(paramMap);
    		
			ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			if(list.size() == 0) {
				message = "존재하지 않는 회원입니다.";
			} else {
				paramMap.put("userid", loginUser.get("USERID"));
				paramMap.put("blocked", userid);
				paramMap.put("status", status);
				
				ms.blockMember(paramMap);
				status = Integer.parseInt(paramMap.get("status").toString());	//DB 오류시 0
				
				if(status == 1) message = userid+"님을 차단했어요.";
				else if (status == -1) message = "이미 차단된 회원입니다.";
				else message = "차단에 실패했어요. 다시 시도해주세요."; 
			}
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("message", message);
		resultMap.put("status", status);
		return resultMap;
    }
    
    
    //차단 해제
    @RequestMapping(value="/unblock", produces="application/json")
    public Map<String, Object> unblock(HttpServletRequest request, @RequestParam("userid") String userid) {
    	HttpSession session = request.getSession();
    	HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session .getAttribute("loginUser");
    	
    	int status = 0;
    	String message = "";
		
		if(loginUser == null) {	//로그인 확인
			message = "로그인 후 이용해주세요!";
		} else {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("userid", loginUser.get("USERID"));
			paramMap.put("blocked", userid);
			paramMap.put("ref_cursor", null);
			
			ms.blockCheck(paramMap);	//해당 유저가 블락 되어 있느지 확인
			
			ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			if(list.size() == 0) {
				message = "차단 목록에서 " + userid +"님을 찾을 수 없습니다.";
			} else {
				paramMap.put("status", status);
				ms.unblockMember(paramMap);
	
				status = Integer.parseInt(paramMap.get("status").toString());
				if(status == 0) message = "차단을 해제하지 못했어요. 다시 시도해주세요.";
				else message = "차단을 해제했어요.";
			}
		}
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", status);
		resultMap.put("message", message);
		return resultMap;
    }

    @Autowired sendEmailService sms; 

    //비밀번호 찾기
    @ResponseBody 
    @RequestMapping(value="/find/pw", method=RequestMethod.POST, produces="application/json")
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
	   	
	   ms.findPw(paramMap);	//select count(*) from member where userid=?, phone=?, email=? 의 결과가 cnt
	   
	   int cnt = Integer.parseInt(paramMap.get("cnt").toString());
	   
	   if(cnt == 1) {
		   String tempPassword = sms.getTempPassword();	//무작위로 생성된 임시 비밀번호를 받아옴
		   ms.changePw(userid, tempPassword); //비밀번호를 임시 비밀번호로 변경
		   resultMap.put("cnt", 1);
		   resultMap.put("message", userid + "님의 임시 비밀번호는 " + tempPassword + "입니다. 로그인 후 비밀번호를 변경해주세요!");
		   
	   } else {
		   resultMap.put("cnt", 0);
		   resultMap.put("message", "같은 정보의 회원을 찾을 수 없습니다.");
	   }
       return resultMap;
    }
   
  
    //아이디 찾기 액션 //ajax
    @RequestMapping(value="/find/id", method=RequestMethod.POST, produces="application/json")
    @ResponseBody
    public Map<String, Object> findId(Model model, HttpServletRequest request,
    		@RequestParam("name") String name,
    		@RequestParam("phone") String phone) {
    	
    	HashMap<String,Object> paramMap = new HashMap<>();
    	paramMap.put("name", name);
    	paramMap.put("phone", phone);
    	paramMap.put("userid", null);
    	
    	ms.findId(paramMap);	//select userid from member where phone=?, email=? 의 결과가 userid
    	
    	String userid = (String) paramMap.get("userid");
    	
    	HashMap<String, Object> result = new HashMap<>();
    	
    	if(userid == null || userid.equals("")) {	//userid 결과가 없는 경우
    		result.put("status", 0);
    		result.put("message", "같은 정보의 회원을 찾을 수 없습니다.");
    	} else {	//userid 결과가 있는 경우
    		result.put("status", 1);
    		result.put("message", "회원님의 아이디는 " + userid + " 입니다. 로그인 페이지로 이동할까요?");
    	}    	
    	return result;
    }
    
    
    //비공개 계정 설정
    @RequestMapping(value="/user/private", produces="application/json")
    public Map<String, Object> privateAccount (HttpServletRequest request) {
	    HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session .getAttribute("loginUser");
		
		int status = 0;
		String message = "";
		
		if(loginUser == null) {
			message = "로그인 후 이용해주세요!";
		} else {
			if (((String)loginUser.get("USEYN")).equals("p")) {
				message = "이미 계정이 비공개로 설정되어있어요:)";
			} else {
				String userid = (String) loginUser.get("USERID");
				ms.privateAccount(userid);	//로그인한 사용자의 useyn을 p로 변경
				message = "계정이 비공개로 설정되었어요!";
				status = 1;
				loginUser.replace("USEYN", "p");	//세션의 로그인 정보도 수정
			}
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", status); //성공 1, 실패 0
		resultMap.put("message", message);
		
		return resultMap;
    }
    
    //비공개 계정 -> 공개 계정으로 전환
    @RequestMapping(value="/user/public", produces="application/json")
    public Map<String, Object> PublicAccount (HttpServletRequest request, Model model, RedirectAttributes rttr) {
	    HttpSession session = request.getSession();
		HashMap<String, Object> loginUser 
		= (HashMap<String, Object>) session .getAttribute("loginUser");
		
		int status = 0;
		String message = "";
		
		if(loginUser == null) {
			message = "로그인 후 이용해주세요!";
		} else {
			if(((String)loginUser.get("USEYN")).equals("y")) { 
				message = "이미 공개된 계정이에요:)";
			} else {
				String userid = (String) loginUser.get("USERID");
				ms.PublicAccount(userid);	//로그인한 사용자의 useyn을 y로 변경
				
				message = "계정 비공개를 해제 했어요!";
				status = 1;
				loginUser.replace("USEYN", "y");	//세션의 로그인 정보도 수정
			}
		}
		Map<String, Object> resultMap = new  HashMap<>();
		resultMap.put("status", status);	//성공 1, 실패 0
		resultMap.put("message", message);
		
		return resultMap;
    }
}
