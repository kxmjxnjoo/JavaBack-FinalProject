//package com.ezen.springfeed.tmp.controller;
//import com.ezen.springfeed.member.Member;
//import com.ezen.springfeed.post.Post;
//import com.ezen.springfeed.reply.Reply;
//import com.ezen.springfeed.tmp.service.TmpMemberService;
//import com.ezen.springfeed.tmp.service.PostService;
//import com.ezen.springfeed.tmp.service.StoryService;
//import com.ezen.springfeed.tmp.service.UtilService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.HashMap;
//
//@RestController
//@CrossOrigin(origins= "*")
//public class ReactController {
//
//    @Autowired
//    PostService ps;
//
//    @Autowired
//    TmpMemberService ms;
//
//    @Autowired
//    UtilService us;
//
//    @Autowired
//    StoryService ss;
//
//    // Get loginUser's feed
//    @RequestMapping(value="/api/post/feed", produces="application/json")
//    public ArrayList<Post> getPostFeed(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="page", required=false) Integer page) {
//
//        String userid = getLoginUserid(request);
//        if(userid == null) {
//            return new ArrayList<>();
//        }
//
//        // Get 10 posts by pages
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("userid", userid);
//        paramMap.put("page", page == null ? 0 : page);
//        paramMap.put("ref_cursor", null);
//        ps.getPostFeed(paramMap);
//
//        ArrayList<HashMap<String, Object>> list =
//                (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
//        if(list.size() == 0 || list == null) {
//            return new ArrayList<>();
//        }
//        ArrayList<Post> postList = new ArrayList<Post>();
//        for(HashMap<String, Object> pvo : list) {
//            Post pdto = new Post();
//            pdto.setUserid((String) pvo.get("USERID"));
//            pdto.setAddress((String) pvo.get("ADDRESS"));
//            pdto.setContent((String) pvo.get("CONTENT"));
//            pdto.setPost_img((String) pvo.get("IMG"));
//            pdto.setCreate_date((Timestamp) pvo.get("CREATE_DATE"));
//            pdto.setPostNum( Integer.parseInt(String.valueOf( pvo.get("POST_NUM"))));
//            pdto.setLikeCount(Integer.parseInt(String.valueOf(pvo.get("LIKECOUNT"))));
//            pdto.setIsSaved(Integer.parseInt(String.valueOf(pvo.get("ISSAVED"))));
//            pdto.setIsLiked(Integer.parseInt(String.valueOf(pvo.get("ISLIKED"))));
//
//            //pdto.setReplyCount(pvo.get("REPLYCOUNT");
//            postList.add(pdto);
//        }
//
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        return postList;
//    }
//
//    // Get loginUser
//    @RequestMapping(value="/api/user/login", produces="application/json")
//    public ArrayList<HashMap<String, Object>> getLoginUser(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        HashMap<String, Object> mdtoHash = (HashMap<String, Object>) session.getAttribute("loginUser");
//        ArrayList<HashMap<String, Object>> returnArray = new ArrayList<>();
//
//
//        if(mdtoHash != null) {
//            HashMap<String, Object> result = new HashMap<>();
//
//            result.put("userid", mdtoHash.get("userid"));
//            result.put("name", mdtoHash.get("name"));
//            result.put("img", mdtoHash.get("img"));
//
//            returnArray.add(result);
//        }
//
//        return returnArray;
//    }
//
//    // Get loginUser from Session
//    private String getLoginUserid(HttpServletRequest request) {
//        HttpSession session = request.getSession();
//        HashMap<String, Object> mdtoHash = (HashMap<String, Object>) session.getAttribute("loginUser");
//        if(mdtoHash != null) {
//            return (String) mdtoHash.get("USERID");
//        }
//        return null;
//    }
//
//    @RequestMapping(value="/api/search/post", produces="application/json")
//    public ArrayList<Post> getPostSerachResult(@RequestParam(value="key", required = false) String key, @RequestParam(value="page", required = false) Integer page) {
//        // Create paramMap
//        HashMap<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("key", key);
//        paramMap.put("page", page == null ? 0 : page);
//        paramMap.put("ref_cursor", null);
//
//        // Get results in paramMap
//        us.getPostSearch(paramMap);
//
//        // Convert paramMap to ArrayList<PostDto>
//        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
//        if(list.size() == 0) {
//            return null;
//        }
//        ArrayList<Post> result = new ArrayList<Post>();
//        for(HashMap<String, Object> post : list) {
//            Post pdto = new Post();
//            //pdto.setPostNum((Integer) post.get("POST_NUM"));
//            pdto.setAddress((String) post.get("ADDRESS"));
//            pdto.setPost_img((String) post.get("IMG"));
//            pdto.setContent((String) post.get("CONTENT"));
//            pdto.setCreate_date((Timestamp) post.get("CREATE_DATE"));
//            result.add(pdto);
//        }
//        return result;
//    }
//
//    @RequestMapping(value="/api/explore/feed", produces="application/json")
//    public ArrayList<Post> getExploreFeed(@RequestParam(value="page", required = false) Integer page) {
//        // Create Hashmap
//        HashMap<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("page", page == null ? 0 : page);
//        paramMap.put("ref_cursor", null);
//        us.getExploreFeed(paramMap);
//
//        ArrayList<HashMap<String, Object>> list =
//                (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
//
//        if(list.size() == 0) {
//            return null;
//        }
//
//        ArrayList<Post> result = new ArrayList<Post>();
//        for(HashMap<String, Object> post : list) {
//            Post pdto = new Post();
//            pdto.setPostImg((String) post.get("IMG"));
//            pdto.setReplyCount(Integer.parseInt(String.valueOf(post.get("NUM_REPLY"))));
//            pdto.setLikeCount(Integer.parseInt(String.valueOf(post.get("NUM_LIKE"))));
//            pdto.setPostNum(Integer.parseInt(String.valueOf(post.get("POST_NUM"))));
//
//            result.add(pdto);
//        }
//
//        return result;
//    }
//
//    // Get Main Storylist
//    @RequestMapping(value="/api/story/list", produces = "application/json;charset=UTF-8")
//    public ArrayList<Member> getStoryList(HttpServletRequest request) {
//        String userid = getLoginUserid(request);
//
//        // Create paramMap
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("userid", userid);
//        paramMap.put("ref_cursor", null);
//
//        ss.getStoryList(paramMap);
//
//        // Get MemberDto from paramMap
//        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
//        ArrayList<Member> result = new ArrayList<Member>();
//        for(HashMap<String, Object> mem : list) {
//            Member mdto = new Member();
//            mdto.setImg((String) mem.get("IMG"));
//            mdto.setUserid((String) mem.get("USERID"));
//            result.add(mdto);
//        }
//
//        return result;
//    }
//}