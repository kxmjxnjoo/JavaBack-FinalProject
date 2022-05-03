package com.ezen.springfeed.controller;

import com.ezen.springfeed.dto.MemberDto;
import com.ezen.springfeed.dto.PostDto;
import com.ezen.springfeed.dto.ReplyDto;
import com.ezen.springfeed.service.MemberService;
import com.ezen.springfeed.service.PostService;
import com.ezen.springfeed.service.StoryService;
import com.ezen.springfeed.service.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class ReactController {

    @Autowired
    PostService ps;

    @Autowired
    MemberService ms;

    @Autowired
    UtilService us;

    @Autowired
    StoryService ss;

    // Get user's post
    @RequestMapping(value="/api/post")
    public ArrayList<PostDto> getPostsByUserid(@RequestParam("userid") String userid) {
        // Get Posts from db
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userid", userid);
        paramMap.put("ref_cursor", null);
        ps.getPostsByUserid(paramMap);
        ArrayList<HashMap<String, Object>> postHashList =
                (ArrayList<HashMap<String, Object>>)  paramMap.get("ref_cursor");

        // Convert Hashmap to PostDto
        ArrayList<PostDto> postList = new ArrayList<PostDto>();
        for(HashMap<String, Object> postHashmap : postHashList) {
            PostDto pdto = new PostDto();
            pdto.setAddress((String) postHashmap.get("ADDRESS"));
            pdto.setUserid((String) postHashmap.get("USERID"));
            pdto.setPost_img((String) postHashmap.get("IMG"));
            pdto.setContent((String) postHashmap.get("CONTENT"));
            postList.add(pdto);
        }

        return postList;
    }

    // Get loginUser's feed
    @RequestMapping(value="/api/post/feed", produces="application/json")
    public ArrayList<PostDto> getPostFeed(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="page", required=false) Integer page) {

        String userid = getLoginUserid(request);
        if(userid == null) {
            return new ArrayList<>();
        }

        // Get 10 posts by pages
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", userid);
        paramMap.put("page", page == null ? 0 : page);
        paramMap.put("ref_cursor", null);
        ps.getPostFeed(paramMap);

        ArrayList<HashMap<String, Object>> list =
                (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
        if(list.size() == 0 || list == null) {
            return new ArrayList<>();
        }
        ArrayList<PostDto> postList = new ArrayList<PostDto>();
        for(HashMap<String, Object> pvo : list) {
            PostDto pdto = new PostDto();
            pdto.setUserid((String) pvo.get("USERID"));
            pdto.setAddress((String) pvo.get("ADDRESS"));
            pdto.setContent((String) pvo.get("CONTENT"));
            pdto.setPost_img((String) pvo.get("IMG"));
            pdto.setCreate_date((Timestamp) pvo.get("CREATE_DATE"));
            pdto.setPostNum( Integer.parseInt(String.valueOf( pvo.get("POST_NUM"))));
            pdto.setLikeCount(Integer.parseInt(String.valueOf(pvo.get("LIKECOUNT"))));

            //pdto.setReplyCount(pvo.get("REPLYCOUNT");
            postList.add(pdto);
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
        return postList;
    }

    // Get loginUser
    @RequestMapping(value="/api/user/login", produces="application/json")
    public MemberDto getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        HashMap<String, Object> mdtoHash = (HashMap<String, Object>) session.getAttribute("loginUser");

        if(mdtoHash != null) {
            MemberDto mdto = new MemberDto();
            mdto.setUserid((String) mdtoHash.get("USERID"));
            mdto.setEmail((String) mdtoHash.get("EMAIL"));
            mdto.setImg((String) mdtoHash.get("IMG"));
            mdto.setIntroduce((String) mdtoHash.get("INTRODUCE"));
            mdto.setName((String) mdtoHash.get("NAME"));
            mdto.setPhone((String) mdtoHash.get("PHONE"));
            return mdto;
        }
        return null;
    }

    // Get loginUser from Session
    private String getLoginUserid(HttpServletRequest request) {
        HttpSession session = request.getSession();
        HashMap<String, Object> mdtoHash = (HashMap<String, Object>) session.getAttribute("loginUser");
        if(mdtoHash != null) {
            return (String) mdtoHash.get("USERID");
        }
        return null;
    }

    // Get specific user's info
    @RequestMapping(value="/api/user", produces="application/json")
    public MemberDto getUser(HttpServletRequest request, @RequestParam("id") String id) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", id);
        paramMap.put("ref_cursor", null);
        ms.getMember(paramMap);

        ArrayList<HashMap<String, Object>> list =
                (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
        if(list.size() == 0) {
            return null;
        }
        HashMap<String, Object> mvo = list.get(0);
        MemberDto mdto = new MemberDto();
        mdto.setPhone((String) mvo.get("PHONE"));
        mdto.setIntroduce((String) mvo.get("INTRODUCE"));
        mdto.setName((String) mvo.get("NAME"));
        mdto.setEmail((String) mvo.get("EMAIL"));
        mdto.setUserid((String) mvo.get("USERID"));
        mdto.setImg((String) mvo.get("IMG"));

        // Get isfollowing
        String loginUser = getLoginUserid(request);
        if(loginUser != null) {
            paramMap = new HashMap<>();
            paramMap.put("follower", loginUser);
            paramMap.put("userid", id);

            ms.getIsFollowing(paramMap);

            mdto.setIsFollowing(Integer.parseInt(String.valueOf(paramMap.get("isFollowing"))));
        }

        return mdto;
    }

    // Get userpage info
    @RequestMapping(value="/api/user/follow/count", produces="application/json")
    public HashMap<String, Object> getUserpage(@RequestParam(value="id") String id) {
        // Create paramMap
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", id);
        paramMap.put("ref_cursor", null);

        // Get followerCount
        int followerCount = 0;
        ms.getFollowerCount(paramMap);
        followerCount = Integer.parseInt((String) paramMap.get("followerCount"));

        // Get followingCount
        int followingCount = 0;
        ms.getFollowingCount(paramMap);
        followingCount = Integer.parseInt((String) paramMap.get("followingCount"));

        // Create map
        HashMap<String, Object> result = new HashMap<>();
        result.put("follower", followerCount);
        result.put("following", followingCount);

        return result;
    }

    // Get search result
    @RequestMapping(value="/api/search/member", produces="application/json")
    public ArrayList<MemberDto> getMemberSearchResult(@RequestParam(value="key") String key, @RequestParam(value="page", required = false) Integer page) {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("key", key);
        paramMap.put("page", page != null ? page : 0);
        paramMap.put("ref_cursor", null);
        us.getMemberSearch(paramMap);

        ArrayList<HashMap<String, Object>> list =
                (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");

        if(list.size() == 0) {
            return null;
        }

        ArrayList<MemberDto> result = new ArrayList<MemberDto>();
        for(HashMap<String, Object> mem : list) {
            MemberDto mdto = new MemberDto();
            mdto.setName((String) mem.get("NAME"));
            mdto.setUserid((String) mem.get("USERID"));
            result.add(mdto);
        }

        return result;
    }

    @RequestMapping(value="/api/search/post", produces="application/json")
    public ArrayList<PostDto> getPostSerachResult(@RequestParam(value="key", required = false) String key, @RequestParam(value="page", required = false) Integer page) {
        // Create paramMap
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("key", key);
        paramMap.put("page", page == null ? 0 : page);
        paramMap.put("ref_cursor", null);

        // Get results in paramMap
        us.getPostSearch(paramMap);

        // Convert paramMap to ArrayList<PostDto>
        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
        if(list.size() == 0) {
            return null;
        }
        ArrayList<PostDto> result = new ArrayList<PostDto>();
        for(HashMap<String, Object> post : list) {
            PostDto pdto = new PostDto();
            //pdto.setPostNum((Integer) post.get("POST_NUM"));
            pdto.setAddress((String) post.get("ADDRESS"));
            pdto.setPost_img((String) post.get("IMG"));
            pdto.setContent((String) post.get("CONTENT"));
            pdto.setCreate_date((Timestamp) post.get("CREATE_DATE"));
            result.add(pdto);
        }
        return result;
    }

    // Get user's following list (page)
    @RequestMapping(value="/api/user/following", produces="application/json")
    public ArrayList<MemberDto> getUserFollowingList(@RequestParam(value="id") String id, @RequestParam(value="page", required = false) Integer page) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", id);
        paramMap.put("page", page != null ? page : 0);
        paramMap.put("load", 5);
        paramMap.put("ref_cursor", null);
        ms.getFollowedList(paramMap);

        ArrayList<HashMap<String, Object>> list =
                (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
        if(list.size() == 0) {
            return null;
        }
        ArrayList<MemberDto> result = new ArrayList<MemberDto>();
        for(HashMap<String, Object> mem : list) {
            MemberDto mdto = new MemberDto();
            mdto.setImg((String) mem.get("IMG"));
            mdto.setName((String) mem.get("NAME"));
            mdto.setUserid((String) mem.get("USERID"));

            result.add(mdto);
        }
        return result;
    }

    // Get user's follower list (page)
    @RequestMapping(value="/api/user/follower", produces = "application/json")
    public ArrayList<MemberDto> getUserFollowerList(@RequestParam(value="id") String id, @RequestParam(value="page", required = false) Integer page) {
        // Create paramMap
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", id);
        paramMap.put("page", page != null ? page : 0);
        paramMap.put("load", 5);
        paramMap.put("ref_cursor", null);

        // Get data from db
        ms.getFollowerList(paramMap);

        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");
        if(list.size() == 0) {
            return null;
        }
        ArrayList<MemberDto> result = new ArrayList<MemberDto>();
        for(HashMap<String, Object> mem : list) {
            MemberDto mdto = new MemberDto();
            mdto.setUserid((String) mem.get("USERID"));
            mdto.setImg((String) mem.get("IMG"));
            mdto.setName((String) mem.get("NAME"));
            result.add(mdto);
        }

        return result;
    }

    @RequestMapping(value="/api/explore/feed", produces="application/json")
    public ArrayList<PostDto> getExploreFeed(@RequestParam(value="page", required = false) Integer page) {
        // Create Hashmap
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("page", page == null ? 0 : page);
        paramMap.put("ref_cursor", null);
        us.getExploreFeed(paramMap);

        ArrayList<HashMap<String, Object>> list =
                (ArrayList<HashMap<String, Object>>) paramMap.get("ref_cursor");

        if(list.size() == 0) {
            return null;
        }

        ArrayList<PostDto> result = new ArrayList<PostDto>();
        for(HashMap<String, Object> post : list) {
            PostDto pdto = new PostDto();
            pdto.setPost_img((String) post.get("POST_IMG"));
            pdto.setReplyCount(Integer.parseInt(String.valueOf(post.get("NUM_REPLY"))));
            pdto.setLikeCount(Integer.parseInt(String.valueOf(post.get("NUM_LIKE"))));
            pdto.setPostNum(Integer.parseInt(String.valueOf(post.get("POST_NUM"))));
            result.add(pdto);
        }

        return result;
    }

    // -1 : fail
    // 0 : unlike
    // 1 : like
    @RequestMapping(value="/api/post/like", produces = "application/json")
    public int likePost(@RequestParam(value="num") int postNum) {
        // Create paramMap
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("postNum", postNum);
        paramMap.put("ref_cursor", null);

        ps.insertLike(paramMap);

        // Get result
        HashMap<String, Object> result = (HashMap<String, Object>) paramMap.get("ref_cursor");
        return (int) result.get("result");
    }

    // Get Main Storylist
    @RequestMapping(value="/api/story/list", produces = "application/json")
    public ArrayList<MemberDto> getStoryList(HttpServletRequest request) {
        String userid = getLoginUserid(request);

        // Create paramMap
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", userid);

        ss.getStoryList(paramMap);

        // Get MemberDto from paramMap
        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) paramMap.get("p_cur");
        ArrayList<MemberDto> result = new ArrayList<MemberDto>();
        for(HashMap<String, Object> mem : list) {
            MemberDto mdto = new MemberDto();
            mdto.setImg((String) mem.get("IMG"));
            mdto.setUserid((String) mem.get("USERID"));
            result.add(mdto);
        }

        return result;
    }

    // Get saved post
    @RequestMapping(value="/api/post/save", produces = "application/json")
    public ArrayList<PostDto> getSavedPost(@RequestParam("id") String id, @RequestParam(value="page", required = false) Integer page) {
        // Create paramMap
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", id);
        paramMap.put("page", page == null ? 0 : page);

        // Get result
        ps.getSavedPost(paramMap);

        // Convert it to PostDto
        ArrayList<HashMap<String, Object>> result = (ArrayList<HashMap<String, Object>>) paramMap.get("p_cur");
        ArrayList<PostDto> list = new ArrayList<>();
        for(HashMap<String, Object> post : result) {
            PostDto pdto = new PostDto();
            pdto.setPost_img((String) post.get("IMG"));
            pdto.setPostNum(Integer.parseInt(String.valueOf(post.get("POST_NUM"))));
            pdto.setLikeCount(Integer.parseInt(String.valueOf(post.get("NUM_LIKE"))));
            list.add(pdto);
        }

        return list;
    }

    // Insert save post
    @RequestMapping(value="/api/post/save/insert", produces="application/json")
    public int insertSavePost(HttpServletRequest request, @RequestParam("num") int num) {
        // Create paramMap
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", getLoginUserid(request));
        paramMap.put("postnum", num);

        ps.insertSavePost(paramMap);

        return Integer.parseInt(String.valueOf(paramMap.get("RESULT")));
    }

    @RequestMapping(value="/api/user/follow", produces="application/json")
    public int follow(HttpServletRequest request, @RequestParam("id") String following) {
        // Create paramMap
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("follower", getLoginUserid(request));
        paramMap.put("followed", following);

        ms.insertFollow(paramMap);

        return Integer.parseInt(String.valueOf(paramMap.get("result")));
    }

    @RequestMapping(value="/api/user/unfollow")
    public int unfollow(HttpServletRequest request, @RequestParam("id") String following) {
        // Create ParamMap
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("followed", following);
        paramMap.put("follower", getLoginUserid(request));

        ms.unfollow(paramMap);

        int result = Integer.parseInt(String.valueOf(paramMap.get("result")));

        /* Add noti
        if(result != 0) {
            HashMap<String, Object> paramMap = new HashMap<>();
            ms.addNotification();
        }
         */

        return result;
    }

    @RequestMapping(value="/api/post/num")
    public PostDto getPostByNum(HttpServletRequest request, @RequestParam("num") int num, @RequestParam(value="userid", required = false) String userid) {
        // Create paramMap
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("postnum", num);
        paramMap.put("userid", userid == null ? getLoginUserid(request) : userid);
        paramMap.put("p_cur", null);

        ps.selectPostByNum(paramMap);

        // Get postdto
        ArrayList<HashMap<String, Object>> result = (ArrayList<HashMap<String, Object>>) paramMap.get("p_cur");

        if(result == null) {
            return null;
        }

        for(HashMap<String, Object> post : result) {
            PostDto pdto = new PostDto();
            pdto.setPost_img((String) post.get("IMG"));
            pdto.setContent((String) post.get("CONTENT"));
            pdto.setAddress((String) post.get("ADDRESS"));
            pdto.setUserid((String) post.get("USERID"));
            pdto.setCreate_date((Timestamp) post.get("CREATE_DATE"));
            pdto.setUser_img((String) post.get("USERIMG"));
            pdto.setLikeCount(Integer.parseInt(String.valueOf(post.get("LIKE_COUNT"))));

            pdto.setIsLiked(Integer.parseInt(String.valueOf(post.get("ISLIKED"))));
            pdto.setIsSaved(Integer.parseInt(String.valueOf(post.get("ISSAVED"))));
            return pdto;
        }

        return null;
    }

    @RequestMapping(value="/api/post/comment")
    public ArrayList<ReplyDto> getReplyByPostNum(@RequestParam("postnum") int postNum, @RequestParam(value="page", required = false) Integer page) {
        // Create paramMap
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("postnum", postNum);
        paramMap.put("page", page == null ? 0 : page);
        paramMap.put("p_cur", null);

        ps.getReplyByPostNum(paramMap);

        // Convert it to ReplyDto
        ArrayList<HashMap<String, Object>> result = (ArrayList<HashMap<String, Object>>) paramMap.get("p_cur");
        ArrayList<ReplyDto> list = new ArrayList<>();

        for(HashMap<String, Object> re : result) {
            ReplyDto rdto = new ReplyDto();
            rdto.setContent((String) re.get("CONTENT"));
            rdto.setImg((String) re.get("IMG"));
            rdto.setUserid((String) re.get("USERID"));
            rdto.setReply_date((Timestamp) re.get("REPLY_DATE"));

            list.add(rdto);
        }

        return list;
    }
}