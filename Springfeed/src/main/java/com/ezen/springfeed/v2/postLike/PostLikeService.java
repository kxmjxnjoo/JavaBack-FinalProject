package com.ezen.springfeed.v2.postLike;

import com.ezen.springfeed.model.PostLike;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostLikeService {
    private final PostLikeRepository plr;

    public PostLikeService(PostLikeRepository plr) {
        this.plr = plr;
    }

    public Integer getPostLikeCount(Long postNum) {
        return plr.countByPostNum(postNum);
    }

    public void addPostLike(PostLike postLike) {
        Optional<PostLike> search = plr.findByPostNumAndUserid(postLike.getPostNum(), postLike.getUserid());

        if(search.isPresent()) {
            throw new IllegalStateException("이미 좋아요 했어요");
        }

        plr.save(postLike);
    }

    public void deletePostLike(Long postNum, String userid) {
        Optional<PostLike> postLike = plr.findByPostNumAndUserid(postNum, userid);

        if(!postLike.isPresent()) {
            throw new IllegalStateException("포스트에 좋아요를 하지 않았어요");
        }

        plr.deleteById(postLike.get().getPostLikeNum());
    }
}
