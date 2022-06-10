package com.ezen.springfeed.v2.replyLike;

import com.ezen.springfeed.model.ReplyLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyLikeRepository extends JpaRepository<ReplyLike, Long> {
    Integer countAllByReplyNum(Integer replyNum);

    Integer countAllByReplyNumAndUserid(Integer replyNum, String userid);

    ReplyLike findByReplyNumAndUserid(Integer replyNum, String userid);
}
