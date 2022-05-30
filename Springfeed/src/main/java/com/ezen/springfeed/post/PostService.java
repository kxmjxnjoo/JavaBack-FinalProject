package com.ezen.springfeed.post;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository pr;

    public PostService(PostRepository pr) {
        this.pr = pr;
    }

    public List<Post> getPostsByUserid(String userid) {
        return pr.findAllByUseridOrderByCreateDate(userid);
    }

    public void addPost(Post post) {
        pr.save(post);
    }

    @Transactional
    public void editPost(Post editedPost) {
        Post post = pr.findById(editedPost.getPostNum())
                .orElseThrow(() -> new IllegalStateException(
                        "수정하려는 포스트가 없어요"
                ));

        if(editedPost.getPostImg() != null) {
            post.setPostImg(editedPost.getPostImg());
        }

        if(editedPost.getContent() != null) {
            post.setContent(editedPost.getContent());
        }

        if(editedPost.getAddress() != null) {
            post.setAddress(editedPost.getAddress());
        }
    }

    public void deletePost(Long postnum) {
        pr.deleteById(postnum);
    }
}
