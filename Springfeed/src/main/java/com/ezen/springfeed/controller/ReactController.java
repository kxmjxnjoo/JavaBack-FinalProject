package com.ezen.springfeed.controller;

import com.ezen.springfeed.dto.MemberDto;
import com.ezen.springfeed.dto.PostDto;
import com.ezen.springfeed.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class ReactController {

    @Autowired
    PostService ps;

    @RequestMapping("/api/post")
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
            pdto.setPost_img((String) postHashmap.get("POST_IMG"));
            pdto.setContent((String) postHashmap.get("CONTENT"));
            postList.add(pdto);
        }

        return postList;
    }

    // Member
    @RequestMapping(value="/api/user")
    public MemberDto getUser(HttpServletRequest request) {
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
}