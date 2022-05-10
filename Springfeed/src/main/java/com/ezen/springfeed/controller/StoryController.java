package com.ezen.springfeed.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.springfeed.dto.StoryDto;
import com.ezen.springfeed.service.MemberService;
import com.ezen.springfeed.service.StoryService;

@RestController
public class StoryController {

	@Autowired
	StoryService ss;
	
	@Autowired
	MemberService ms;

	//스토리 추가
	@RequestMapping(value="/story/add", produces="application/json") 
	public Map<String, Object> addStory(@ModelAttribute("dto") StoryDto storydto,
			HttpServletRequest request) {
		
		Map<String, Object> resultMap = new HashMap<>();
		String message = "";
		int status = 0;
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null)  {	//로그인 체크
			message = "로그인 후 이용해주세요.";
		} else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			
			if(storydto.getFontColor() == null || ((String)storydto.getFontColor()).equals(""))
				//폰트 색을 지정하지 않은 경우 기본 흰색
				paramMap.put("fontcolor", "white");
			else
				paramMap.put("fontcolor", storydto.getFontColor());
			
			paramMap.put("story_content", storydto.getStory_content());
			paramMap.put("userid", loginUser.get("USERID"));
			paramMap.put("story_num", 0);
			paramMap.put("story_img", storydto.getStory_img());
			paramMap.put("status", status);
			
			ss.addStory(paramMap);	
			
			status = Integer.parseInt(paramMap.get("status").toString());
			
