package com.ezen.springfeed.v2.replyLike;

import org.springframework.stereotype.Service;

@Service
public class ReplyLikeService {
    private final ReplyLikeRepository rlr;

    public ReplyLikeService(ReplyLikeRepository rlr) {
        this.rlr = rlr;
    }

    public Integer getReplyLikeCount(Integer replyNum) {
        return rlr.countAllByReplyNum(replyNum);
    }

    public Integer getIsReplyLikedByUserid(Integer replyNum, String userid) {
        return rlr.countAllByReplyNumAndUserid(replyNum, userid);
    }

    public void addReplyLike(ReplyLike replyLike) {
        rlr.save(replyLike);
    }

    public void deleteReply(Integer replyNum, String userid) {
        ReplyLike replyLike = rlr.findByReplyNumAndUserid(replyNum, userid);

        if(replyLike == null) {
            return;
        }

        rlr.deleteById(replyLike.getId());
    }
}
