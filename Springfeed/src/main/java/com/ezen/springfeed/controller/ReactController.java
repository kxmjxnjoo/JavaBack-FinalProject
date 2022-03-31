package com.ezen.springfeed.controller;

import com.ezen.springfeed.dto.PostDto;
import com.ezen.springfeed.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class ReactController {

    @Autowired
    PostService ps;

    @RequestMapping("/api/post")
    @CrossOrigin(origins="http://localhost:3000")
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
}