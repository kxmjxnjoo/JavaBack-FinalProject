package com.ezen.springfeed.v2.reply;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findAllByPostNumOrderByReplyDate(Long postNum);
}
