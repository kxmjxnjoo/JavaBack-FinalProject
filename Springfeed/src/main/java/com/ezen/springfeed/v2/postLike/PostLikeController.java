package com.ezen.springfeed.v2.postLike;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v2/post/like")
public class PostLikeController {
    private final PostLikeService pls;

    public PostLikeController(PostLikeService pls) {
        this.pls = pls;
    }

    @GetMapping("/{postnum}")
    public Integer getPostLikeCount(@PathVariable("postnum") Long postNum) {
        return pls.getPostLikeCount(postNum);
    }

    @PostMapping
    public void addPostLike(@RequestBody PostLike postLike) {
        pls.addPostLike(postLike);
    }

    @DeleteMapping
    public void deletePostLike(@RequestParam("postNum") Long postNum, @RequestParam("userid") String userid) {
        pls.deletePostLike(postNum, userid);
    }
}