			if(status == 0) message = "스토리 작성에 실패하였습니다. 다시 시도해주세요.";
			else {
				message = "스토리를 작성했어요.";
				int story_num = Integer.parseInt(paramMap.get("story_num").toString());
				resultMap.put("story_num", story_num);
			}
		}
		
		resultMap.put("message", message);
		resultMap.put("status", status);
		return resultMap;
	}
	
	
	//스토리 읽어오기
	//파라미터로 userid (메인 스토리목록을 클릭해 들어온 경우), story_num(이전, 다음 게시물보기를 통한 접근 혹은 직접 접근)
	//두 가지 경우로 나눠서 처리
	@RequestMapping(value="/story", produces="application/json")
	public Map<String, Object> storyDetail(
			@RequestParam(value="userid", required=false) String userid, 
			@RequestParam(value="story_num", required=false) String story_num, 
			HttpServletRequest request) {

		Map<String, Object> resultMap = new HashMap<>();
		String message = "";
		int status = 0;
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null)  {	//로그인 확인
			message = "로그인 후 이용해주세요!";
		} else { //로그인이 되어이 ㅆ는 경우
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			
			if(story_num == null) paramMap.put("story_num", null);	//userid 로 접근한 경우
			else paramMap.put("story_num", Integer.parseInt(story_num));	//story_num으로 접근한 경우
			
			if(userid != null) {	//userid 으로 접근한 경우 
				paramMap.put("userid", userid);
				ms.findRecentStory(paramMap);	//해당 유저가 작성흔 스토리 중 가장 큰 story_num을 검색하여 story_num에 대입받음
			}
			
			paramMap.put("ref_cursor", null);
			paramMap.put("prev", 0);
			paramMap.put("next", 0);
			paramMap.put("fontcolor", null);
			paramMap.put("useyn", null);
			paramMap.put("memberUseyn", null);
			
			ss.getStory(paramMap);	//story_num으로 게시물 조회
			
			ArrayList<HashMap<String, Object>> list 
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			if(list.size() == 0) {	
				message = "존재하지 않는 스토리 입니다.";
			} else if(((String)paramMap.get("useyn")).equals("b") ){	//신고받은 게시물
				resultMap.put("useyn", "b");
				message = "차단된 계시물 입니다.";
			} else {
				HashMap<String, Object> storyMap = list.get(0);
				resultMap.put("StoryDto", storyMap);
				
				//팔로우 검사
				HashMap<String, Object> followMap = new HashMap<>();
				followMap.put("followed", storyMap.get("USERID"));
				followMap.put("follower", loginUser.get("USERID"));
				followMap.put("followedResult", 0);
				followMap.put("followingResult", 0);
				
				ms.getFollow(followMap);	
				//로그인 유저가 글슨이를 팔로잉 했다면 followingResult 1
				//글쓴이가 로그인 유저를 팔로잉 했다면 followedResult 1
				
				int followedResult = Integer.parseInt(followMap.get("followedResult").toString());
				int followingResult = Integer.parseInt(followMap.get("followingResult").toString());
				
				//사용자가 글쓴이를 팔로우 했는지
				resultMap.put("isFollowing", followingResult);
				
				if(((String)paramMap.get("memberUseyn")).equals("p") && 	
						(followedResult == 0 || followingResult == 0)) { //비공개 계정과 맞팔이 아닐 경우
					
						resultMap.put("useyn", "p");
						message = "비공개된 게시물이에요.";
					
				} else if(((String)paramMap.get("useyn")).equals("y") ) { //비공개 계정이 아닌 경우 
					
					System.out.println("로그인 한 유저 : " + loginUser.get("USERID"));
					System.out.println("스토리 작성자 : " + storyMap.get("USERID"));
					
					HashMap<String, Object> blockCheckMap = new HashMap<>();
					blockCheckMap.put("userid", loginUser.get("USERID"));
					blockCheckMap.put("blocked", storyMap.get("USERID"));
					blockCheckMap.put("ref_cursor", null);
					
					ms.blockCheck(blockCheckMap);	//차단한 유저인지 검색
					
					ArrayList<HashMap<String, Object>> blockList 
						= (ArrayList<HashMap<String, Object>>) blockCheckMap.get("ref_cursor");
					
					if (blockList.size() == 0) {	//차단한 유저가 아닐 경우
						
						resultMap.put("fontcolor", (String)paramMap.get("fontcolor"));
						
						//이전글
						ss.getStoryPrev(paramMap);	//현재 게시물을 제외한 나머지 게시물 중 가장 큰 story_num
						if(paramMap.get("prev") == null) storyMap.put("prev", 0);
						else resultMap.put("prev", Integer.parseInt(paramMap.get("prev").toString()));
						
						//다음글 검색
						ss.getStoryNext(paramMap); //현재 게시물을 제외한 나머지 게시물 중 가장 작은 story_num
						if(paramMap.get("next") == null) storyMap.put("next", 0);
						else resultMap.put("next", Integer.parseInt(paramMap.get("next").toString()));
						
						status = 1;
					} 
					else {	//차단한 유저일 경우
						message = "차단한 유저의 게시물이에요.";
					}
				} else {
					message = "삭제되었거나 비활성화된 계정의 스토리 입니다.";
				}
			}
		}
		resultMap.put("status", status);
		resultMap.put("message", message);
		return resultMap;
	}

	//스토리 삭제
	@RequestMapping(value="/story/delete", produces="application/json")
	public Map<String, Object> deleteStory(@RequestParam("story_num") int story_num, 
			HttpServletRequest request) {
		int status = 0;
		String message = "";
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");

		if(loginUser== null) {
			message = "로그인 후 이용해주세요.";
		} else {
			String userid = (String) loginUser.get("USERID");
			ss.deleteStory(story_num);
			
			message = "스토리를 삭제했어요!";
			status = 1;
		}
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("message", message);
		resultMap.put("status", status);
		return resultMap;
	}
	
	//스토리의 내용을 가지고 스토리 수정 폼으로 이동
	@RequestMapping(value="/story/edit/form", produces="application/json")
	public Map<String, Object> editStory(@RequestParam("story_num") int story_num, 
			HttpServletRequest request) {

		String message = "";
		int status = 0;
		Map<String, Object> resultMap = new HashMap<>();
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if(loginUser== null) {
			message = "로그인 후 이용해주세요.";
		} else {
			System.out.println(story_num);
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("ref_cursor", null);
			paramMap.put("story_num", story_num);
			paramMap.put("fontcolor", "");
			paramMap.put("useyn", "");
			paramMap.put("memberUseyn", "");
			
			ss.getStory(paramMap);
			
			ArrayList<HashMap<String, Object>> list
			= (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
			
			HashMap<String, Object> storyMap = list.get(0);
			
			if(((String) storyMap.get("USERID")).equals((String)loginUser.get("USERID"))) {
				message = "내 스토리만 수정할 수 있어요!";
			} else {
				status = 1;
				System.out.println("스토리번호 : " + storyMap.get("STORY_NUM"));
				System.out.println("폰트 컬러 : " + paramMap.get("FONTCOLOR"));
				System.out.println("이미지 : " + storyMap.get("STORY_IMG"));
				System.out.println("콘텐츠 : " + storyMap.get("STORY_CONTENT"));
				resultMap.put("StoryDto", storyMap);
				resultMap.put("fontcolor", (String)paramMap.get("fontcolor"));
			}
		}
		resultMap.put("status", status);
		resultMap.put("message", message);
		return resultMap;
	}
	
	//스토리 수정
	@RequestMapping(value="/story/edit", produces="application/json") 
	public Map<String, Object> editStory(@ModelAttribute("StoryDto") @Valid StoryDto storydto,
			HttpServletRequest request,
			@RequestParam("story_num") int story_num,
			@RequestParam(value="oldFontColor", required=false) String oldFontcolor,
			@RequestParam(value="oldPicture", required=false) String oldPicture) {
		
		String message = "";
		int status = 0;
		Map<String, Object> resultMap = new HashMap<>();
		
		HttpSession session = request.getSession();
		HashMap<String, Object> loginUser = 
				(HashMap<String, Object>) session.getAttribute("loginUser");
		
		if (loginUser == null)  {	//로그인 확인
			message = "로그인 후 이용해주세요.";
			
		} else {
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			
			if(storydto.getFontColor() == null || ((String)storydto.getFontColor()).equals(""))	//새로 폰트컬러를 지정하지 않은 경우
				if(oldFontcolor == null || ((String)oldFontcolor).equals(""))	//기존 폰트컬러 값이 존재하지 않는 경우
					paramMap.put("fontcolor", "white");
				else 
					paramMap.put("fontcolor", oldFontcolor);
			else
				paramMap.put("fontcolor", storydto.getFontColor());
			
			if(storydto.getStory_img() == null || ((String)storydto.getStory_img()).equals(""))	//이미지를 수정하지 않은 경우
				paramMap.put("story_img", oldPicture);	//기존 이미지를 유지
			else 
				paramMap.put("story_img", storydto.getStory_img());
			
			paramMap.put("story_content", storydto.getStory_content());
			paramMap.put("story_num", story_num);
			paramMap.put("status", 0);
			
			ss.editStory(paramMap);
			
			status = Integer.parseInt(paramMap.get("status").toString());
			
			if(status==0) message = "스토리 수정에 실패했어요. 다시 시도해주세요.";
			else message = "스토리를 수정했어요!";
		}
		resultMap.put("status", status);
		resultMap.put("message", message);
		return resultMap;
	}
}