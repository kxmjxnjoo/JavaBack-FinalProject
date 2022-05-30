package com.ezen.springfeed.post;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/post")
public class PostController {
    private final PostService ps;

    public PostController(PostService ps) {
        this.ps = ps;
    }

    @GetMapping("/{userid}")
    public List<Post> getPostsByUserid(@PathVariable("userid") String userid) {
        return ps.getPostsByUserid(userid);
    }

    @PostMapping
    public void addPost(@RequestBody Post post) {
        ps.addPost(post);
    }

    @PutMapping
    public void editPost(@RequestBody Post post) {
        ps.editPost(post);
    }

    @DeleteMapping("/{postnum}")
    public void deletePost(@PathVariable("postnum") Long postnum) {
        ps.deletePost(postnum);
    }
}
