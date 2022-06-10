package com.ezen.springfeed.v2.post;

import com.ezen.springfeed.model.Post;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/post")
public class PostController {
    private final PostService ps;

    public PostController(PostService ps) {
        this.ps = ps;
    }

    // GET
    @GetMapping("{postnum}")
    public Post getPostByPostnum(@PathVariable("postnum") Long postNum) {
        return ps.selectPostById(postNum);
    }

    @GetMapping
    public List<Post> getPostsOrderByLikeCount() {
        return ps.selectAllPostsOrderByLikeCount();
    }

    @GetMapping("/feed/{userid}")
    public List<Post> getPostFeedByUserid(@PathVariable("userid") String userid) {
        return ps.selectAllPosts();
    }

    @GetMapping("/{userid}")
    public List<Post> getPostsByUserid(@PathVariable("userid") String userid) {
        return ps.getPostsByUserid(userid);
    }

    // POST
    @PostMapping
    public void addPost(@RequestBody Post post) {
        ps.addPost(post);
    }

    // PUT
    @PutMapping("{postnum}")
    public void editPost(@RequestBody Post post, @RequestParam("postnum") Long postNum) {
        ps.editPost(post);
    }

    // DELETE
    @DeleteMapping("/{postnum}")
    public void deletePost(@PathVariable("postnum") Long postnum) {
        ps.deletePost(postnum);
    }
}
